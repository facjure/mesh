(ns isomorphic.styles
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [mesh.utils :as utils]
            [mesh.images :as images]
            [mesh.respond :as respond :refer [breakpoints]]
            [mesh.typography :as typo :refer [typeset vr-block scale-type make-serifs]]
            [mesh.grid :as grid]))

;; Basic

(def gutter (px 15))

(def alegreya ["Alegreya" "Baskerville" "Georgia" "Times" "serif"])
(def sans ["\"Open Sans\"" "Avenir" "Helvetica" "sans-serif"])
(def mono ["Inconsolata" "Menlo" "Courier" "monospace"])

(defstyles typesetting
  (list
   #_(typeset serif
              (:optima typo/font-families)
              (:sourcecode-pro typo/font-families))
   (typo/typeset-html typo/defaults :golden)))

(defstyles grids
  (list utils/alignments
        #_(typo/baseline-overlay (:cadetblue color/color-name->hex) 0)
        (grid/create ".grid" gutter)
        (grid/wrap-widths 978)
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        (grid/respond-small (:mobile breakpoints) gutter)
        (grid/respond-medium (:tablet breakpoints))))

(def index
  (merge grids typesetting))

;; Testing DSL

(def settings
  {:min-width (px 400)
   :max-width (px 1200)
   :min-font (px 12)
   :max-font (px 32)
   :body-font alegreya
   :body-font-weight 400
   :header-font (:garamond typo/font-families)
   :header-font-weight 600
   :header-color "#111"
   :breakpoints {:mobile (px 480)
                 :tablet (px 720)
                 :laptop (px 960)
                 :monitor (px 1920)}})

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

(def index
  (merge grids
         (typography fonts)))
