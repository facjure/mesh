Mesh
====

A Responsive Grid & Typography toolkit for Clojure & Clojurescript. Based on [Garden](https://github.com/noprompt/garden).

## Quickstart

A single api to write Stylesheets in Clojure or Clojurescript:

```clojure
(def gutter (px 20))

(defstyles grids
  (list mixins/alignments
        (grid/initialize ".grid" gutter)
        (grid/create ".grid")
        (grid/wrap-widths 978)
        (mixins/clearfix ".grid")
        (mixins/fit-images ".unit")
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        (grid/respond-small (px 568) gutter)
        (grid/respond-medium (px 1180))))
```

Write styles in **Clojure** when you prefer static styles or compiled styles on server. Assuming you’re running `lein garden auto` with `:stylesheet app.styles/index` set in its build id, all styles will be compiled into css on disk.


```clojure
(ns app.styles)
   
(def index grids)
```

Write styles in **Clojurescript** when you want styles to be dynamically included at runtime, via Js or Cljs. This is useful if you're building fine-grained components based on React-like libraries: rather than bundling styles into a single file, just import individual styles as _functions_:

```clojure
(ns app.styles
  (:require [garden.core :refer [css]]))

(def index
  (css grids)))

(mesh.utils/insert-stylesheet styles/index)
```

OR via **Javascript**:

```javascript
mesh.utils.insert_stylesheet(app.styles.index)
```

For more examples, refer `examples` and run:

	lein dev
	
## Contributing

Mesh is currently in the design phase. 

## Credits

A big thanks to Joel Holbrooks for creating Garden, and David Nolen for everything else :).

Ideas for implementing CSS Grids are inspired from Gridism, Skel, Golden Grid
System, Susy, Lost, and other libraries. I learned a lot by studying the source
of these libraries, and borrowed ideas from them. Writing this library would
have been much harder without the work of these designers.

## References

If you're new to Grid theory, Josef Müller-Brockmann's [Grid Systems](http://www.amazon.com/Grid-Systems-Graphic-Design-Communication/dp/3721201450) is a quintessential introduction to the theory of Grids & Typography in Graphic Design.

- [Thinking grids](https://github.com/priyatam/thinking-grids).
- [Baseline Grids](http://alistapart.com/article/settingtypeontheweb)
- [Fluid Grids](http://alistapart.com/article/fluidgrids)
- [Don’t overthink it](https://css-tricks.com/dont-overthink-it-grids/)
- [OOCS](http://oocss.org/grids_docs.html)
- [The magic behind Bootstrap Grids](http://www.helloerik.com/the-subtle-magic-behind-why-the-bootstrap-3-grid-works)
- [Understanding Susy's Grids](http://www.zell-weekeat.com/susy2-tutorial)
- [CSS Grid Module Level 1 Spec](http://dev.w3.org/csswg/css-grid/)

## Status

0.2.x

## License

Copyright © 2015 Facjure LLC.

Released under the Eclipse Public License, same as Clojure.
