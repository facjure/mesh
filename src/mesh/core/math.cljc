(ns mesh.core.math
  (:require [garden.core :refer [css]]))

#?(:clj
   (defn pow [base e]
     (.pow Math base e)))

#?(:cljs
   (defn pow [base e]
     (.pow js/Math base e)))

(defn whole-number? [n]
    (= n (Math/floor n)))
