(ns examples.hello.page
  (:require [brutha.core :as react]
            [sablono.core :as html :refer-macros [html]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [mesh.core.dom :as gardom]
            [mesh.typography :as typo :refer [typeset]]
            [mesh.grid :as grid]))

(def dummy-text
  "Pellentesque habitant morbi tristique senectus et netus et malesuada
    fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies
    eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas
    semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo")

(defn grids [content]
  (html
   [:div
    [:header "A Minimal Grid"]
    [:div {:class "grid"}
     [:div {:class "col-2-3"}
      [:div {:class "module"}
       [:h3 "2/3"]
       [:p content]]]
     [:div {:class "col-1-3"}
      [:div {:class "module"}
       [:h3 "1/3"]
       [:p content]]]]
     [:div {:class "grid grid-pad"}
      [:div {:class "col-2-3"}
       [:div {:class "module"}
        [:h3 "2/3 (opt-in Outside Padding)"]
        [:p content]]]
      [:div {:class "col-1-3"}
       [:div {:class "module"}
        [:h3 "1/3"]
        [:p content]]]]
     [:div {:class "grid grid-pad"}
      [:div {:class "col-1-8"}
       [:div {:class "module"}
        [:h3 "1/8"]]]
      [:div {:class "col-1-8"}
       [:div {:class "module"}
        [:h3 "1/8"]]]
      [:div {:class "col-1-8"}
       [:div {:class "module"}
        [:h3 "1/8"]]]
      [:div {:class "col-1-8"}
       [:div {:class "module"}
        [:h3 "1/8"]]]
      [:div {:class "col-1-8"}
       [:div {:class "module"}
        [:h3 "1/8"]]]
      [:div {:class "col-1-8"}
       [:div {:class "module"}
        [:h3 "1/8"]]]
      [:div {:class "col-1-8"}
       [:div {:class "module"}
        [:h3 "1/8"]]]
      [:div {:class "col-1-8"}
       [:div {:class "module"}
        [:h3 "1/8"]]]]
     [:div {:class "grid grid-pad"}
      [:div {:class "col-1-4"}
       [:div {:class "module"}
        [:h3 "1/4"]]]
      [:div {:class "col-1-2"}
       [:div {:class "module"}
        [:h3 "1/2"]]]
      [:div {:class "col-1-4"}
       [:div {:class "module"}
        [:h3 "1/4"]]]]]))

(def styles
  (css
   (list
    (grid/create-minimal-grid ".grid" (px 20)))))

(react/mount (grids dummy-text) (js/document.getElementById "hello"))

(gardom/insert-styles styles)
