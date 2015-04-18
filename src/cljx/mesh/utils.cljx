(ns mesh.utils
  (:require [garden.core :refer [css]]))

#+clj
(defn pow [base e]
  (.pow Math base e))

#+cljs
(defn pow [base e]
  (.pow js/Math base e))

#+cljs
(defn viewport-w []
  (.. js/document -documentElement -clientWidth))

#+cljs
(defn viewport-h []
  (.. js/document -documentElement -clientHeight))
