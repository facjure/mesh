(ns examples.isomorphic.page
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [mesh.utils :as utils]
            [mesh.dom :as mesh-dom]
            [examples.isomorphic.styles :as styles]))

(enable-console-print!)

(defonce app-state
  (atom {:fluid nil
         :nested nil
         :baseline nil}))

(defn view [grid-component]
  [:section {:class "demo"}
   (grid-component)])

(defn fluid-grids []
  [:div
   [:h2 "Fluid/Fractional Grids"]
   [:div {:class "grid"}
    [:div {:class "unit whole"}
     [:pre ".whole"]]]
   [:div {:class "grid"}
    [:div {:class "unit half"}
     [:pre ".half"]]
    [:div {:class "unit half"}
     [:pre ".half"]]]
   [:div {:class "grid"}
    [:div {:class "unit one-third"}
     [:pre ".one third"]]
    [:div {:class "unit two-thirds"}
     [:pre ".two thirds"]]]
   [:div {:class "grid"}
    [:div {:class "unit one-quarter"}
     [:pre ".one-quarter"]]
    [:div {:class "unit three-quarters"}
     [:pre ".three-quarters"]]]
   [:div {:class "grid"}
    [:div {:class "unit one-fifth"}
     [:pre ".one-fifth"]]
    [:div {:class "unit four-fifths"}
     [:pre ".four-fifths"]]]
   [:div {:class "grid"}
    [:div {:class "unit two-fifths"}
     [:pre ".two-fifths"]]
    [:div {:class "unit three-fifths"}
     [:pre ".three-fifths"]]]
   [:div {:class "grid"}
    [:div {:class "unit golden-large"}
     [:pre ".golden-large"]]
    [:div {:class "unit golden-small"}
     [:pre ".golden-small"]]]])

;; TODO Refactor
(defn nested-grids []
  [:div
   [:div {:class "grid"}
    [:div {:class "unit one-quarter align-right center-on-mobile"}
     [:h2 "Nested Grids"]
     [:p "Nested Grids also work, but markup gets crazy pretty soon!"]]
    [:div {:class "unit three-quarters"}
     [:div {:class "grid"}
      [:div {:class "unit whole"}
       [:p {:class "align-center"} "Gridception!"]]]
     [:div {:class "grid"}
      [:div {:class "unit one-third"}
       [:pre "***"]]
      [:div {:class "unit two-thirds"}
       [:div {:class "grid"}
        [:div {:class "unit whole"}
         [:p {:class "align-center"} "Gridception!"]]]
       [:div {:class "grid"}
        [:div {:class "unit two-fifths"}
         [:pre "***"]]
        [:div {:class "unit three-fifths"}
         [:pre "***"]]]]]
     [:div {:class "grid"}
      [:div {:class "unit four-fifths"}
       [:div {:class "grid"}
        [:div {:class "unit whole"}
         [:p {:class "align-center"} "Gridception!"]]]
       [:div {:class "grid"}
        [:div {:class "unit one-quarter"}
         [:pre "***"]]
        [:div {:class "unit one-quarter"}
         [:pre "***"]]
        [:div {:class "unit one-quarter"}
         [:pre "***"]]
        [:div {:class "unit one-quarter"}
         [:pre "***"]]]]
      [:div {:class "unit one-fifth"} "***"]]]]])

(defn fluid-widget [data owner]
  (reify
    om/IWillMount
    (will-mount [_]
      (println "Fluid widget mounting"))
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Render!")
      (html (view fluid-grids)))))

(defn nested-widget [data owner]
  (reify
    om/IWillMount
    (will-mount [_]
      (println "Nested widget mounting"))
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Render!")
      (html (view nested-grids)))))

(defn empty-widget [data owner]
  (reify
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Unmounting!")
      (html [:div ""]))))

(defn mount
  ([widget id]
   (mount widget id nil))
  ([widget id style]
   (om/root
    widget
    app-state
    {:target (.getElementById js/document id)})))

(defn unmount [id]
  (om/root
   empty-widget
   app-state
   {:target (.getElementById js/document id)}))

(mount fluid-widget "fluid")

#_(mount nested-widget "nested")

#_(unmount "nested")

#_(mesh-dom/insert-styles styles/index)
