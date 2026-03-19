(ns user)

;; # Re-render docs from the REPL
;; requires [Quarto CLI](https://quarto.org/docs/get-started/) to be installed

(comment
  (require '[scicloj.clay.v2.api :as clay])
  (clay/make! {:base-source-path "test"
               :source-path ["example/core_test.clj"
                             "example/core_test_additional.md"]
               :render true
               :book {:title "Claymer Documentation"}
               :first-as-index true
               :clean-up-target-dir true
               :format [:quarto :html]
               :subdirs-to-sync ["test"]}))
