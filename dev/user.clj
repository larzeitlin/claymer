(ns user)

(comment
  (require '[scicloj.clay.v2.api :as clay])
  ; requires Quarto CLI to be installed: https://quarto.org/docs/get-started/
  (clay/make! {:base-source-path "test"
               :render true
               :book {:title "Claymer Documentation"}
               :first-as-index true
               :clean-up-target-dir true
               :remote-repo {:git-url "https://github.com/larzeitlin/claymer"
                             :branch "main"}
               :format [:quarto :html]}))
