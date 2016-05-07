(ns mesh.core.utils
  (:require [garden.core :refer [css]]))

#?(:cljs
   (defn viewport-w []
     (.. js/document -documentElement -clientWidth)))

#?(:cljs
   (defn viewport-h []
     (.. js/document -documentElement -clientHeight)))

(def alignments
  [[:.align-center {:text-align "center"}]
   [:.align-left {:text-align "left"}]
   [:.align-right {:text-align "right"}]
   [:.pull-left {:float "left"}]
   [:.pull-right {:float "right"}]])
