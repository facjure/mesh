(ns typography.styles
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstyles defrule]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em]]
            [garden.arithmetic :refer [+ - * /]]
            [facjure.mesh.media :as respond]
            [facjure.mesh.typography :as typo :refer [typeset vr-block scales scale-type make-serifs]]
            [facjure.mesh.grid :as grid]))

(def alegreya ["Alegreya" "Baskerville" "Georgia" "Times" "serif"])
(def sans ["\"Open Sans\"" "Avenir" "Helvetica" "sans-serif"])
(def mono ["Inconsolata" "Menlo" "Courier" "monospace"])

(defstyles fonts
  (typeset alegreya sans mono))

(defrule homepage :section.home)
(defrule body :body)
(defrule h1 :h1)
(defrule h2 :h2)
(defrule h3 :h3)
(defrule h4 :h4)
(defrule h5 :h5)
(defrule h6 :h6)
(defrule small :p.small)
(defrule medium :p.medium)
(defrule large :p.large)

(def ms
  (let [f (typo/modular-scale-fn 16 (:golden scales))]
    (fn [n]
      (px (f n)))))

(defstyles typography
  (body
   {:font-family alegreya})

  (homepage
   (h1
     (respond/tablet
      {:padding  [[0 (ms 2)]]
       :font-size (ms 5)
       :line-height (ms 5)})
     (respond/iphone-5
      {:padding  [[0 (ms 2)]]
       :font-size (ms 5)
       :line-height (ms 5)}))

   (h2
     (respond/tablet
      {:padding  [[0 (ms 1)]]
       :font-size (ms 4)
       :line-height (ms 4)}))

   (h3
     (respond/tablet
      {:padding  [[0 (ms 1)]]
       :font-size (ms 3)
       :line-height (ms 3)}))

   (h4
     (respond/tablet
      {:padding  [[0 (ms 1)]]
       :font-size (ms 2)
       :line-height (ms 2)}))

   (h5
     (respond/tablet
      {:padding  [[0 (ms 1)]]
       :font-size (ms 1)
       :line-height (ms 1)}))

   (h6
     (respond/desktop
      {:padding  [[0 (ms 1)]]
       :font-size (ms 0)
       :line-height (ms 0)}))

   (large
     (respond/tablet
      {:padding  [[0 (ms 1)]]
       :font-size (ms -1)
       :line-height (ms -1)}))

   (medium
     (respond/tablet
      {:padding  [[0 (ms 1)]]
       :font-size (ms -2)
       :line-height (ms -2)}))

   (small
     (respond/tablet
      {:padding  [[0 (ms 1)]]
       :font-size (ms -3)
       :line-height (ms -3)}))))

(defstyles typesettings
  typography)

(def index
  typesettings)
