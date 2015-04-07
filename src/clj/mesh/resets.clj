(ns mesh.resets
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt]]
            [garden.units :refer (px+ px* px- px-div em)]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-media]]))


(defn partly-rounded
  ([r1] (partly-rounded r1 r1))
  ([r1 r2]
   {:border {:top-right-radius r1
             :bottom-left-radius r2}}))

(defstyles styles
  (list partly-rounded))
