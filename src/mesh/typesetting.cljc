(ns mesh.typesetting
  (:refer-clojure :exclude [+ - * / rem])
  (:require  [garden.compiler :refer [render-css]]
             [garden.core :refer [css]]
             [garden.units :as units :refer (px pt pc em rem vw)]
             [garden.arithmetic :refer [+ - * /]]
             [garden.stylesheet :refer [at-media]]
             [mesh.core.respond :as respond]
             [mesh.typography :as typography]))

;; Experimental DSL

(defn make-serifs [selector families]
  (fn [declarations]
    (let [styles (selector declarations)]
      (conj styles (typography/font (:ff-sans families) 2 400 0.5 1.45)))))

(defn scale-type [selector params]
  (fn [declarations]
    (let [styles (selector declarations)]
      (conj styles
            (at-media {:min-width (get-in params [:breakpoints :mobile])}
                      [:& {:font-size (* 1.5 (:min-font params))}])
            (at-media {:min-width (get-in params [:breakpoints :tablet])}
                      [:& {:font-size (* 2 (:min-font params))}])
            (at-media {:min-width (get-in params [:breakpoints :laptop])}
                      [:& {:font-size (* 2.5 (:min-font params))}])))))


(defn leading [selector lead]
  (fn [declarations]
    (let [styles (selector declarations)]
      (conj styles {:line-height (em lead)}))))
