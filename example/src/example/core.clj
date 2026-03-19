(ns example.core)

(defn fib
  "Takes a non-negative integer `n` and returns the nth number in the Fibonacci sequence."
  [n]
  (nth (map first (iterate (fn [[a b]] [b (+' a b)]) [0 1])) n))

(defn fib-naive [n]
  (if (<= n 1) n (+ (fib-naive (- n 1)) (fib-naive (- n 2)))))


(comment
  ;; this is an example comment
  (fib 10)
  ;; recursive
  (fib (fib 10))
  ;; functional
  (map fib (range 10))
  )


(comment
  (require '[scicloj.clay.v2.api :as clay])

  clay/make-hiccup


  )
