(require '[lumo.build.api :as b])

(b/build "src"
  {:main 'scratch
   :output-to "main.js"
   :optimizations :advanced
   :target :nodejs})