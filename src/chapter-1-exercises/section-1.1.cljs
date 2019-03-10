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

(defn test
  [x y]
  (if (= x 0) 0 y))

; Maximum call stack size exceeded
; (println (test 0 (p)))

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
  ; (if (good-enough? guess x)
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
; TODO

; 1.8
; TODO