(ns examples.typography.page
  (:refer-clojure :exclude [+ - * /])
  (:require  [om.core :as om :include-macros true]
             [om.dom :as dom :include-macros true]
             [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(defonce app-state
  (atom {:msg "The quick brown fox jumps over the lazy dog"}))

(defn component [data owner]
  (reify
    om/IRenderState
    (render-state [_ {:keys [msg]}]
      (html
       [:section {:class "home"}
        [:h1 (:msg data)]
        [:h2 (:msg data)]
        [:h3 (:msg data)]
        [:h4 (:msg data)]
        [:h5 (:msg data)]
        [:h6 (:msg data)]
        [:p.large (:msg data)]
        [:p.medium (:msg data)]
        [:p.small (:msg data)]]))))

(defn mount [widget id]
  (om/root
   widget
   app-state
   {:target (.getElementById js/document id)}))

#_(mount component "typography")
