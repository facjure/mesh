(ns mesh.images
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.core :refer [css]]
            [garden.units :as u :refer [px pt pc em]]
            [garden.color :as color :refer [hsl rgb rgba]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-media]]))

(defn fit-images [clazz]
  [clazz
   [:img {:max-width "100%"}]])

;; FIXME
(defn photos []
  [[:div.photo {:margin [[(px 30) 0]]}
    [:img {:display "block"
           :max-width "100%"
           :margin "auto"}]
    [:p {:width "auto"
         :margin [[(px 10) "18.041237113402%" 0 0]]
         :font-size (em 0.8)
         :letter-spacing (px 1)
         :color (rgba 0 0 0 0.3)}
     (at-media {:screen true :max-width (px 800)}
               [:& {:margin [[(px 10) (px 20) 0 (px 20)]]}])]]
   [:.div.photo-medium {:float "left"
                        :margin [[(px 5) (px 30) (px 10) 0]]}
    [:img {:display "block"
           :max-width (px 300)
           :box-shadow [[0 0 (px 1) (rgba 0 0 0 0.4)]]}
     (at-media {:screen true :max-width (px 800)}
               [:& {:float "none"
                    :margin [[(px 30) 0]]}
                :img {:margin [[0 "auto"]]}])]]])

(def fluid-media
  [[:figure {:position "relative"}
    [:img :object :embed :video {:max-width (pc 100)
                                 :display "block"}]]
   [:img {:border 0
          "-ms-interpolation-mode" "bicubic"}]])

