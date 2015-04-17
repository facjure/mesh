(ns examples.om.components
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [mesh.utils :as utils]
            [examples.om.styles :as styles]))

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

(defn baseline-grid []
  [:div
   [:div {:class "grid"}
    [:div {:class "unit one-half align-right"}
     [:h2 "Baseline Grids"]
     [:p
      "Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
Iure atque, optio fuga voluptates officia alias excepturi vero consectetur 
numquam sunt, cupiditate ad vitae facilis amet officiis debitis dignissimos!
 Id, quisquam? Lorem"]]
    [:p
      "Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
Iure atque, optio fuga voluptates officia alias excepturi vero consectetur 
numquam sunt, cupiditate ad vitae facilis amet officiis debitis dignissimos!
 Id, quisquam? Lorem"]
    [:p
      "Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
Iure atque, optio fuga voluptates officia alias excepturi vero consectetur 
numquam sunt, cupiditate ad vitae facilis amet officiis debitis dignissimos!
 Id, quisquam? Lorem"]]])
