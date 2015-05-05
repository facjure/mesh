(ns examples.hello.page
  (:require [brutha.core :as react]
            [flupot.dom :as dom]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [mesh.dom :as mesh-dom]
            [mesh.typography :as typo :refer [typeset]]
            [mesh.grid :as grid]))

(def app (js/document.getElementById "hello"))

(def styles
  (css
   (list
    (grid/create-minimal-grid ".grid" (px 20)))))

(react/mount (dom/h1 "Hello World") app)

(mesh-dom/insert-styles styles)
