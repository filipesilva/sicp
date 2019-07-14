; Language notes: CLJS follows Java naming conventions. File paths should map to the namespace and
; can't contain . or - characters. Use _ instead. They also need one dot (two sections) at least.
(ns sicp.exercises.section_1_1
  (:require [cljs.test :refer-macros [deftest is]]))

; 1.1

; Language notes: like in JS, it seems I can't start a name with a number.
(deftest e-1-1-basic-arithmetic
  (is (= 10 10))
  (is (= (+ 5 3 4) 12))
  (is (= (- 9 1) 8))
  (is (= (/ 6 2) 3))
  (is (= 10 10))
  (is (= (+ (* 2 4) (- 4 6)) 6)))

(def a 3)
(def b (+ a 1))

(deftest e-1-1-defs
  (is (= a 3))
  (is (= b 4))
  (is (= (+ a b (* a b)) 19))
  (is (= (= a b) false))
  (is (= (if (and (> a b) (< b (* a b)))
           a
           b)
         4))
  (is (= (cond (= a 4) 6
               (= b 4) (+ 6 7 a)
               :else 25)
         16))
  (is (= (+ 2 (if (> b a) b a))
         6))
  (is (= (* (cond (> a b) a
                  (< a b) b
                  :else -1)
            (+ a 1))
         16)))

; 1.2
(deftest e-1-2
  (is (= (/
          (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5)))))
          (* 3 (- 6 2) (- 2 7)))
         -0.24666666666666667)))

; 1.3
(defn square-sum-larger-two
  "Return the sum of the squares of the to larger numbers."
  [x, y, z]
  (reduce + (map #(* % %) (rest (sort [x y z])))))

(deftest e-1-3
  (is (= (square-sum-larger-two 4 5 6)
         61)))

; 1.4
(defn a-plus-abs-b
  [a b]
  ((if (> b 0) + -) a b))

(deftest e-1-4
  (is (= (a-plus-abs-b 1 1)
         2))
  (is (= (a-plus-abs-b 1 -1)
         2)))

; 1.5
(defn p [] (p))

(defn test-fn
  [x y]
  (if (= x 0) 0 y))

; Maximum call stack size exceeded
(deftest e-1-5
  (is (thrown? js/RangeError (test-fn 0 (p)))))

; 1.6
(defn new-if
  [predicate then-clause else-clause]
  (cond predicate then-clause
        :else else-clause))

(deftest e-1-6-new-if
  (is (= (new-if (= 2 3) 0 5) 5))
  (is (= (new-if (= 1 1) 0 5) 0)))

(defn average
  [x y]
  (/ (+ x y) 2))

(defn square
  [x]
  (* x x))

(defn abs
  [x]
  (if (> x 0) x (- x)))

(defn improve
  [guess x]
  (average guess (/ x guess)))

(defn good-enough?
  [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn sqrt-iter
  [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x)
               x)))


(defn sqrt-iter-new-if
  [guess x]
  ; Using `new-if` causes a `Maximum call stack size exceeded` error.
  ; In `new-if`, the `else` is always evaluated because Lisp follows applicative-order evaluation.
  ; That's why `if` needs to be a special form: to prevent the else from being evaluated at all.
  (new-if (good-enough? guess x)
          guess
          (sqrt-iter-new-if (improve guess x)
                            x)))

(defn sqrt
  [x]
  (sqrt-iter 1.0 x))

(deftest e-1-6-sqrt
  (is (= (sqrt 9) 3.00009155413138))
  (is (thrown? js/RangeError (sqrt-iter-new-if 1.0 9) 0)))

; 1.7

(deftest e-1-7
  ; The criteria for good-enough is that the difference between x and the square of the guess
  ; be less than 0.001. For small enough numbers, that will always be true after the first few
  ; iterations over the initial guess.
  ; Note: we can log the guesses using `(do stmt1 stmt2)`, as only stmt2 is returned.
  ; The following roots are the mostly the same, each suffering exactly 5 iterations:
  (is (= (sqrt 0.000000000000000006) 0.03125000000000006))
  (is (= (sqrt 0.0000000000000000123) 0.03125000000000013))
  (is (= (sqrt 0.000000000000003) 0.031250000000031974))
  ; For very large numbers, like the below, the test seems to recurse with the same guess for a
  ; long time then crash the program. This crashes recursing with 21356293.758311227 guess:
  (is (thrown? js/RangeError (sqrt 456091283091283) 0))
  ; This happens because limited precision means improving over the guess can not return a different
  ; value:
  (is (= (improve 21356293.758311227 456091283091283) 21356293.758311227)))

; 1.8

(defn cube
  [x]
  (* x x x))

(defn good-enough-cube?
  [guess x]
  (< (abs (- (cube guess) x)) 0.001))

(defn improve-cube
  [guess x]
  (/ (+ (/ x (square guess))
        (* 2 guess))
     3))

(defn cbrt-iter
  [guess x]
  (if (good-enough-cube? guess x)
    guess
    (cbrt-iter (improve-cube guess x)
               x)))

(defn cbrt
  [x]
  (cbrt-iter 1.0 x))

(deftest e-1-8
  (is (= (cbrt 9) 2.0801035255095734)))
