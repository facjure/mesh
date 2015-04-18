(ns examples.om.page
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [mesh.utils :as utils]
            [examples.om.components :as comp]
            [examples.om.styles :as styles]))

(enable-console-print!)

(def app-state
  (atom {:fluid nil
         :nested nil
         :baseline nil
         :content {:storyboard "Beautiful languages"}}))

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
      (html (view comp/fluid-grids)))))

(defn nested-widget [data owner]
  (reify
    om/IWillMount
    (will-mount [_]
      (println "Nested widget mounting"))
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Render!")
      (html (view comp/nested-grids)))))

(defn baseline-widget [data owner]
  (reify
    om/IWillMount
    (will-mount [_]
      (println "Baseline widget mounting"))
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Render!")
      (html (view comp/baseline-grid)))))

(defn storyboard-widget [data owner]
  (reify
    om/IWillMount
    (will-mount [_]
      (println "Storyboard widget mounting"))
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Render!")
      (html (view (comp/storyboard ()))))))

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
      (html (section comp/fluid-grids)))))

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

#_(mount fluid-widget "fluid")

#_(mount nested-widget "nested")

#_(mount baseline-widget "baseline")

(mount storyboard-widget "storyboard")

#_(unmount "fluid")

(utils/insert-style styles/index)
