(ns examples.om.page
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [mesh.utils :as utils]
            [examples.om.styles :as styles]))

(enable-console-print!)

(def app-state (atom {:foo "bar"}))

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

(defn nested-grids []
  [:div
   [:div {:class "grid"}
    [:div {:class "unit one-quarter align-right center-on-mobile"}
     [:h2 "Nested Grids"]
     [:p "Nested Grids also work, but markup gets crazy pretty soon!"]]
    [:div {:class "unit three-quarters"}
     [:div {:class "grid"}
      [:div {:class "unit whole"}
       [:p {:class "align-center"} "Gridcenption!"]]]
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

(defn view [grid-component]
  [:section {:class "demo"}
   (grid-component)])

(defn empty-widget [data owner]
  (reify
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Unmounting!")
      (html [:div ""]))))

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
    om/IWillUnmount
    (will-unmount [_]
      (println "Hello widget unmounting"))
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Render!")
      (html (view nested-grids)))))

(defn diy-widget [data owner]
  (reify
    om/IInitState
    (init-state [_]
      {:count 0})
    om/IWillMount
    (will-mount [_]
      (println "Hello widget mounting"))
    om/IWillUnmount
    (will-unmount [_]
      (println "Hello widget unmounting"))
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Render!")
      (html (section fluid-grids)))))

(defn mount [widget id]
  (om/root
   widget
   app-state
   {:target (.getElementById js/document id)}))

(defn unmount [id]
  (om/root
   empty-widget
   app-state
   {:target (.getElementById js/document id)}))

(mount fluid-widget "fluid")

(mount nested-widget "nested")

#_(unmount "fluid")

(utils/insert-stylesheet styles/index)
