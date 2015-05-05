(ns dev.repl
  (:require-macros
   [figwheel.client.utils :refer [enable-dev-blocks!]])
  (:require [examples.hello.page :as hello]
            [examples.typography.page :as typo]
            [examples.grids.page :as grids]
            [examples.storyboard.page :as story]
            [figwheel.client :as fw]))

(enable-console-print!)
(enable-dev-blocks!)

(print "Starting Figwheel Cljs Repl ... ")

(fw/start
 {:websocket-url "ws://localhost:3449/figwheel-ws"
  :on-jsload (fn []
               (print "Figwheel ... js loaded "))
  :load-warninged-code true})
