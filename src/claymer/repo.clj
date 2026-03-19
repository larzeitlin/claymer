(ns claymer.repo
  (:require
   [scicloj.kindly.v4.kind :as kind]
   [scicloj.clay.v2.config :as clay-config]
   [scicloj.clay.v2.util.path :as clay-path]
   [clojure.java.io :as io])
  (:import [java.nio.file Paths]))

(defn file->absolute-path [f]
  (Paths/get
   (.getAbsolutePath (io/file f))
   (make-array String 0)))

(defn rel-path-with-line [var-symbol]
  (let [m (meta (resolve var-symbol))
        file-path (file->absolute-path (:file m))
        root-path (file->absolute-path (System/getProperty "user.dir"))]
    {:path (.relativize root-path file-path)
     :line (:line m)}))

(defn src-link
  "Generate a link to a file in the remote repo using Clay's config.
  Must have `:remote-repo` set in `/clay.edn`"
  [var-symbol]
  (println var-symbol)
  (let [remote-repo (:remote-repo (clay-config/config))
        _ (println (rel-path-with-line var-symbol))
        {:keys [path line]} (rel-path-with-line var-symbol)
        path-with-line (str path "#L" line)]
    (kind/hiccup
     [:a {:href (clay-path/file-git-url remote-repo path-with-line)}
      (str (symbol (resolve var-symbol)))])))

