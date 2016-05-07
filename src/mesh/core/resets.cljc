(ns mesh.core.resets
  (:require [garden.core :refer [css]]))

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

