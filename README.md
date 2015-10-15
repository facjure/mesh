Mesh
====

A Responsive Grid & Typography toolkit for Clojure & Clojurescript. Built on
[Garden](https://github.com/noprompt/garden), and
[Gardener](https://github.com/facjure/gardener).

## Rationale

> Web Design is 95% Typography - iA

## Features

- A grid in a single fn
- Available grids: columnar, container-row-column, fluid-nested-grid, golden-grid
- Baseline Grid in a single fn
- Modular Scale in a single fn
- Viewport Math in a single fn
- Baseline Typography
- Typography mixins, and responsive utilities with gardener.
- Create new DSLs

## Quickstart

**A simple 8-column grid** with `col-x` classes:

```clojure
(grid/create-minimal-grid ".grid" (px 20))
```

**Fluid, Fractional grids**

```clojure
(def gutter (px 20))

(def grids
   (list (grid/create ".grid" gutter)
         (grid/wrap-widths 978)
         (grid/create-nested-units)
         (grid/nuke-gutters-and-padding)
         (grid/respond-small (:mobile breakpoints) gutter)
         (grid/respond-medium (:tablet breakpoints))))
```

**Nested Grids**

Add the following to fluid grids:

```clojure
(grid/create-nested-units)
```

**Baseline Typpography**

```clojure
(typography/zoomable-baseline-grid 16 24)
```

**Create new DSLs**

You may be working with a Creative Director or a Print Designer who does not
understand CSS. But they understand Design. Wouldn't it be nice to understand
their specs and codify them into data?

Designer: Make the headings `serifs` and `scale-types` using four `breakpoints`
(take 480 px increments). And yes, use our brand settings.

```clojure
(def settings
  {:min-width (px 400)
   :max-width (px 1200)
   :min-font (px 12)
   :max-font (px 32)
   :body-font (:eb-garamond typo/font-families)
   :header-font (:eb-garamond typo/font-families)
   :header-font-weight 600
   :header-color "#111"
   :scale :golden-ratio
   :breakpoints {:mobile (px 480)
                 :tablet (px 960)
                 :laptop (px 1440)
                 :monitor (px 1920)}})

(def fonts {:font-size-base (em 1.5)
            :line-height-base (em 1.45)
            :ff-serif ["EB Garamond" "Serif"]
            :ff-sans ["Fira Sans" "sans-serif"]
            :ff-mono ["Source Code Pro" "monospace"]})
			
(defn headings [declarations]
	[:h1 :h2 :h3 :header declarations])

;; Create your own DSL
(-> headings
    (scale-type settings)
    (make-serifs typo/font-families)))
```

Functional programmer: "Sure, let me build two higher order functions."

```clojure
;; a bad example
(defn make-serifs [selector families]
  (fn [declarations]
    (let [styles (selector declarations)]
      (conj styles (font (:garamond families) 3 600 0.5 2)))))

;; slightly better
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

```

## Examples

Examples include example designs with
[Brutha](https://github.com/weavejester/brutha) and
[Om](https://github.com/omcljs/om).

Run `examples` with the current mesh src, using figwheel:

	lein dev

- hello: simple example with Cljs
- typography: showcase of all grids with Clj (compile to css)
- grids: showcase of all grids with Clj (compile to css)

For a complete user-interface design with Om, see [Mala](https://github.com/priyatam/mala).

## Clojure or Clojurescript?

Styles can be compiled on the server, client, or generated as plain css files.

Write styles in **Clojure** when you prefer static styles or compiled styles on
server. Assuming you’re running `lein garden auto` with `:stylesheet
app.styles/index` set in its build id, all styles will be compiled into css on
disk.

Write styles in **Clojurescript** when you want styles to be dynamically
included at runtime, via Js or Cljs. This is useful if you're building
fine-grained components based on React-like libraries: rather than bundling
styles into a single file, just import individual styles as _functions_:

```clojure
(ns app.styles
  (:require [garden.core :refer [css]]))

(def index
  (css grids)))

(mesh.utils/insert-styles styles/index)
```

Invoke styles via plain old **Javascript**:

```javascript
mesh.dom.insert_styles(app.styles.index)
```

You don't need complex build tools like [Webpack](http://webpack.github.io) in
Clojurescript. Just Convert CSS into data and pass them around as code.

## Template

[Mala](https://github.com/priyatam/mala) is a complete User-Interface lein
template integrating Om, Core.Async, and Mesh.

## Credits

A big thanks to Joel Holbrooks for creating Garden, Chris Coyler for countless
[CSS-Tricks](https://css-tricks.com), and David Nolen.

Ideas for implementing CSS Grids and Typography are inspired and being ported
from [Gridism](http://cobyism.com/gridism/),
[Lost](https://github.com/corysimmons/lost), [Golden Grid
System](http://goldengridsystem.com), and other libraries. I learned a lot by
studying the source of these libraries, and borrowed ideas from them. Starting
this library would have been much harder without the work of these designers.

## References

- [Clojure/West Presentation](https://www.youtube.com/watch?v=-jnJGNDoSXc)
- [LispCast Interview](http://www.lispcast.com/pre-west-priyatam-mudivarti)
- [Grid Systems in Graphic Design](http://www.amazon.com/Grid-Systems-Graphic-Design-Communication/dp/3721201450)
by Josef Müller-Brockmann is a classic
- [Thinking grids](https://github.com/priyatam/thinking-grids) is my reference to
explore grid libraries in Sass, Less, and Stylus.
- [Don’t overthink it](https://css-tricks.com/dont-overthink-it-grids/) by Chris Coyler
- [Baseline Grids](http://alistapart.com/article/settingtypeontheweb)
- [Fluid Grids](http://alistapart.com/article/fluidgrids)
- [OOCS](http://oocss.org/grids_docs.html)
- [The magic of Bootstrap Grids](http://www.helloerik.com/the-subtle-magic-behind-why-the-bootstrap-3-grid-works)
- [Understanding Susy's Grids](http://www.zell-weekeat.com/susy2-tutorial)

## Contributing

Mesh is currently in the design phase. 

I'm in the process of porting css/sass libraries before settling on the core
apis. Feel free to use the early versions below for prototyping and provide
feedback via github issues.

## Status

Unstable.

[![Clojars Project](http://clojars.org/facjure/mesh/latest-version.svg)](http://clojars.org/facjure/mesh)

## License

Copyright © 2015 Facjure, LLC.

Released under the Eclipse Public License, same as Clojure.
