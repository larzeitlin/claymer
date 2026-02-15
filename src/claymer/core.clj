(ns claymer.core)

; # `claymer.core`

(defn fib [n]
  (nth (map first (iterate (fn [[a b]] [b (+' a b)]) [0 1])) n))

(defn fib-naive [n]
  (if (<= n 1) n (+ (fib-naive (- n 1)) (fib-naive (- n 2)))))
