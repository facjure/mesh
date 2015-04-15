(ns examples.om.styles
  (:require [garden.core :refer [css]]
            [garden.units :as u :refer [px pt]]
            [mesh.mixins :as mixins]
            [mesh.typography :as typo :refer [typeset]]
            [mesh.grid :as grid]))

(def gutter (px 20))

(def ff-serif ["\"EB Garamond\"" "serif"])
(def ff-sans ["\"Fira Sans\"" "sans-serif"])
(def ff-mono ["\"Source Code Pro\"" "monospace"])

(def typesetting
  (list (typeset ff-serif ff-sans ff-mono)))

(def grids
  (list mixins/alignments
        (grid/initialize ".grid" gutter)
        (grid/create ".grid")
        (grid/wrap-widths 978)
        (mixins/clearfix ".grid")
        (mixins/fit-images ".unit")
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        (grid/respond-small (px 568) gutter)
        (grid/respond-medium (px 1180))))

(def index
  (css (merge grids typesetting)))
