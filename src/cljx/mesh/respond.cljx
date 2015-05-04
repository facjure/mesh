(ns mesh.respond
  (:refer-clojure :exclude [+ - * /])
  #+cljs
  (:require-macros [mesh.def :refer [defbreakpoint]])
  #+clj
  (:require [mesh.def :refer [defbreakpoint]])
  (:require [garden.core :refer [css]]
            [garden.units :as u :refer (px pt em rem dpi)]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-media]]))

;; Generic

(def breakpoints
  {:mobile (px 320)
   :tablet (px 768)
   :laptop (px 1024)
   :desktop (px 1440)})

(defbreakpoint mobile-sm
  {:screen true
   :min-width (px 320)
   :max-width (px 480)})

(defbreakpoint mobile-lg
  {:screen true
   :min-width (px 481)})

(defbreakpoint tablet
  {:screen true
   :min-width (px 768)
   :max-width (px 1023)})

(defbreakpoint laptop
  {:screen true
   :min-width (px 1024)
   :max-width (px 1439)})

(defbreakpoint desktop-sm
  {:screen true
   :min-width (px 1440)})

(defbreakpoint desktop-lg
  {:screen true
   :min-width (px 1824)})

;; IPhones (3, 4, 4s, 5, 5s, 6)

(def iphone-3-4-media-params
  {:screen true
   :min-device-width (px 320)
   :max-device-width (px 480)
   :-webkit-min-device-pixel-ratio 2})

(defbreakpoint iphone-3-4
  iphone-3-4-media-params)

(defbreakpoint iphone-3-4-landscape
  (assoc iphone-3-4-media-params :orientation :landscape))

(defbreakpoint iphone-3-4-portrait
  (assoc iphone-3-4-media-params :orientation :portrait))

(def iphone-5-media-params
  {:screen true
   :min-device-width (px 320)
   :max-device-width (px 568)
   :-webkit-min-device-pixel-ratio 2})

(defbreakpoint iphone-5
  iphone-5-media-params)

(defbreakpoint iphone-5-landscape
  (assoc iphone-5-media-params :orientation :landscape))

(defbreakpoint iphone-5-portrait
  (assoc iphone-5-media-params :orientation :portrait))

(def iphone-6-media-params
  {:screen true
   :min-device-width (px 375)
   :max-device-width (px 667)
   :-webkit-min-device-pixel-ratio 2})

(defbreakpoint iphone-6
  iphone-6-media-params)

(defbreakpoint iphone-6-landscape
  (assoc iphone-6-media-params :orientation :landscape))

(defbreakpoint iphone-6-portrait
  (assoc iphone-6-media-params :orientation :portrait))

;; IPads

(def ipad-1-2-media-params
  {:screen true
   :min-device-width (px 768)
   :max-device-width (px 1024)
   :-webkit-min-device-pixel-ratio 1})

(defbreakpoint ipad-1-2
  ipad-1-2-media-params)

(defbreakpoint ipad-1-2-landscape
  (assoc ipad-1-2-media-params :orientation :landscape))

(defbreakpoint ipad-1-2-portrait
  (assoc ipad-1-2-media-params :orientation :portrait))

(def ipad-mini ipad-1-2)
(def ipad-mini-landscape ipad-1-2-landscape)
(def ipad-mini-portrait ipad-1-2-portrait)

(def ipad-3-4-media-params
  {:screen true
   :min-device-width (px 768)
   :max-device-width (px 1024)
   :-webkit-min-device-pixel-ratio 2})

(defbreakpoint ipad-3-4
  ipad-3-4-media-params)

(defbreakpoint ipad-3-4-landscape
  (assoc ipad-3-4-media-params :orientation :landscape))

(defbreakpoint ipad-3-4-portrait
  (assoc ipad-3-4-media-params :orientation :portrait))

;; Laptops

(defbreakpoint non-retina-laptops
  {:screen true
   :min-device-width (px 1200)
   :max-device-width (px 1600)
   :-webkit-min-device-pixel-ratio 1})

(defbreakpoint retina-laptops
  {:screen true
   :min-device-width (px 1200)
   :max-device-width (px 1600)
   :-webkit-min-device-pixel-ratio 2
   :min-resolution (dpi 192)})
