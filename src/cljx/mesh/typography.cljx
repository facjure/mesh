(ns mesh.typography
  (:refer-clojure :exclude [+ - * / rem])
  (:require  [garden.compiler :refer [render-css]]
             [garden.core :refer [css]]
             [garden.units :as units :refer (px pt em rem vw)]
             [garden.arithmetic :refer [+ - * /]]
             [garden.stylesheet :refer [at-media]]
             [mesh.respond :as respond]
             #+clj
             [mesh.utils :as utils :refer [pow]]
             #+cljs
             [mesh.utils :as utils :refer [pow viewport-w]]))

#+cljs
(enable-console-print!)

(def state
  (atom {:font-size nil}))

(def font-families
  {:garamond ["\"EB Garamond\"" "Baskerville" "Georgia" "Times" "serif"]
   :optima ["\"Optima\"" "Segoe" "Calibri" "Arial" "sans-serif"]
   :firasans ["\"Fira Sans\"" "Calibri" "Arial" "sans-serif"]
   :sourcecode-pro ["\"Source Code Pro\"" "monospace"]})

(def scales
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
  {:line-height-ratio 1.5
   :header-ratio (:golden scales)
   :min-width (px 400)
   :max-width (px 1200)
   :vertical-rythm true
   :min-font (px 12)
   :max-font (px 32)
   :body-color "#666"
   :body-font (:garamond font-families)
   :body-font-weight 400
   :header-font (:garamond font-families)
   :header-font-weight 600
   :header-color "#111"})

(defn font [family size weight kerning leading & options]
  {:font-family family
   :font-size (rem size)
   :font-weight weight
   :letter-spacing (rem kerning)
   :line-height (em leading)
   :text-transform (get options :text-transform "none")})

(defn baseline-overlay [color offset]
  ;; TODO: implement a real func in Garden
  (let [linear-gradient (str "linear-gradient(to top, " color " 5%, white 5%)")]
    [:body {:background-position [[0 (px offset)]]
            :background-size [["100%" (em (:line-height-ratio defaults))]]
            :background linear-gradient}]))

;; http://madebymike.com.au/writing/precise-control-responsive-typography/
;; calc(a + (b - x) * ((100vw - d) / (e - f))

#+cljs
(defn calc [min-font max-font min-width max-width]
  (println "Viewport size:" (viewport-w))
  (let [font-diff (+ min-font (- max-font min-font))
        vp-diff (- (px (viewport-w)) min-width)
        wid-diff (- max-width min-width)
        res (* font-diff (/ vp-diff wid-diff))]
    (println "Calculated Fontsize: " (:magnitude res))
    (swap! state assoc :font-size res)
    res))

#+cljs
(defn typeset-html [conf scale]
  [[:html {:font-family (:body-font conf)
           :font-weight (:body-font-weight conf)
           :color (:body-color conf)
           :line-height (em (:line-height-ratio conf))
           :font-size (:min-font conf)}]
   (at-media {:min-width (:tablet respond/breakpoints)}
             [:html {:font-size (calc (:min-font conf)
                                      (:max-font conf)
                                      (:min-width conf)
                                      (:max-width conf))}])
   (at-media {:min-width (:laptop respond/breakpoints)}
             [:html {:font-size (calc (:min-font conf)
                                      (:max-font conf)
                                      (:min-width conf)
                                      (:max-width conf))}])
   (at-media {:min-width (:max-width conf)}
             [:html {:font-size (:max-font conf)}])])

#+clj
(defn typeset-html [conf scale]
  [[:html {:font-family (:body-font conf)
           :font-weight (:body-font-weight conf)
           :color (:body-color conf)
           :line-height (em (:line-height-ratio conf))
           :font-size (:min-font conf)}]
   (at-media {:min-width (:min-width conf)}
             [:html {:font-size (* (scale scales) (:min-font conf))}])])

(def reset
  {:margin 0
   :padding 0})

(defn block []
  {:margin-bottom (:line-height-ratio defaults)})

(defn vr-block [units offset]
  (let [a (* (:line-height-ratio defaults) (:max-font defaults))
        b (/ (:max-font defaults) 2)
        c (/ units 2)
        d (+ 1 (/ offset (:max-font defaults)))]
    (em (* c d (/ a b)))))

(defn typeset [serif sans mono]
  [[:body :p (font sans 1 300 0.1 1.5)]
   [:h1 (font serif 3 600 0.5 2)]
   [:h2 (font serif 3 400 0.5 2)]
   [:h3 (font serif 2 300 0.5 2)]
   [:h4 (font serif 1.5 300 0.3 2)]
   [:h5 :h6 (font mono 1.2 300 0.2 1.5)]
   [:header (font serif 4 700 0.3 1.5 "small-caps")]
   [:footer (font sans 1 100 0.3 1.5)]])

;; Ring-Like API samples FIXME

(defn make-serifs [selector families]
  (fn [declarations]
    (let [styles (selector declarations)]
      (conj styles (font (:garamond families) 3 600 0.5 2)))))

(defn scale-type [selector params]
  (fn [declarations]
    (let [styles (selector declarations)]
      (conj styles
            (at-media {:min-width (get-in params [:breakpoints :mobile])}
                      [:& {:font-size (* 1.5 (:min-font params))}])
            (at-media {:min-width (get-in params [:breakpoints :tablet])}
                      [:& {:font-size (* 1.75 (:min-font params))}])
            (at-media {:min-width (get-in params [:breakpoints :laptop])}
                      [:& {:font-size (* 2.25 (:min-font params))}])))))
