^:kindly/hide-code
(ns claymer.core-test
  (:require
   [claymer.core :as sut]
   [clojure.test :as test :refer [deftest is testing]]
   [clojure.test.check :as tc]
   [clojure.test.check.generators :as gen]
   [clojure.test.check.properties :as prop]
   [scicloj.kindly.v4.kind :as kind]))

;; # Fibonacci Sequence Function
;; ## Functionality
;; - `fib`: Takes a non-negative integer `n` and returns the nth Fibonacci number.
;; ### Examples:
;; Produces nth number in the fibonacci sequence:
(deftest fib-test
  (testing
      (is (= (sut/fib 0) 0)))
  (testing
      (is (= (sut/fib 12) 144)))
  (testing
      (is (= (sut/fib 69) 117669030460994))))

; Negitive numbers are out of bounds.
(deftest fib-neg-n-out-of-range-test
  (is
   (try (sut/fib -1)
        (catch Exception e
          (= (type e) java.lang.IndexOutOfBoundsException)))))

;; ## Properties
;; ### Parity Invarient
;; Every third Fibonacci number is even.
;; This is because an odd + even = odd so we get the cycle:
;; ```
;; 0+1=1 -> 1+1=2 -> 1+2=3 -> ...
;; E+O=O -> O+O=E -> O+E=O -> ...
;; ```

(def parity-invariant
  (prop/for-all
   [v gen/pos-int]
   (let [fib-number (sut/fib v)]
     (if (zero? (rem v 3))
       (even? fib-number)
       (odd? fib-number)))))

(tc/quick-check 10000 parity-invariant)

^:kindly/hide-code
(defn bench-ms [f x]
  (let [start (System/nanoTime)
        _ (f x)
        end (System/nanoTime)]
    (/ (- end start) 1e6)))

;; ## Performance Comparison
;; Below we compare the efficient iterative `fib` against a naive recursive
;; `fib-naive` implementation across a range of input sizes. The naive version
;; has exponential time complexity, so its execution time grows dramatically.

^:kindly/hide-code
(def bench-data
  (let [ns-range (range 0 31)]
    (concat
     (for [n ns-range]
       {:n n :time-ms (bench-ms sut/fib n) :implementation "fib (iterative)"})
     (for [n ns-range]
       {:n n :time-ms (bench-ms sut/fib-naive n) :implementation "fib-naive (recursive)"}))))

^:kindly/hide-code
(kind/vega-lite
 {:$schema "https://vega.github.io/schema/vega-lite/v5.json"
  :title "Fibonacci Performance: Iterative vs Naive Recursive"
  :width 600
  :height 400
  :data {:values bench-data}
  :mark {:type "line" :point true}
  :encoding {:x {:field "n" :type "quantitative" :title "n"}
             :y {:field "time-ms" :type "quantitative" :title "Time (ms)"}
             :color {:field "implementation" :type "nominal" :title "Implementation"}}})

