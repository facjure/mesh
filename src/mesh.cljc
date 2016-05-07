(ns mesh
  (:require [garden.compiler :as compiler]
            [garden.util :as util]
            [garden.types]
            [garden.color]
            [garden.selectors :as selectors])
  (:import (garden.types CSSUnit
                         CSSFunction
                         CSSAtRule)
           (garden.color CSSColor)
           (garden.selectors CSSSelector)))

(defmethod print-method CSSUnit [css-unit writer]
  (.write writer (compiler/render-css css-unit)))
