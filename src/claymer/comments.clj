(ns claymer.comments
  (:require
   [clojure.string :as string]
   [rewrite-clj.zip :as z]
   [scicloj.kindly.v4.kind :as kind]))

(defn rich-comments [file]
  (loop [loc (z/of-file file)
         comments []]
    (if (z/end? loc)
      (map kind/code comments)
      (let [cur-comments (if (and (z/list? loc)
                                  (= 'comment (-> loc z/down z/sexpr)))
                           (let [inner (-> loc z/down* z/right*) ;; skip past `comment` symbol
                                 body (loop [node inner
                                             acc ""]
                                        (if (nil? node)
                                          (string/trim acc)
                                          (recur (z/right* node)
                                                 (str acc (z/string node)))))]
                             (conj comments body))
                           comments)]
        (recur (z/next loc) cur-comments)))))

(comment
  (require '[clojure.java.io :as io])
  ;; This is a self capturing rich comment.
  (rich-comments (io/file "src/claymer/comments.clj")))
