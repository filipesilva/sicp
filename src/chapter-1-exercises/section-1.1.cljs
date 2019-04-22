; 1.1
(println 10)
(println (+ 5 3 4))
(println (- 9 1))
(println (/ 6 2))
(println (+ (* 2 4) (- 4 6)))

(println (def a 3))
(println (def b (+ a 1)))

(println (+ a b (* a b)))
(println (= a b))

(println (if (and (> a b) (< b (* a b))) a b));

(println (cond (= a 4) 6
               (= b 4) (+ 6 7 a)
               :else 25))

(println (+ 2 (if (> b a) b a)))

(println (* (cond (> a b) a
                  (< a b) b
                  :else -1)
            (+ a 1)))

; 1.2
(println (/
          (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5)))))
          (* 3 (- 6 2) (- 2 7))))

; 1.3
(defn square-sum-larger-two
  "Return the sum of the squares of the to larger numbers."
  [x, y, z]
  (reduce + (map #(* % %) (rest (sort [x y z])))))

(println (square-sum-larger-two 4 5 6))

; 1.4
(defn a-plus-abs-b
  [a b]
  (if (> b 0) + -) a b)
(println (a-plus-abs-b 1 1))
(println (a-plus-abs-b 1 -1))

; 1.5
(defn p [] (p))

(defn test-fn
  [x y]
  (if (= x 0) 0 y))

; Maximum call stack size exceeded
; (println (test-fn 0 (p)))

; 1.6
(defn new-if
  [predicate then-clause else-clause]
  (cond predicate then-clause
        :else else-clause))
(println (new-if (= 2 3) 0 5))
(println (new-if (= 1 1) 0 5))

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
          (sqrt-iter (improve guess x)
                     x)))

(defn sqrt
  [x]
  (sqrt-iter 1.0 x))

(println (sqrt 9))

; 1.7
; The criteria for good-enough is that the difference between x and the square of the guess
; be less than 0.001. For small enough numbers, that will always be true after the first few
; iterations over the initial guess.
; Note: we can log the guesses using `(do stmt1 stmt2)`, as only stmt2 is returned.
; The following roots are the mostly the same, each suffering exactly 5 iterations:

(println (sqrt 0.000000000000000006)) ; 0.03125000000000006
(println (sqrt 0.0000000000000000123)) ; 0.03125000000000013
(println (sqrt 0.000000000000003)) ; 0.031250000000031974

; For very large numbers, like the below, the test seems to recurse with the same guess for a
; long time then crash the program
; (println (sqrt 456091283091283)) ; crashes recursing with 21356293.758311227 guess
; This happens because limited precision means improving over the guess can not return a different
; value:
(improve 21356293.758311227 456091283091283) ; also 21356293.758311227

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

(println (cbrt 9))
