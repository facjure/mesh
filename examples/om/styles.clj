(ns om.styles
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt vw]]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [mesh.mixins :as mixins]
            [mesh.respond :as respond :refer [breakpoints]]
            [mesh.typography :as typo :refer [typeset]]
            [mesh.grid :as grid]))

(def gutter (px 15))

(defstyles typesetting
  (list
   #_(typeset (:garamond typo/font-families)
              (:optima typo/font-families)
              (:sourcecode-pro typo/font-families))
   (typo/typeset-html typo/defaults :golden)))

(defstyles grids
  (list mixins/alignments
        (typo/overlay (:aquamarine color/color-name->hex) 2)
        (grid/initialize ".grid" gutter)
        (grid/create ".grid")
        (grid/wrap-widths 978)
        (mixins/clearfix ".grid")
        (mixins/fit-images ".unit")
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        (grid/respond-small (:mobile breakpoints) gutter)
        (grid/respond-medium (:tablet breakpoints))))

(def index
  (merge grids typesetting))
