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

(def reset-padding
  {:padding "0 !important"})

(def border-box
  {:box-sizing "border-box"})

(defn clearfix [clazz]
  [clazz {:*zoom 1}
   [:&:before :&:after {:display "table"
                        :content " "
                        :line-height 0}]
   [:&:after {:clear "both"}]])

(def alignments
  [[:.align-center {:text-align "center"}]
   [:.align-left {:text-align "left"}]
   [:.align-right {:text-align "right"}]
   [:.pull-left {:float "left"}]
   [:.pull-right {:float "right"}]])
