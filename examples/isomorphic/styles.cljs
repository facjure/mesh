(ns examples.isomorphic.styles
  (:require [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [mesh.respond :as respond :refer [breakpoints]]
            [mesh.mixins :as mixins]
            [mesh.typography :as typo]
            [mesh.grid :as grid]))

;; Generic Grids

(def gutter (px 20))

(def typesetting
  (list
   #_(typo/typeset (:garamond typo/font-families)
              (:optima typo/font-families)
              (:sourcecode-pro typo/font-families))
   (typo/typeset-html typo/defaults :golden)))

(def grids
  (list mixins/alignments
        #_(typo/baseline-overlay (:aquamarine color/color-name->hex) 2)
        (grid/create ".grid" gutter)
        (grid/wrap-widths 978)
        (mixins/clearfix ".grid")
        (mixins/fit-images ".unit")
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        (grid/respond-small (:mobile breakpoints) gutter)
        (grid/respond-medium (:tablet breakpoints))))

(def index
  (css (merge grids typesetting)))
