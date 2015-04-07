(defproject mesh "0.1.0"
  :description "A responsive grid & typography toolkit, built on Clojurescript"
  :url "https://github.com/facjure/mesh"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/facjure/mesh"}
  :min-lein-version "2.5.0"
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :global-vars {*warn-on-reflection* false *assert* false}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2850"]
                 [garden "1.2.5"]]

  :node-dependencies [[autoprefixer "5.1.0"]]

  :source-paths ["src/clj" "target/classes"]

  :clean-targets ^{:protect false} ["resources/public/js" "target/classes"]

  :cljsbuild {:builds
              {:app {:source-paths ["src/cljs"]
                     :compiler {:output-to "resources/public/js/mesh.js"
                                :output-dir "resources/public/js/out"
                                :main dev.repl
                                :asset-path "js/out"
                                :optimizations :none
                                :cache-analysis true
                                :source-map true}}}}

  :profiles {:dev {:dependencies [[figwheel "0.2.5"]
                                  [figwheel-sidecar "0.2.5"]
                                  [org.omcljs/om "0.8.8"]
                                  [sablono "0.3.4"]]
                   :cljsbuild {:builds
                               {:app {:source-paths ["env/dev"]}}}
                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :nrepl-port 7888
                              :css-dirs ["resources/public/css"]
                              :open-file-command "emacsclient"}
                   :garden {:builds
                            [{:id "mesh"
                              :source-paths ["src/clj"]
                              :stylesheet mesh.styles/all
                              :compiler {:output-to "resources/public/css/mesh.css"
                                         :pretty-print true}}]}}}

  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-garden "0.2.5"]
            [lein-npm "0.5.0"]
            [lein-figwheel "0.2.5-SNAPSHOT"]
            [lein-pdo "0.1.1"]
            [lein-cljfmt "0.1.7"]]

  :aliases {"clean-all"  ["pdo" "clean," "garden" "clean"]
            "dev" ["pdo" "garden" "auto," "figwheel"]
            "format" ["cljfmt" "check"]})
