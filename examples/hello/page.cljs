(ns examples.hello.page
  (:require  [om.core :as om :include-macros true]
             [om.dom :as dom :include-macros true]
             [sablono.core :as html :refer-macros [html]]
             [garden.core :refer [css]]
             [garden.units :as u :refer [px pt em]]
             [garden.color :as color :refer [hsl rgb]]
             [mesh.respond :as respond :refer [breakpoints]]
             [mesh.dom :as mesh-dom]
             [mesh.mixins :as mixins]
             [mesh.typography :as typo :refer [typeset]]
             [mesh.grid :as grid]))

(enable-console-print!)

(defonce app-state
  (atom {:msg "hello world"}))

(def styles
  (let [gutter (px 20)]
    (list mixins/alignments
          (grid/initialize ".grid" gutter)
          (grid/create ".grid")
          (grid/wrap-widths 978)
          (mixins/clearfix ".grid")
          (mixins/fit-images ".unit")
          (grid/create-nested-units)
          (grid/nuke-gutters-and-padding))))

(defn baseline-grid []
  [:div
   [:div {:class "grid"}
    [:div {:class "unit one-half"}
     [:h2 "Baseline Grids"]
     [:p
      "Lorem ipsum dolor sit amet, consectetur adipisicing elit.
       Iure atque, optio fuga voluptates officia alias excepturi vero
       consectetur numquam sunt, cupiditate ad vitae facilis amet offm"]
     [:p
      "Lorem ipsum dolor sit amet, consectetur adipisicing elit.
       Iure atque, optio fuga voluptates officia alias excepturi verom"]
     [:p
      "Lorem ipsum dolor sit amet, consectetur adipisicing elit.
       Iure atque, optio fuga voluptates officia alias excepturi vero
       consectetur numquam sunt, cupiditate ad vitae facilis amet officiis
       debitis dignissimos!  Id, quisquam? Lorem"]]
    [:div {:class "unit one-half"}
     [:p
      "Lorem ipsum dolor sit amet, consectetur adipisicing elit.
       Iure atque, optio fuga voluptates officia alias excepturi vero
       consectetur numquam sunt, cupiditate ad vitae facilis amet offm"]]
    [:p
     "Lorem ipsum dolor sit amet, consectetur adipisicing elit.
       Iure atque, optio fuga voluptates officia alias excepturi verom"]
    [:p
     "Lorem ipsum dolor sit amet, consectetur adipisicing elit.
       Iure atque, optio fuga voluptates officia alias excepturi vero
       consectetur numquam sunt, cupiditate ad vitae facilis amet officiis
       debitis dignissimos!  Id, quisquam? Lorem"]]])

(defn component [data owner]
  (reify
    om/IRenderState
    (render-state [_ {:keys [msg]}]
      (println msg)
      (html
       [:section {:class "demo"}
        baseline-grid]))))

(defn mount [widget id]
  (om/root
   widget
   app-state
   {:target (.getElementById js/document id)}))

#_(mount baseline-widget "baseline")

#_(mesh-dom/insert-styles (css styles))
