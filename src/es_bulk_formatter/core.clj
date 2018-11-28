(ns es-bulk-formatter.core
  (require [cheshire.core :refer :all]
           [clojure.java.io :refer :all])
  (:gen-class))

(defmulti bulk-operation (fn [op input] op))
(defmethod bulk-operation :index [_ input]
  (str (generate-string {:index {}}) "\n" (generate-string input) "\n"))
(defmethod bulk-operation :create [_ input]
  (str (generate-string {:create {}}) "\n" (generate-string input) "\n"))
(defmethod bulk-operation :delete [_ input]
  (str (generate-string {:delete {:_id input}}) "\n"))
(defmethod bulk-operation :update [_ _])

(defn read-json
  [file]
  (-> file
      slurp
      (parse-string true)))

(defn bulk-format
  [in-file op out-file]
  (let [inputs (read-json in-file)]
    (with-open [w (writer out-file :append true)]
      (doseq [input inputs]
        (.write
          w
          (bulk-operation op input))))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
