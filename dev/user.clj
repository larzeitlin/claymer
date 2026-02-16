(ns user)

;; # Re-render docs from the REPL
;; requires [Quarto CLI](https://quarto.org/docs/get-started/) to be installed

(comment
  (require '[scicloj.clay.v2.api :as clay])
  (clay/make! {:base-source-path "test"
               :source-path ["claymer/core_test.clj"
                             "claymer/core_test_additional.md"]
               :render true
               :book {:title "Claymer Documentation"}
               :first-as-index true
               :clean-up-target-dir true
               :remote-repo {:git-url "https://github.com/larzeitlin/claymer"
                             :branch "main"}
               :format [:quarto :html]
               :subdirs-to-sync ["test"]}))
