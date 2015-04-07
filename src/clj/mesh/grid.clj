(ns mesh.grid
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt]]
            [garden.units :refer (px+ px* px- px-div em)]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-media]]))

(def base-color "#f921ab")
(def color-grey "#f2f2f2")

(def red (hsl 0 100 50))
(def orange (color/hsl 30 100 50))

(color/mix red orange)

;; Mixins


;; Layout

(defn page [bg-color padding]
  [:body
   {:border-width (px 100)
    :background-color bg-color
    :color (color/mix bg-color red)
    :padding (em padding)
    :marging (em 10)
    :line-height 2}])

(defstyles styles
  (list (page color-grey 2)))
