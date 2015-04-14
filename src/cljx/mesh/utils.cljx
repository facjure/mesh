(ns mesh.utils
  (:require   [garden.core :refer [css]]
              [mesh.typography :as typography]
              [mesh.grid :as grid]))

#+cljs
(defn insert-stylesheet [styles]
  "Inserts Stylesheet into document head"
  (let [el (.createElement js/document "style")
        node (.createTextNode js/document styles)]
    (.appendChild el node)
    (.appendChild (.-head js/document) el)
    el))
