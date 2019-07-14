# README

This is my working environment for working through [Structure and Interpretation of Computer Programs](https://en.wikipedia.org/wiki/Structure_and_Interpretation_of_Computer_Programs) in ClojureScript and JavaScript (when possible).

## Installation

Install the [Shadow-CLJS requirements](https://github.com/thheller/shadow-cljs#requirements), [VS Code](https://code.visualstudio.com/), and the [Calva](https://github.com/BetterThanTomorrow/calva) VS Code plugin.

Run `npm install`.


## ClojureScript workflow

Press `ctrl+shift+p` to open the command palette in VS Code and search for `Jack-in` and run it. Select the `sicp` and `sicp-test` builds, and press ok. Then select the `sicp` to connect to.

A terminal will be opened that is running the CLJS tests in watch mode, together with a Calva REPL for CLJS. The Calva REPL will switch to the namespace of the currently open file. Run `node out/sicp.js` on a terminal to give the REPL an environment.

While writing code you can use Calva commands in the command palette to evaluate the current form and print it inline or to the output panel. You can use [deftest](https://clojurescript.org/tools/testing) to add tests that will be ran on save.


## JavaScript workflow

Run `npm run watch-js` to run and watch `src/sicp/main.js`. Change the `require` calls in that file to change the tested files. You can write [assertions](https://nodejs.org/api/assert.html) which will be checked when the script runs.

Run `node` to enter a node REPL, where you can use `.load file.js` to load a file. 

You can also use [vscode-code-runner](https://github.com/formulahendry/vscode-code-runner) to run snippets on the terminal,although you'll need some extra config on windows (https://github.com/formulahendry/vscode-code-runner/issues/338#issuecomment-466659944).


## CLJS resources:

- docs: https://cljs.github.io/api/, <http://clojuredocs.org/>, <https://clojure.org/reference/reader>
- reference: <https://devdocs.io/>
- cheatsheet: <https://cljs.info/cheatsheet/>, <https://clojure.org/api/cheatsheet>
- syntax: <https://clojure.org/guides/learn/syntax>, <https://github.com/shaunlebron/ClojureScript-Syntax-in-15-minutes>
- JS interop: <https://github.com/anmonteiro/lumo/wiki/Invoke-node>, <https://lambdaisland.com/episodes/clojurescript-interop>
- Style guide: <https://github.com/bbatsov/clojure-style-guide>
- Shadow-CLJS: <https://github.com/anmonteiro/lumo/wiki/Cli-options>
- Calva: <https://github.com/BetterThanTomorrow/calva>
- Lumo: <https://github.com/anmonteiro/lumo>
