(ns dev.repl
  (:require-macros
   [figwheel.client.utils :refer [enable-dev-blocks!]])
  (:require [examples.om.page :as main]
            [figwheel.client :as fw]))

(enable-console-print!)
(enable-dev-blocks!)

(print "Starting Figwheel Cljs Repl ... ")

(fw/start
 {:websocket-url "ws://localhost:3449/figwheel-ws"
  :on-jsload (fn []
               (print "Figwheel ... js loaded "))
  :load-warninged-code true})
