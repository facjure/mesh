(ns examples.isomorphic.styles
  (:require [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [mesh.respond :as respond :refer [breakpoints]]
            [mesh.utils :as utils]
            [mesh.images :as images]
            [mesh.typography :as typo]
            [mesh.grid :as grid]))

;; Generic Grids

(def gutter (px 20))

(def defaults
  {:line-height-ratio 1.5
   :header-ratio (:golden scales)
   :min-width (px 480)
   :max-width (px 960)
   :min-font (px 12)
   :max-font (px 28)
   :body-color "#666"
   :body-font (:garamond font-families)
   :body-font-weight 400
   :header-font (:garamond font-families)
   :header-font-weight 600
   :header-color "#111"
   :breakpoints {:mobile (px 480)
                 :tablet (px 720)
                 :laptop (px 960)}})

(def typesetting
  (list
   (typo/typeset-html defaults :golden)))

(def grids
  (list utils/alignments
        (grid/create ".grid" gutter)
        (grid/wrap-widths 978)
        (images/fit-images ".unit")
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        (grid/respond-small (:mobile breakpoints) gutter)
        (grid/respond-medium (:tablet breakpoints))))

(def index
  (css (merge grids typesetting)))
