(defproject mesh "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [garden "1.1.5"]]

  :plugins [[lein-cljsbuild "1.0.2"]
            [lein-garden "0.1.8"]]

  :source-paths ["src"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src"]
              :compiler {
                :output-to "mesh.js"
                :output-dir "out"
                :optimizations :none
                :pretty-print true
                :source-map true}}]})
