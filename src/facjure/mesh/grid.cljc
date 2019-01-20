(ns facjure.mesh.grid
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.core :refer [css]]
            [garden.units :as u :refer [px pt pc]]
            [garden.units :refer (px+ px* px- px-div em)]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-media]]
            [facjure.mesh.media :as media]
            [facjure.mesh.core :refer [border-box clearfix reset-padding]]))

(defn initialize [clazz gutter]
  "Define a unit within grid with a gutter in px"
  [[clazz :.unit border-box]
   [clazz {:display "block"
           :clear "both"}
    [:.unit {:float "left"
             :width "100%"
             :padding (/ gutter 2)}
     [:&:first-child {:padding-left gutter}]
     [:&:last-child {:padding-right gutter}]]]])

;; A simple Grid, based on on 1,2,4,8 columns
;; https://css-tricks.com/dont-overthink-it-grids

(defn create-minimal-grid [clazz pad]
  [[:* {:box-sizing "border-box"}]
   [clazz {:background "white"
           :margin [[0 0 pad 0]]}
    [:&:after {:content ""
               :display "table"
               :clear "both"}]
    ["[class*='col-']" {:float "left"
                        :padding-right pad
                        }]
    [:.col-1-3 {:width "33.33%"}]
    [:.col-2-3 {:width "66.66%"}]
    [:.col-1-2 {:width "50.00%"}]
    [:.col-1-4 {:width "25.00%"}]
    [:.col-1-8 {:width "12.50%"}]
    [:.out-padding {:padding [[pad 0 pad pad pad]]}
     ["[class*='col-']:last-of-type" {:padding-right pad}]]]])

;; Fluid/Fractional Grids, with nesting

(defn create-nested-units []
  [:.unit
   [:.unit
    [:&:first-child {:padding-left 0}]
    [:&:last-child {:padding-right 0}]]
   [:.grid:first-child
    [:>
     [:.unit {:padding-top 0}]]]
   [:.grid:last-child
    [:>
     [:.unit {:padding-bottom 0}]]]])

(defn nuke-gutters-and-padding []
  [[:.unit.no-gutters reset-padding]
   [:.no-gutters
    [:.unit reset-padding]]])

(defn wrap-widths [width]
  [[:.wrap
    [:.grid {:max-width (px width)
             :margin "0 auto"}]]
   [:.grid.wrap
    {:max-width (px width)
     :margin "0 auto"}]])

(defn create-fractions [clazz]
  [[clazz
    [:.whole {:width "100%"}]]
   [clazz
    [:.half {:width "50%"}]]
   [clazz
    [:.one-third {:width "33.3332%"}]]
   [clazz
    [:.two-thirds {:width "66.6665%"}]]
   [clazz
    [:.one-fourth :.one-quarter {:width "25%"}]]
   [clazz
    [:.three-forths :.three-quarters {:width "75%"}]]
   [clazz
    [:.one-fifth {:width "20%"}]]
   [clazz
    [:.two-fifths {:width "40%"}]]
   [clazz
    [:.three-fifths {:width "60%"}]]
   [clazz
    [:.four-fifths {:width "80%"}]]
   [clazz
    [:.golden-small {:width "38.2716%"}]]
   [clazz
    [:.golden-large {:width "61.7283%"}]]])

(defn create [clazz gutter]
  (list (clearfix clazz)
        (initialize clazz gutter)
        (create-fractions clazz)))

(defn media-small [width gutter]
  (at-media {:screen true :max-width width}
            [".grid:not(.no-stacking-on-mobiles)"
             [:>
              [:.unit {:width "100% !important"
                       :padding-left gutter
                       :padding-right gutter}]]]
            [:.unit
             [:.grid
              [:.unit {:padding-left (px 0)
                       :padding-right (px 0)}]]]
            [:.center-on-mobiles {:text-align "center !important"}]
            [:.hide-on-mobiles {:display "none !important"}]))

(defn media-medium [width]
  (at-media {:screen true :min-width width}
            [:.wider
             [:.grid {:max-width width
                      :margin "0 auto"}]]))

;; Bootstrap-style Container=>Row=>Column Grids with 15px margins, and % offsets

(def container
  (list
   [:& clearfix]
   (media/mobile)
   (media/phablet)
   (media/tablet)
   (media/desktop)
   (media/hd)))

(defn offset [n]
  (let [m (min (max 0 n) 12)]
    [:&
     {:margin-left (pc (* 100 (/ n 12.0)))}]))

(defn col [n]
  (let [m (min (max 0 n) 12)]
    [:&
     {:float :left
      :position :relative
      :min-height (px 1)
      :padding-left (px 15)
      :padding-right (px 15)
      :width (pc (* 100 (/ m 12.0)))}]))

(def row
  [:&
   {:margin-right (px -15)
    :margin-left (px -15)}
   clearfix])

(defn block [& {:as opts}]
  [:&
   (when-let [c (:col opts)]
     (col c))
   (when-let [o (:offset opts)]
     (offset o))])
