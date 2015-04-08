(ns om.styles
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt]]
            [mesh.mixins :as mixins]
            [mesh.typography :refer [typeset]]
            [mesh.grid :as grid]))

(def gutter (px 20))

(def ff-serif ["\"EB Garamond\"" "serif"])
(def ff-sans ["\"Fira Sans\"" "sans-serif"])
(def ff-mono ["\"Source Code Pro\"" "monospace"])

(defstyles typesetting
  (list (typeset ff-serif ff-sans ff-mono)))

(defstyles grids
  (list mixins/alignments
        (grid/initialize ".grid" gutter)
        (grid/create ".grid")
        (grid/wrap-widths 978)
        (mixins/clearfix ".grid")
        (grid/fit-images ".unit")
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        (grid/respond-small (px 568) gutter)
        (grid/respond-medium (px 1180))))

(def index
  (merge grids typesetting))
