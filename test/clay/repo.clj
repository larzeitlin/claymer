(ns clay.repo
  (:require
   [scicloj.kindly.v4.kind :as kind]
   [scicloj.clay.v2.config :as clay-config]
   [scicloj.clay.v2.util.path :as clay-path])
  (:import [java.nio.file Paths]))

(defn rel-path-with-line [var-symbol]
  (let [m (meta (resolve var-symbol))
        file-path (Paths/get (:file m) (make-array String 0))
        root-path (Paths/get (System/getProperty "user.dir") (make-array String 0))]
    {:path (.relativize root-path file-path)
     :line (:line m)}))

(defn src-link
  "Generate a link to a file in the remote repo using Clay's config.
  Must have `:remote-repo` set in `/clay.edn`"
  [var-symbol]
  (let [remote-repo (:remote-repo (clay-config/config))
        {:keys [path line]} (rel-path-with-line var-symbol)
        path-with-line (str path "#L" line)]
    (kind/hiccup
     [:a {:href (clay-path/file-git-url remote-repo path-with-line)}
      (str (symbol (resolve var-symbol)))])))

