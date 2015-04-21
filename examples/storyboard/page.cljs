(ns examples.storyboard.page
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [garden.core :refer [css]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [mesh.dom :as mesh-dom :refer [insert-styles]]
            [mesh.utils :as utils]
            [mesh.images :as images]
            [mesh.respond :as respond :refer [breakpoints]]
            [mesh.grid :as grid]
            [mesh.typography :as typo :refer [typeset]]
            [examples.storyboard.styles :as styles]))

#_(enable-console-print!)

(defonce app-state
  (atom {:content
         {:title "Thinking Clojurescript"
          :issue "Issue No #1"
          :publisher "The Facjure Review"
          :logo ""
          :editorial "Object Oriented programming as a paradigm has many real
                   benefits but one of the worst plagues it has inflicted on
                   programming culture is obscuring data. Functional programming
                   is not a silver bullet but its emphasis on unadorned data is
                   a guiding light. No models."
          :interviews ["David Nolen, " "Bruce Hoffman, " "Joel Holdbrooks"]
          :developer-pic ""
          :essays ["“A meditation in Edn” by Priyatam Mudivarti"]
          :quotes {:nolen-tweet "“We create things with our tools, and
                    our tools have consequences”"
                   :dev-quote "The Functional Final Frontier in Clojurescript"}}
         :interviewer "nolen.png"
         :styles {:font-size-base (em 1.5)
                  :line-height-base (em 1.45)
                  :min-width (px 400)
                  :max-width (px 1200)
                  :min-font (px 12)
                  :max-font (px 24)
                  :body-font ["Alegreya" "Baskerville" "Georgia" "Times" "serif"]
                  :body-font-weight 400
                  :header-font (:firasans typo/font-families)
                  :header-font-weight 600
                  :breakpoints {:mobile (px 480)
                                :tablet (px 720)
                                :desktop (px 960)}
                  :scale 1}
         :data {:foo "Foo"
                :bar "Bar"}}))

(def new-state
  {:content
   {:title "Thinking Ruby"
    :issue "Issue No #87 2008"
    :publisher "The Ruby Review"
    :logo "http://placekitten.com/404/404"
    :editorial "Dynamic programming Redux"
    :interviews ["DHH " "Jason, " "Joe"]
    :developer-pic "/img/ruby.png"
    :quotes {:dev-quote "It’s a lot harder to pull your head up and ask why."
             :nolen-best "The Functional Final Frontier in Clojurescript"}}
   :styles {:font-size-base (em 1.5)
            :line-height-base (em 1.45)
            :min-width (px 400)
            :max-width (px 1200)
            :min-font (px 12)
            :max-font (px 24)
            :body-font ["Alegreya" "Baskerville" "Georgia" "Times" "serif"]
            :body-font-weight 400
            :header-font (:firasans typo/font-families)
            :header-font-weight 600
            :breakpoints {:mobile (px 480)
                          :tablet (px 720)
                          :desktop (px 960)}
            :scale 1}
   :data {:foo "Foo"
          :bar "Bar"}})

(def history
  (atom [@app-state]))

(add-watch app-state :history
           (fn [_ _ old new]
             #_(println old new history)
             (when-not (= (last @history) new)
               (swap! history conj new))))

(defn undo! []
  (when (> (count @history) 1)
    (swap! history pop)
    (reset! app-state (last @history))))

;; Components

(defn- empty-comp [data owner]
  (om/component
    (html [:div ""])))

(defn undo-button []
  (dom/div nil
    (dom/button #js {:onClick #(undo!)}
      "Undo")))

(defn- image [id]
  [:img {:src (str "/img/" id)}] )

(defn view [{:keys [title issue publisher logo developer-pic
                    interviews essays quotes]
             :as content}]
  [:section
   [:div {:class "grid"}
    [:div {:class "unit whole align-center"}
     [:h1 publisher]]]
   [:div {:class "grid"}
    [:div {:class "unit golden-small"}
     [:h3 issue]
     [:h3 title]]
    [:div {:class "unit golden-large"}
     [:div {:class "unit one-third"} [:img {:src logo}]]]]
   [:div {:class "grid"}
    [:div {:class "unit half"}
     [:p (:editorial content)]]
    [:div {:class "unit half"}
     [:h5 "Interviews"]
     [:p (map str interviews)]
     [:h5 "Essays"]
     [:p (map str essays)]]]
   [:div {:class "grid"}
    [:div {:class "unit one-third" }
     [:blockquote.oval-quotes' ;; oval-thought-border
      [:p (get-in content [:quotes :dev-quote])]]]
    [:div {:class "unit two-thirds"} ]]
   [:div {:class "grid"}
    #_[:div {:class "unit golden-small"}]
    [:div {:class "unit golden-large photo-medium"} [:img {:src developer-pic}]
     [:p (get-in content [:quotes :dev-quote])]]]])

(defn component [data owner]
  (reify
    om/IWillMount
    (will-mount [_]
      (println "widget mounting")
      ;; Insert fine-grain component styles
      (insert-styles styles/page-styles))
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Render!")
      (html
       [:section {:class "demo"}
        (view (:content data))
        (undo-button)]))
    om/IWillUnmount
    (will-unmount [_]
      (println "widget unmounting")
      ;; remove styles
      )))

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

(mount component "storyboard")


;; Demos

#_(unmount "storyboard")

#_(swap! app-state assoc-in [:content :logo] "/img/logo.png")
#_(swap! app-state assoc-in [:content :developer-pic] "/img/cljs.png")

#_(swap! app-state assoc-in [:content :quotes :nolen-tweet]
         "Clojurescript, Lisp's revenge")

#_(reset! app-state new-state)
#_(undo!)
