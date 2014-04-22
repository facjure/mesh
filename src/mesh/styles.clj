(ns mesh.styles
 (:refer-clojure :exclude [+ - * /])
 (:require [garden.core :refer [css]]
           [garden.units :as u :refer [px pt]]
           [garden.units :refer (px+ px* px- px-div)]
           [garden.repl]
           [garden.color :as color :refer [hsl rgb]]
           [garden.arithmetic :refer [+ - * /]]
           [garden.stylesheet :refer [at-media]]
           [garden.def :refer [defrule defkeyframes]]))


(css [:body {:font-size "16px"}])

(css [:h1 :h2 {:font-weight "none"}])

(css [:h1 [:a {:text-decoration "none"}]])

(css [:h1 :h2 {:font-weight "normal"}
             [:strong :b {:font-weight "bold"}]])

(css [:p {:font ["16px" "sans-serif"]}])


(def vendor (css [:.box
                  {:-moz {:border-radius "3px"
                          :box-sizing "border-box"}}]))

(defn partly-rounded
  ([r1] (partly-rounded r1 r1))
  ([r1 r2]
   {:border {:top-right-radius r1
             :bottom-left-radius r2}}))

(css [:.box (partly-rounded "3px")])


(px (px 16))

(px (pt 1))

(px* 2 (pt 1))

(def red (hsl 0 100 50))
(def green (hsl 120 100 50))

(color/darken red 25)

(color/color+ red green)

(+ 20 (color/hsl 0 0 0) 1 (color/rgb 255 0 0))

(def media
  (css
   (at-media {:min-width (px 768) :max-width (px 979)}
             [:container {:width (px 960)}])))



;; Layout

(css [:* :*before :*after] {:.box {:-moz "border-box"
                                   :-webkit "border-box"}})

*, *:before, *:after {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}


