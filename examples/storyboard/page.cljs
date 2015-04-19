(ns examples.storyboard.page
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [mesh.dom :as mesh-dom]
            [mesh.utils :as utils]
            [mesh.images :as images]
            [mesh.respond :as respond :refer [breakpoints]]
            [mesh.grid :as grid]
            [mesh.typography :as typo :refer [typeset]]))

(enable-console-print!)

;; STATE ;;

(defonce app-state
  (atom {:styles {}
         :content {:header "On the Rocks"
                   :subheading "Clojure/West, Portland"
                   :quotes {:hickey "a system made out of genuinely simple
                            parts, is going to be able to affect the
                            greatest change with the least work."
                            :nolen "ClojureScript: Lisp's Revenge"
                            :rossum "If the language is any good, your users are
                            going to take it to places where you never thought
                            it would be taken."
                            :ruby "It’s a lot harder to pull your head up and ask why."}}}))

(defn- empty-comp [data owner]
  (om/component
    (html [:div ""])))

(defn- image [id]
  [:img {:src (str "/img/" id)}] )

;; COMPONENTS ;;

(defn view [content]
  [:section
   [:div {:class "grid"}
    [:div {:class "unit whole align-left"}
     [:p content]]
    [:div {:class "unit whole photo"}
     [:div.photo (image "clj-com.png") ]
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
        (view (get-in data [:content :header]))]))))

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
  (list utils/alignments
        (grid/create ".grid" (px 30))
        (grid/wrap-widths 1200)
        (images/fit-images ".unit")
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        (grid/respond-small (:mobile breakpoints) (px 20))
        (grid/respond-medium (:tablet breakpoints))))

(def styles
  (css
   (merge
    (images/photos)
    grid-styles
    (typography fonts))))

(defn empty-comp [data owner]
  (om/component
    (html [:div ""])))

(mount component "storyboard")

#_(unmount "storyboard")

(mesh-dom/insert-styles styles)
