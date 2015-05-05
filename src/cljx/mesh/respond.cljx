(ns mesh.respond
  (:refer-clojure :exclude [+ - * /])
  #+cljs
  (:require-macros [mesh.def :refer [defbreakpoint]])
  #+clj
  (:require [mesh.def :refer [defbreakpoint]])
  (:require [garden.core :refer [css]]
            [garden.units :as units :refer (px pt em rem dpi)]
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

;; Galaxy Phones

(def galaxy-s3-media-params
  {:screen true
   :min-device-width (px 320)
   :max-device-width (px 640)
   :-webkit-min-device-pixel-ratio 2})

(defbreakpoint galaxy-s3
  galaxy-s3-media-params)

(defbreakpoint galaxy-s3-landscape
  (assoc galaxy-s3-media-params :orientation :landscape))

(defbreakpoint galaxy-s3-portrait
  (assoc galaxy-s3-media-params :orientation :portrait))

(def galaxy-s4-media-params
  {:screen true
   :min-device-width (px 320)
   :max-device-width (px 640)
   :-webkit-min-device-pixel-ratio 3})

(defbreakpoint galaxy-s4
  galaxy-s4-media-params)

(defbreakpoint galaxy-s4-landscape
  (assoc galaxy-s4-media-params :orientation :landscape))

(defbreakpoint galaxy-s4-portrait
  (assoc galaxy-s4-media-params :orientation :portrait))

(def galaxy-s5-media-params
  {:screen true
   :min-device-width (px 360)
   :max-device-width (px 640)
   :-webkit-min-device-pixel-ratio 3})

(defbreakpoint galaxy-s5
  galaxy-s5-media-params)

(defbreakpoint galaxy-s5-landscape
  (assoc galaxy-s5-media-params :orientation :landscape))

(defbreakpoint galaxy-s5-portrait
  (assoc galaxy-s5-media-params :orientation :portrait))

;; HTC One

(def htc-one galaxy-s5)
(def htc-one-landscape galaxy-s5-landscape)
(def htc-one-portrait galaxy-s5-portrait)

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

;; Galaxy Tablets

(def galaxy-tab-media-params
  {:min-device-width (px 800)
   :max-device-width (px 1280)})

(defbreakpoint galaxy-tab
  galaxy-tab-media-params)

(defbreakpoint galaxy-tab-landscape
  (assoc galaxy-tab-media-params :orientation :landscape))

(defbreakpoint galaxy-tab-portrait
  (assoc galaxy-tab-media-params :orientation :portrait))

;; Nexus Tablets

(def nexus-tab-media-params
  {:screen true
   :min-device-width (px 601)
   :max-device-width (px 906)
   :-webkit-min-device-pixel-ratio 1.331
   :-webkit-max-device-pixel-ratio 1.332})

(defbreakpoint nexus-tab
  nexus-tab-media-params)

(defbreakpoint nexus-tab-landscape
  (assoc nexus-tab-media-params :orientation :landscape))

(defbreakpoint nexus-tab-portrait
  (assoc nexus-tab-media-params :orientation :portrait))

;; Kindle Fire Crap

(def kindle-fire-media-params
  {:screen true
   :min-device-width (px 800)
   :max-device-width (px 1280)
   :-webkit-min-device-pixel-ratio 1.5})

(defbreakpoint kindle-fire
  kindle-fire-media-params)

(defbreakpoint kindle-fire-landscape
  (assoc kindle-fire-media-params :orientation :landscape))

(defbreakpoint kindle-fire-portrait
  (assoc kindle-fire-media-params :orientation :portrait))

;; Kindle Fire HD Crap

(def kindle-fire-hd-media-params
  {:screen true
   :min-device-width (px 1200)
   :max-device-width (px 1600)
   :-webkit-min-device-pixel-ratio 1.5})

(defbreakpoint kindle-fire-hd
  kindle-fire-hd-media-params)

(defbreakpoint kindle-fire-hd-landscape
  (assoc kindle-fire-hd-media-params :orientation :landscape))

(defbreakpoint kindle-fire-hd-portrait
  (assoc kindle-fire-hd-media-params :orientation :portrait))

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
