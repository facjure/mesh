(ns facjure.mesh.core
  (:require [garden.core :refer [css]]))

;; Resets -----

(defn clearfix [clazz]
  [clazz {:*zoom 1}
   [:&:before :&:after {:display "table"
                        :content " "
                        :line-height 0}]
   [:&:after {:clear "both"}]])

(def reset-common-selectors
  [:h1 :h2 :h3 :h4 :h5 :h6
   :p :blockquote :pre
   :dl :dt :dd :ol :ul :li
   :fieldset :form :label :legend
   :th :td
   :artcle :section :aside :figure :footer :header
   :hgroup :menu :nav :section
   {:margin 0
    :padding 0
    :border 0}])

(def reset-padding
  {:padding "0 !important"})

(def alignments
  [[:.align-center {:text-align "center"}]
   [:.align-left {:text-align "left"}]
   [:.align-right {:text-align "right"}]
   [:.pull-left {:float "left"}]
   [:.pull-right {:float "right"}]])

(def border-box
  {:box-sizing "border-box"})

(def content-box
  {:box-sizing "content-box"})


;; Math ------

#?(:cljs
   (defn viewport-w []
     (.. js/document -documentElement -clientWidth)))

#?(:cljs
   (defn viewport-h []
     (.. js/document -documentElement -clientHeight)))

#?(:clj
   (defn pow [base e]
     (.pow Math base e)))

#?(:cljs
   (defn pow [base e]
     (.pow js/Math base e)))

(defn whole-number? [n]
    (= n (Math/floor n)))


;; Dom -----

#?(:cljs
   (defn insert-styles [styles]
     "Inserts Stylesheet into document head"
     (let [el (.createElement js/document "style")
           node (.createTextNode js/document styles)]
       (.appendChild el node)
       (.appendChild (.-head js/document) el)
       el)))

