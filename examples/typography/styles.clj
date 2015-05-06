(ns typography.styles
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstyles defrule]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em]]
            [garden.arithmetic :refer [+ - * /]]
            [mesh.utils :as utils]
            [mesh.respond :as respond]
            [mesh.typography :as typo :refer [typeset vr-block scales]]
            [mesh.typesetting :as typesetting :refer [scale-type make-serifs]]
            [mesh.grid :as grid]))

(def alegreya ["Alegreya" "Baskerville" "Georgia" "Times" "serif"])
(def sans ["\"Open Sans\"" "Avenir" "Helvetica" "sans-serif"])
(def mono ["Inconsolata" "Menlo" "Courier" "monospace"])

(def ms
  (let [f (typo/modular-scale-fn 16 3/4)]
    (fn [n]
      (px (f n)))))

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

(defstyles typography
  (body
   {:font-family alegreya})

  (homepage
   (h1
     (respond/desktop
      {:padding  [[0 (ms 1)]]
       :font-size (ms 5)
       :line-height (ms 5)}))

   (h2
     (respond/desktop
      {:padding  [[0 (ms 1)]]
       :font-size (ms 4)
       :line-height (ms 4.4)}))

   (h3
     (respond/desktop
      {:padding  [[0 (ms 1)]]
       :font-size (ms 3)
       :line-height (ms 3)}))

   (h4
     (respond/desktop
      {:padding  [[0 (ms 1)]]
       :font-size (ms 2)
       :line-height (ms 2)}))

   (h5
     (respond/desktop
      {:padding  [[0 (ms 1)]]
       :font-size (ms 1)
       :line-height (ms 1)}))

   (h6
     (respond/desktop
      {:padding  [[0 (ms 1)]]
       :font-size (ms 0)
       :line-height (ms 0)}))

   (large
     (respond/desktop
      {:padding  [[0 (ms 1)]]
       :font-size (ms -1)
       :line-height (ms -1)}))

   (medium
     (respond/desktop
      {:padding  [[0 (ms 1)]]
       :font-size (ms -2)
       :line-height (ms -2)}))

   (small
     (respond/desktop
      {:padding  [[0 (ms 1)]]
       :font-size (ms -3)
       :line-height (ms -3)}))))

(defstyles typesettings
  (list typography))

(def index
  typesettings)
