(ns mesh.respond
  (:refer-clojure :exclude [+ - * /])
  #+cljs
  (:require-macros [mesh.def :refer [defbreakpoint]])
  #+clj
  (:require [mesh.def :refer [defbreakpoint]])
  (:require [garden.core :refer [css]]
            [garden.units :as u :refer [px pt]]
            [garden.units :refer (px+ px* px- px-div em)]
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

;; IPhones

(def iphone-4-media-params
  {:screen true
   :min-device-width (px 320)
   :max-device-width (px 480)
   :-webkit-min-device-pixel-ratio 2})

(defbreakpoint iphone-4
  iphone-4-media-params)

(defbreakpoint iphone-4-landscape
  (assoc iphone-4-media-params :orientation :landscape))

(defbreakpoint iphone-4-portrait
  (assoc iphone-4-media-params :orientation :portrait))

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

