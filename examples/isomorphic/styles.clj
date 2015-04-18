(ns isomorphic.styles
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [mesh.mixins :as mixins]
            [mesh.respond :as respond :refer [breakpoints]]
            [mesh.typography :as typo :refer [typeset vr-block scale-type make-serifs]]
            [mesh.grid :as grid]))

;; Basic

(def gutter (px 15))

(defstyles typesetting
  (list
   (typeset (:garamond typo/font-families)
              (:optima typo/font-families)
              (:sourcecode-pro typo/font-families))
   #_(typo/typeset-html typo/defaults :golden)))

(defstyles grids
  (list mixins/alignments
        #_(typo/baseline-overlay (:cadetblue color/color-name->hex) 0)
        (grid/create ".grid" gutter)
        (grid/wrap-widths 978)
        (mixins/clearfix ".grid")
        (mixins/fit-images ".unit")
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        (grid/respond-small (:mobile breakpoints) gutter)
        (grid/respond-medium (:tablet breakpoints))))

(def index
  (merge grids typesetting))

;; Ring-like API

(def settings
  {:min-width (px 400)
   :max-width (px 1200)
   :min-font (px 12)
   :max-font (px 32)
   :body-font (:garamond typo/font-families)
   :body-font-weight 400
   :header-font (:garamond typo/font-families)
   :header-font-weight 600
   :header-color "#111"
   :scale 1.5})

(def fonts {:font-size-base (em 1.5)
            :line-height-base (em 1.45)
            :ff-serif ["EB Garamond" "Serif"]
            :ff-sans ["Fira Sans" "sans-serif"]
            :ff-mono ["Source Code Pro" "monospace"]})

(defn headings [declarations]
  [:h1 :h2 :h3 :header declarations])

(defn sub-headings [declarations]
  [:h4 :h5 :h6 declarations])

(defn body-copy [declarations]
  [:section :article :p])

(def typography
  (-> headings
      (scale-type settings)
      (make-serifs typo/font-families)))

#_(def index
  (typography fonts))
