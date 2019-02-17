# README

This is my working environment for working through 
[Structure and Interpretation of Computer Programs](https://en.wikipedia.org/wiki/Structure_and_Interpretation_of_Computer_Programs)
in ClojureScript.

It uses [Lumo](https://github.com/anmonteiro/lumo) as the ClojureScript (CLJS) environment.
Run the local version using `yarn lumo` or install it globally from the `lumo-cljs` package.

Running `lumo` with no arguments starts the REPL.
Add the `#!/usr/bin/env lumo` hashbang to a .cljs file to run it as an executable, which is the 
same as `lumo file.cljs`.
Load a file into the REPL via `lumo -i file.cljs -r`.
Build the `./scratch.cljs` file into runnable JS with `lumo -c src build.cljs` or `yarn build`.

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
