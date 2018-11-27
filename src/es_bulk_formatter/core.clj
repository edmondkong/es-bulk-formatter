(ns es-bulk-formatter.core
  (require [cheshire.core :refer :all]
           [clojure.java.io :refer :all])
  (:gen-class))

(defmulti bulk-operation (fn [op] op))
(defmethod bulk-operation :index [_] {:index {}})
(defmethod bulk-operation :create [_])
(defmethod bulk-operation :delete [_])
(defmethod bulk-operation :update [_])

(defn read-json
  [file]
  (-> file
      slurp
      (parse-string true)))

(defn bulk-format
  [in-file op out-file]
  (let [inputs (read-json in-file)
        bulk-op (bulk-operation op)]
    (with-open [w (writer out-file :append true)]
      (doseq [input inputs]
        (.write
          w
          (str
            (generate-string bulk-op)
            "\n"
            (generate-string input)
            "\n"))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
