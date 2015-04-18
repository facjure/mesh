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
  (atom {:msg "Hello Garden"}))

(def styles
  (css
   (list
    (grid/create-minimal-grid ".grid" (px 20)))))

(defn component [data owner]
  (reify
    om/IRenderState
    (render-state [_ {:keys [msg]}]
      (println msg)
      (html [:h1 (:msg data)]))))

(defn mount [widget id]
  (om/root
   widget
   app-state
   {:target (.getElementById js/document id)}))

#_(mount component "hello")

#_(mesh-dom/insert-styles styles)
