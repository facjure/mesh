(ns mesh.def
  (:require [garden.types]
            [garden.util :as util]
            [garden.core]
            [garden.stylesheet :refer [at-media]])
  (:import garden.types.CSSFunction
           garden.types.CSSAtRule))

(defmacro defbreakpoint [name media-params]
  `(defn ~name [& rules#]
     (at-media ~media-params
       [:& rules#])))
