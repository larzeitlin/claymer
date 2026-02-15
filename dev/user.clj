(ns user)

(comment
  (require '[scicloj.clay.v2.api :as clay])
  ; requires Quarto CLI to be installed: https://quarto.org/docs/get-started/
  (clay/make! {:base-source-path "test"
               :render true
               :first-as-index true
               :format [:quarto :fgm]}))
