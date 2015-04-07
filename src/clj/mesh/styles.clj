(ns mesh.styles
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [mesh.resets :as resets]
            [mesh.typography :as typography]
            [mesh.grid :as grid]))

(def all
  (merge resets/styles grid/styles typography/styles))
