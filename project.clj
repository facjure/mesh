(defproject facjure/mesh "0.5.1"
  :description "A toolkit for responsive grids and typography"
  :url "https://github.com/facjure/mesh"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git" :url "https://github.com/facjure/mesh"}
  :min-lein-version "2.8.1"
  :global-vars {*warn-on-reflection* false *assert* false}
  :dependencies [[org.clojure/clojure "1.10.0" :scope "provided"]
                 [org.clojure/clojurescript "1.10.439" :scope "provided"]
                 [com.gfredericks/cljs-numbers "0.1.2"]
                 [garden "1.3.6"]]
  :node-dependencies [[autoprefixer "9.4.3"]
                      [css-min "0.4.3"]]
  :source-paths ["src" "target/classes"]
  :clean-targets ^{:protect false} ["resources/public/js" "target/classes"]
  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["examples" "env/dev"]
                :compiler {:main dev.repl
                           :output-to "resources/public/js/mesh.js"
                           :output-dir "resources/public/js/out"
                           :asset-path "js/out"
                           :optimizations :none
                           :cache-analysis true
                           :source-map true
                           :install-deps true
                           :npm-deps {:create-react-class "15.6.0"}
                           :preloads [devtools.preload]}}
               {:id "prod"
                        :source-paths ["src"]
                        :compiler {:output-to "dist/mesh.min.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]}
  :garden {:builds
           [{:id "typography"
             :source-paths ["src" "examples"]
             :stylesheet typography.styles/index
             :compiler {:output-to "resources/public/css/typography.css"
                        :pretty-print true}}
            {:id "grids"
             :source-paths ["src" "examples"]
             :stylesheet grids.styles/index
             :compiler {:output-to "resources/public/css/grids.css"
                        :pretty-print true}}]}

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.16"]
            [lein-garden "0.2.8"]
            [lein-npm "0.6.2"]
            [lein-pdo "0.1.1"]
            [lein-doo "0.1.9"]]

  :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}

  :profiles {:dev
             {:dependencies [[cider/piggieback "0.3.10" :exclude [org.clojure/tools.nrepl]]
                             [figwheel-sidecar "0.5.16"]
                             [binaryage/devtools "0.9.10"]
                             [reagent "0.8.1"]]
              :figwheel {:http-server-root "public"
                         :server-port 3449
                         :nrepl-port 7888
                         :css-dirs ["resources/public/css"]
                         :open-file-command "emacsclient"}}}
  :aliases {"init"  ["pdo" "clean," "garden" "clean"]
            "dev" ["pdo" "garden" "auto," "figwheel"]
            "release" ["pdo" "clean," "cljsbuild" "once" "prod"]})
