(ns examples.storyboard.styles
  (:require  [sablono.core :as html :refer-macros [html]]
             [garden.core :refer [css]]
             [garden.units :as u :refer [px pt em]]
             [garden.color :as color :refer [hsl rgb]]
             [mesh.dom :as mesh-dom :refer [insert-styles]]
             [mesh.utils :as utils]
             [mesh.images :as images]
             [mesh.respond :as respond :refer [breakpoints]]
             [mesh.grid :as grid]
             [mesh.typography :as typo :refer [typeset]]))

(def serif ["Alegreya" "Baskerville" "Georgia" "Times" "serif"])
(def sans ["\"Open Sans\"" "Avenir" "Helvetica" "sans-serif"])
(def mono ["Inconsolata" "Menlo" "Courier" "monospace"])

(def typography-base
  (typeset serif sans mono))

(defn body-copy [declarations]
  [:div :p])

(defn headings [declarations]
  [:h1 :h2])

(defn sub-headings [declarations]
  [:h3 :h4])

(def grid-styles
  (list (grid/create ".grid" (px 30))
        (grid/wrap-widths 960)
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        (grid/respond-small (:mobile breakpoints) (px 20))
        (grid/respond-medium (:tablet breakpoints))
        (images/fit-images ".unit")
        utils/alignments))

(def page-styles
  (css (merge
        (images/photos)
        grid-styles
        typography-base)))
