(defproject es-bulk-formatter "0.1.0-SNAPSHOT"
  :description "Clojure REPL tool for creating bulk operations for Elasticsearch"
  :url "https://github.com/edmondkong/es-bulk-formatter"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot es-bulk-formatter.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
