Mesh
====

A Responsive Grid & Typography toolkit for Clojure & Clojurescript. Built on [Garden](https://github.com/noprompt/garden).

## Quickstart

A sample workflow to write Stylesheets in Clojure or Clojurescript:

A dead simple grid:

```clojure
(grid/create-minimal-grid ".grid" (px 20))
```

Fractional and nested grids:

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

**Create new DSLs**:

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
   :body-font (:garamond typo/font-families)
   :body-font-weight 400
   :header-font (:garamond typo/font-families)
   :header-font-weight 600
   :header-color "#111"
   :scale 1.5})

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

To make this spec work a true functional programmer will say, sure, let me
build two higher order functions for you.

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

We can't chain mixins that pass the same context around a single declaration in
Sass.

But we can do that with Garden.

Welcome to Clojurescript.

## Workflow

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
mesh.utils.insert_styles(app.styles.index)
```

As you can see you don't need complex build tools like Webpack in Clojurescript. Convert CSS into
data, and pass them around as code. Did I say they have namespaces?

## Examples

The `examples` folder consists of working examples, primarily in Om.

To run examples:

	lein dev

Examples are run with the current mesh src with figwheel.

## Templates

- [mala](https://github.com/priyatam/mala) is an isomorphic clojure/cljs
  template to build SPA with Ring, Om, and Garden (and soon Mesh).

## Contributing

Mesh is currently in the design phase. 

## Credits

A big thanks to Joel Holbrooks for creating Garden, and David Nolen for everything else :).

Ideas for implementing CSS Grids are inspired and being ported from
[Gridism](http://cobyism.com/gridism/),
[Lost](https://github.com/corysimmons/lost),
[Golden Grid System](http://goldengridsystem.com),
[Susy](http://susy.oddbird.net) and other libraries. I learned a lot by studying
the source of these libraries, and borrowed ideas from them. Starting this
library would have been much harder without the work of these designers.

## References

Josef Müller-Brockmann's [Grid Systems](http://www.amazon.com/Grid-Systems-Graphic-Design-Communication/dp/3721201450)
is a quintessential introduction to the theory of Grids & Typography.

[Thinking grids](https://github.com/priyatam/thinking-grids) is my reference to
explore various grid libraries in CSS3, Sass, Less, and Stylus.

- [Baseline Grids](http://alistapart.com/article/settingtypeontheweb)
- [Don’t overthink it](https://css-tricks.com/dont-overthink-it-grids/)
- [Fluid Grids](http://alistapart.com/article/fluidgrids)
- [OOCS](http://oocss.org/grids_docs.html)
- [The magic behind Bootstrap Grids](http://www.helloerik.com/the-subtle-magic-behind-why-the-bootstrap-3-grid-works)
- [Understanding Susy's Grids](http://www.zell-weekeat.com/susy2-tutorial)

## Status

0.2.x

Unstable. Incomplete. I'm still in the process of porting several sass grid+typo
libraries to gain a better understanding of a flexible api for Clojurescript.

## License

Copyright © 2015 Facjure LLC.

Released under the Eclipse Public License, same as Clojure.
