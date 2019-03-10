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
  ((if (> b 0) + -) a b))
(println (a-plus-abs-b 1 1))
(println (a-plus-abs-b 1 -1))

; 1.5

(defn p [] (p))

(defn test
  [x y]
  (if (= x 0) 0 y))

; Maximum call stack size exceeded
; (println (test 0 (p)))
