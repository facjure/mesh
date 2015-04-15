(ns om.styles
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt vw]]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [mesh.mixins :as mixins]
            [mesh.typography :as typo :refer [typeset]]
            [mesh.grid :as grid]))

(def ff-serif ["\"EB Garamond\"" "serif"])
(def ff-sans ["\"Fira Sans\"" "sans-serif"])
(def ff-mono ["\"Source Code Pro\"" "monospace"])

(def gutter (px 15))

(def breakpoints
  {:mobile (px 480)
   :tablet (px 960)
   :laptop (px 1440)
   :monitor (px 1920)})

(defstyles typesetting
  (list
   #_(typeset ff-serif ff-sans ff-mono)
   (typo/typeset-html typo/defaults)))

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

(typo/typeset-html typo/defaults)

;;(- (px 100) (px 90))

