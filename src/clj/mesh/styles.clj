(ns mesh.styles
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [mesh.typography :as typography]
            [mesh.grid :as grid]))

;; Local testing
#_(def all
    (merge grid/styles typography/styles))

