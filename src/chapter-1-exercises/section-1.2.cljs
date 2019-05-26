; 1.9
(defn new+
  [a b]
  (if (= a 0)
    b
    (inc (new+ (dec a) b))))

(println (new+ 4 5))
; (+ 4 5)
; (inc (+ 3 5))
; (inc (inc (+ 2 5)))
; (inc (inc (inc (+ 1 5))))
; (inc (inc (inc (inc (+ 0 5)))))
; (inc (inc (inc (inc 5)))
; (inc (inc (inc 6))
; (inc (inc 7))
; (inc 8)
; 9
; This process is recursive.

(defn another-new+
  [a b]
  (if (= a 0)
    b
    (another-new+ (dec a) (inc b))))

(println (another-new+ 4 5))

; (+ 4 5)
; (+ 3 6)
; (+ 2 7)
; (+ 1 8)
; (+ 0 9)
; 9
; This process is iterative.

; 1.10
(defn A
  "Ackermans function.
  A(x y) calls `A(x-1 `2), where the part between ` is repeated y-1 times (with matching parens).
  e.g A(x 4) is A(x-1 A(x-1 A(x-1 2)))."
  [x y]
  (cond (= y 0) 0
        (= x 0) (* 2 y)
        (= y 1) 2
        :else (A (- x 1)
                 (A x (- y 1)))))

(println (A 1 10))
(println (A 2 4))
(println (A 3 3))

(defn k
  "Computes 5n^2"
  [n]
  (* 5 n n))
(println "k" (k 2))

; (A 0 4) 
; (* 2 4)
(defn f
  "Computes 2n"
  [n]
  (A 0 n))
(println "f" (f 1) (f 2) (f 3) (f 4) (f 5) (f 6) (f 7)) ; f 2 4 6 8 10 12 14

; (A 1 4)
; (A 0 (A 1 3))
; (A 0 (A 0 (A 1 2)))
; (A 0 (A 0 (A 0 (A 1 1))))
; (A 0 (A 0 (A 0 2))))
; (A 0 (A 0 4))
; (A 0 8)
; 16
(defn g
  "Computes 2^n. 
  Or: computes (2*)(repeat n times)1. e.g. (g 4) is 2*2*2*2*1."
  [n]
  (A 1 n))
(println "g" (g 1) (g 2) (g 3) (g 4) (g 5) (g 6) (g 7)) ; prints g 2 4 8 16 32 64 128

; (A 2 4)
; (A 1 (A 2 3))
; (A 1 (A 1 (A 2 2)))
; (A 1 (A 1 (A 1 (A 2 1))))
; (A 1 (A 1 (A 1 2))))
; (A 1 (A 1 4))
; (A 1 16)
; 65536
(defn h
  "Computes (2^)(repeat n times)1. e.g. (h 4) is 2^2^2^2^1."
  [n]
  (A 2 n))
(println "h" (h 1) (h 2) (h 3) (h 4)) ; h 2 4 16 65536


; 1.11

(defn fib-lookalive-recur
  [n]
  (if (< n 3)
    n
    (+ (fib-lookalive-recur (- n 1))
       (* 2 (fib-lookalive-recur (- n 2)))
       (* 3 (fib-lookalive-recur (- n 3))))))

(println (fib-lookalive-recur 10))

(defn fib-lookalive-iter
  [n]
  (defn fib-lookalive-iter-helper
    [n-1 n-2 n-3 counter]
    (if (= counter 0)
      n-1
      (fib-lookalive-iter-helper (+ n-1 (* 2 n-2) (* 3 n-3))
                                 n-1
                                 n-2
                                 (dec counter))))
  (fib-lookalive-iter-helper 2 1 0 (- n 2)))

(println (fib-lookalive-iter 10))

; 1.12
(defn pascal-recur
  ; imagine the pascal triagle as a left-aligned in a grid, e.g.
  ; 1
  ; 1 1
  ; 1 2 1
  [line column]
  (cond (= column 0) 1 ; left edge is 1.
        (= column line) 1 ; right edge is also 1.
        (> column line) 0 ; this should be an error, but I haven't looked into errors yet.
        ; otherwise add the column-1 and column elements of the line above.
        :else (+ (pascal-recur (dec line) (dec column)) (pascal-recur (dec line) column))))

(println (pascal-recur 0 0))
(println (pascal-recur 1 0) (pascal-recur 1 1))
(println (pascal-recur 2 0) (pascal-recur 2 1) (pascal-recur 2 2))
(println (pascal-recur 3 0) (pascal-recur 3 1) (pascal-recur 3 2) (pascal-recur 3 3))
(println (pascal-recur 4 0) (pascal-recur 4 1) (pascal-recur 4 2) (pascal-recur 4 3)
         (pascal-recur 4 4))

; 1.13
; Fiddled around with this for a couple of hours. Went to check a couple of solutions,
; saw that I wasn't that close. Was reminded that I really don't like pure math.


; 1.14
; Drew a tree similar to fibonacci. I guess the number of steps is also O(theta(phi^n)), and space
; is O(n), like fibonacci. I'm pretty sure this is common for all tree recursive of branching
; factor 2.

; 1.15
; a) 4 times, 12.15/3/3/3/3/3 = 0.05, which is when p isn't called again.
; b) O(n) for both space and time, like the factorial.

; 1.16
(println "# 1.16")
(defn square [n] (* n n))

(defn fast-expt
  "Exponentiation using log n steps, recursive."
  [b n]
  (cond (= 0 n) 1
        (even? n) (square (fast-expt b (/ n 2)))
        :else (* b (fast-expt b (- n 1)))))

(println "## fast-expt")
(println (fast-expt 2 1))
(println (fast-expt 2 2))
(println (fast-expt 2 3))
(println (fast-expt 2 4))
(println (fast-expt 2 5))
(println (fast-expt 2 8))
(println (fast-expt 2 10))
(println (fast-expt 2 16))
(println (fast-expt 2 20))

; Found a solution, then went to compare it with others and found that mine would break down on 
; (f 2 5) and (f 2 10), and others. The solution below isn't my original one.
(defn fast-expt-iter
  "Exponentiation using log n steps, iterative."
  [b n]
  (defn fast-expt-iter-helper [a b n]
    (cond (= n 0) a
          (even? n) (fast-expt-iter-helper a (square b) (/ n 2))
          :else (fast-expt-iter-helper (* a b) b (- n 1))))
  (fast-expt-iter-helper 1 b n))


(println "## fast-expt-iter")
(println (fast-expt-iter 2 1))
(println (fast-expt-iter 2 2))
(println (fast-expt-iter 2 3))
(println (fast-expt-iter 2 4))
(println (fast-expt-iter 2 5))
(println (fast-expt-iter 2 8))
(println (fast-expt-iter 2 10))
(println (fast-expt-iter 2 16))
(println (fast-expt-iter 2 20))
