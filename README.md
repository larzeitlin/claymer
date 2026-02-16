# Claymer
An example of using [Clay](https://scicloj.github.io/clay/) to create self-documenting
tests in clojure code.

- `src` contains some example implementation code.
- `test` contains more or less normal clojure tests with a little markup for Clay
- `dev/user.clj` has a command to render the docs out to HTML.
- `docs` contains the rendered output
- `clay.edn` contains config pointing to this remote repository for handling links back to sourcecode.

Github pages is simply pointed to `docs/index.html`

See [the documents](http://lzeitlin.com/claymer/)

## Features demonstrated
### Docstring extraction from symbol
Using `kind`:
```clojure
(kind/doc #'my-alias/my-sym)
```
### Link to source-code in github (at line number) from symbol
Added by `test/clay/repo.clj`
```clojure
(repo/src-link 'my-alias/my-sym)
```
### Tests and results
### Property based testing results
### Profiling with a chart of results
### Combining parsed clojure files and markdown files into a single docs book.
See that both `test/core_test.clj` and `test/core_test_additional.md` are in the rendered docs.
