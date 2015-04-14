Mesh
====

A Responsive Grid & Typography toolkit for Clojure & Clojurescript. Based on [Garden](https://github.com/noprompt/garden).

## Quickstart

A sample grid:

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

To run examples (built on Om)

	lein dev
	
## Contributing

Mesh is currently in the design phase. 

## Credits

A big thanks to Joel Holbrooks for creating Garden, and David Nolen for everything else :). Ideas for implementing Grids are inspired and taken from Gridism, Lost, Bootstrap, and many grid libraries. 

## References

Josef Müller-Brockmann's [Grid Systems](http://www.amazon.com/Grid-Systems-Graphic-Design-Communication/dp/3721201450) is a quintessential introduction to the theory of Grids & Typography in Graphic Design.

- [Thinking grids](https://github.com/priyatam/thinking-grids).
- [Baseline Grids](http://alistapart.com/article/settingtypeontheweb)
- [Fluid Grids](http://alistapart.com/article/fluidgrids)
- [Don't overthink it](https://css-tricks.com/dont-overthink-it-grids/)
- [Golden Grid System](http://goldengridsystem.com)
- [CSS Grid Module Level 1 Spec](http://dev.w3.org/csswg/css-grid/)
- [OOCS](http://oocss.org/grids_docs.html)
- [The magic behind Bootstrap Grids](http://www.helloerik.com/the-subtle-magic-behind-why-the-bootstrap-3-grid-works)
- [Understanding Susy's Grids](http://www.zell-weekeat.com/susy2-tutorial)

## Status

0.2.x

## License

Copyright © 2015 Facjure LLC.

Released under the Eclipse Public License, same as Clojure.
