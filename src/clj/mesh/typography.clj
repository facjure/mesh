(ns mesh.typography
  (:refer-clojure :exclude [+ - * / rem])
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt]]
            [garden.units :refer (px+ px* px- px-div em rem)]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-media]]))

(defn font [family size weight letter-spacing line-height & options]
  {:font-family family
   :font-size (rem size)
   :font-weight weight
   :letter-spacing (rem letter-spacing)
   :line-height (em line-height)
   :text-transform (get options :text-transform "none")})

(defn typeset [serif sans mono]
  [[:body :p (font sans 1 300 0.1 1.5)]
   [:h1 (font serif 3 600 0.5 2)]
   [:h2 (font serif 3 400 0.5 2)]
   [:h3 (font serif 2 300 0.5 2)]
   [:h4 (font serif 1.5 300 0.3 2)]
   [:h5 :h6 (font mono 1.2 300 0.2 1.5)]
   [:header (font serif 4 700 0.3 1.5 "small-caps")]
   [:footer (font sans 1 100 0.3 1.5)]])


