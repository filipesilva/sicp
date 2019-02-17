# README

This is my working environment for working through 
[Structure and Interpretation of Computer Programs](https://en.wikipedia.org/wiki/Structure_and_Interpretation_of_Computer_Programs)
in ClojureScript and JavaScript (when possible).

It uses [Lumo](https://github.com/anmonteiro/lumo) as the ClojureScript (CLJS) environment.
Run a local version using `npx lumo` after `npm i`, or install it globally via `npm i -g lumo-cljs`.
[NodeJS](https://nodejs.org/en/) is used for the JavaScript environment. 

Ways to run code:
- Running `lumo`/`node` with no arguments starts the REPL.
- Add the `#!/usr/bin/env lumo` (or `node`) hashbang to a file to run it as an executable, which is
the same as `lumo ./file.cljs`/`node ./file.js`.
- Load a file into the CLJS REPL via `lumo -i ./file.cljs -r` or `(load-file "./file.js")` while
running. The Node REPL only allows `.load file.js` while running, but errors out with hashbangs.
- Build the `./scratch.cljs` file into runnable JS with `lumo -c src build.cljs` or `npx build`.

CLJS resources:
- docs: http://clojuredocs.org/, https://clojure.org/reference/reader
- cheatsheet: https://cljs.info/cheatsheet/, https://clojure.org/api/cheatsheet
- syntax: https://clojure.org/guides/learn/syntax, https://github.com/shaunlebron/ClojureScript-Syntax-in-15-minutes
- JS interop: https://github.com/anmonteiro/lumo/wiki/Invoke-node, 
https://lambdaisland.com/episodes/clojurescript-interop
- Lumo docs: https://github.com/anmonteiro/lumo/wiki/Cli-options

## Windows
The Lumo REPL is [bugged on Windows](https://github.com/anmonteiro/lumo/issues/266) and exits after
a few commands.
Workarounds include using [WSL](https://docs.microsoft.com/en-us/windows/wsl/install-win10) or 
Docker via `docker run -v $(pwd -W):/src -it anmonteiro/lumo`.
To use hashbangs on Windows use [GitBash](https://git-scm.com/downloads) as your console.
