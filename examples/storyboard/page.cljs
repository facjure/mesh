(ns examples.storyboard.page
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [mesh.dom :as mesh-dom]
            [mesh.mixins :as mixins]
            [mesh.respond :as respond :refer [breakpoints]]
            [mesh.grid :as grid]
            [mesh.typography :as typo :refer [typeset]]))

(enable-console-print!)

;; STATE ;;

(defonce app-state
  (atom {:styles {}
         :content {:storyboard "Crazy languages"}}))

(defn- empty-comp [data owner]
  (om/component
    (html [:div ""])))

(defn- image [id]
  [:img {:src (str "/img/" id)}] )

;; COMPONENTS ;;

(defn view [content]
  [:section
   [:div {:class "grid"}
    [:div {:class "unit whole center photo"}
     [:div.photo (image "ringo.png") ]
     [:p content]]]
   [:div {:class "grid"}
    [:div {:class "unit one-third"}]
    [:div {:class "unit one-third photo"} (image "clj.png")]
    [:div {:class "unit one-third photo"}
     [:div "hello"]]]
   [:div {:class "grid"}
    [:div {:class "unit one-third photo"} (image "python.png")]
    [:div {:class "unit one-third photo"} (image "ruby.png")]
    [:div {:class "unit one-third photo"} (image "cljs.png")]]])

(defn component [data owner]
  (reify
    om/IWillMount
    (will-mount [_]
      (println "Storyboard widget mounting"))
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Render!")
      (html
       [:section {:class "demo"}
        (view (get-in data [:content :storyboard]))]))))

(defn mount
  ([widget id]
   (mount widget id nil))
  ([widget id style]
   (om/root
    component
    app-state
    {:target (.getElementById js/document id)})))

(defn unmount [id]
  (om/root
   empty-comp
   app-state
   {:target (.getElementById js/document id)}))

;; STYLES ;;

(def settings
  {:min-width (px 400)
   :max-width (px 1200)
   :min-font (px 12)
   :max-font (px 32)
   :body-font (:garamond typo/font-families)
   :body-font-weight 400
   :header-font (:garamond typo/font-families)
   :header-font-weight 600
   :scale 2})

(def fonts {:font-size-base (em 1.5)
            :line-height-base (em 1.45)
            :ff-serif ["EB Garamond" "Serif"]
            :ff-sans ["Fira Sans" "sans-serif"]
            :ff-mono ["Source Code Pro" "monospace"]})

(defn body-copy [declarations]
  [:div :p])

(def typography
  (-> body-copy
      (typo/scale-type settings)
      (typo/make-serifs typo/font-families)))

(def grid-styles
  (list mixins/alignments
        (grid/create ".grid" (px 30))
        (grid/wrap-widths 1200)
        (mixins/clearfix ".grid")
        (mixins/fit-images ".unit")
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        (grid/respond-small (:mobile breakpoints) (px 20))
        (grid/respond-medium (:tablet breakpoints))))

(def styles
  (css (merge grid-styles (typography fonts))))

(defn empty-comp [data owner]
  (om/component
    (html [:div ""])))

#_(mount component "storyboard")

#_(unmount "storyboard")

(mesh-dom/insert-styles styles)
