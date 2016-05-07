(ns grids.styles
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [mesh.core.images :as images]
            [mesh.core.respond :as respond :refer [breakpoints]]
            [mesh.core.resets :as resets]
            [mesh.core.utils :as utils]
            [mesh.typography :as typo :refer [typeset vr-block scale-type make-serifs]]
            [mesh.grid :as grid]))

(def gutter (px 20))

(def alegreya ["Alegreya" "Baskerville" "Georgia" "Times" "serif"])
(def sans ["\"Open Sans\"" "Avenir" "Helvetica" "sans-serif"])
(def mono ["Inconsolata" "Menlo" "Courier" "monospace"])

(defstyles printer
  #_(typeset serif
             (:optima typo/font-families)
             (:sourcecode-pro typo/font-families))
  (typo/typeset-html typo/defaults :golden))

(defstyles reset
  resets/reset-common-selectors)
#_(typo/baseline-overlay (:cadetblue color/color-name->hex) 0)

(defstyles grids
  utils/alignments
  (grid/create ".grid" gutter)
  (grid/wrap-widths 978)
  (grid/create-nested-units)
  (grid/nuke-gutters-and-padding)
  (grid/respond-small (:mobile breakpoints) gutter)
  (grid/respond-medium (:tablet breakpoints)))

(def bp
  (let [grid-title (defrule grid-title :.grid-title)]
    (respond/iphone-5
     (grid-title {:font-size 10}))))

(def index
  (merge grids printer bp reset))
