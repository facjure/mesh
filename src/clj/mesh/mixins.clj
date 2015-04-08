(ns mesh.mixins
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt]]
            [garden.units :refer (px+ px* px- px-div em)]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-media]]))

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
