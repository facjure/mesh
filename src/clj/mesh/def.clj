(ns mesh.def
  (:require [garden.types]
            [garden.util :as util]
            [garden.core])
  (:import garden.types.CSSFunction
           garden.types.CSSAtRule))

(defmacro defstyles
  "Convenience macro equivalent to `(def name (list styles*))`."
  [name & styles]
  `(def ~name (list ~@styles)))
