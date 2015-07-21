(ns mesh.dom
  (:require [garden.core :refer [css]]))

#?(:cljs
  (defn insert-styles [styles]
    "Inserts Stylesheet into document head"
    (let [el (.createElement js/document "style")
          node (.createTextNode js/document styles)]
      (.appendChild el node)
      (.appendChild (.-head js/document) el)
      el)))
