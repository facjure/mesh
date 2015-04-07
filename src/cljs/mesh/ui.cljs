(ns mesh.ui
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(def app-state (atom {:foo "bar"}))

(defn widget [data owner]
  (reify
    om/ICheckState
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
      (html
       [:div
        [:h2 "Hello world!"]
        [:p (str "Count: " count)]
        [:button {:on-click #(do
                               (println "I can read!" (:foo data))
                               (om/update-state! owner :count inc))}
         "Bump!"]
        [:button {:on-click #(do
                               (println "I can also read!" (:foo data))
                               (om/update-state! owner :count identity))}
         "Do Nothing"]]))))

(om/root widget app-state
         {:target (.getElementById js/document "app")})
