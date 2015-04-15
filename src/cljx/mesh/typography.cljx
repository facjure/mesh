(ns mesh.typography
  #+clj
  (:refer-clojure :exclude [+ - * / rem])
  (:require  [garden.compiler :refer [render-css]]
             [garden.core :refer [css]]
             [garden.units :as units :refer (px pt em rem vw)]
             [garden.arithmetic :refer [+ - * /]]
             [garden.stylesheet :refer [at-media]]
             [mesh.utils :refer [pow]]))

(def font-families
  {:garamond ["\"EB Garamond\"" "Baskerville" "Georgia" "Times" "serif"]
   :optima ["\"Optima\"" "Segoe" "Calibri" "Arial" "sans-serif"]})

(def scale
  {:minor-second 1.067
   :major-second 1.125
   :minor-third  1.2
   :major-third  1.25
   :perfect-fourth 1.333
   :aug-fourth 1.414
   :perfect-fifth 1.5
   :minor-sixth 1.6
   :golden 1.618
   :major-sixth 1.667
   :minor-seventh 1.778
   :major-seventh 1.875
   :octave 2
   :major-tenth 2.5
   :major-eleventh 2.667
   :major-twelfth 3
   :double-octave 4})

(def defaults
  {:line-height-ratio 1.75
   :header-ratio (:golden scale)
   :min-width (px 400)
   :max-width (px 1200)
   :vertical-rythm true
   :min-font (px 12)
   :max-font (px 24)
   :body-color "#666"
   :body-font (:garamond font-families)
   :body-font-weight 400
   :header-font (:garamond font-families)
   :header-font-weight 600
   :header-color "#111"})

;; http://madebymike.com.au/writing/precise-control-responsive-typography/
;; calc(a + (b - x) * ((100vw - d) / (e - f))
(defn calc [min-font max-font min-width max-width]
  (let [a min-font
        b (.magnitude max-font)
        c (.magnitude min-font)
        d min-width
        e (.magnitude max-width)
        f (.magnitude min-width)
        res (* (+ a (- b c)) (/ (- (vw 100) d) (- e f)))]
    res))

(defn typeset-html [conf]
  [[:html {:font-family (:body-font conf)
           :font-weight (:body-font-weight conf)
           :color (:body-color conf)
           :line-height (em (:line-height-ratio conf))
           :font-size (:min-font conf)}]
   (at-media {:min-width (:min-width conf)}
             [:html {:font-size (calc (:min-font conf)
                                      (:max-font conf)
                                      (:min-width conf)
                                      (:max-width conf))}])])

(defn overlay [color offset]
  [:body {:backround color
          :background-size [["100%" (em (:line-height-ratio defaults))]]
          :background-position [[0 (px offset)]]}])

(defn font [family size weight kerning leading & options]
  {:font-family family
   :font-size (rem size)
   :font-weight weight
   :letter-spacing (rem kerning)
   :line-height (em leading)
   :text-transform (get options :text-transform "none")})

(defn typeset [serif sans mono]
  [[:body :p (font sans 1 300 0.1 1.5)]
   [:h1 (font serif 3 600 0.5 2)]
   [:h2 (font serif 3 400 0.5 2)]
   [:h3 (font serif 2 300 0.5 2)]
   [:h4 (font serif 1.5 300 0.3 2)]
   [:h5 :h6 (font mono 1.2 300 0.2 1.5)]
   [:header (font serif 4 700 0.3 1.5 "small-caps")]
   [:footer (font sans 1 100 0.3 1.5)]])
