(ns mesh.respond
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.core :refer [css]]
            [garden.units :as u :refer [px pt]]
            [garden.units :refer (px+ px* px- px-div em)]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-media]]))

(def breakpoints
  {:mobile (px 480)
   :tablet (px 720)
   :laptop (px 960)
   :monitor (px 1920)})
