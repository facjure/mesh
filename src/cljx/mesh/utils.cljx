(ns mesh.utils
  (:require [garden.core :refer [css]]))

#+clj
(defn pow [base e]
  (.pow Math base e))

#+cljs
(defn insert-stylesheet [styles]
  "Inserts Stylesheet into document head"
  (let [el (.createElement js/document "style")
        node (.createTextNode js/document styles)]
    (.appendChild el node)
    (.appendChild (.-head js/document) el)
    el))

#+cljs
(defn pow [base e]
  (.pow js/Math base e))

#+cljs
(defn viewport-w []
  (.. js/document -documentElement -clientWidth))

#+cljs
(defn viewport-h []
  (.. js/document -documentElement -clientHeight))
