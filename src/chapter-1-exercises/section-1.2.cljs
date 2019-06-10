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

(defn fib-lookalike-recur
  [n]
  (if (< n 3)
    n
    (+ (fib-lookalike-recur (- n 1))
       (* 2 (fib-lookalike-recur (- n 2)))
       (* 3 (fib-lookalike-recur (- n 3))))))

(println (fib-lookalike-recur 10))

(defn fib-lookalike-iter
  [n]
  (defn fib-lookalike-iter-helper
    [n-1 n-2 n-3 counter]
    (if (= counter 0)
      n-1
      (fib-lookalike-iter-helper (+ n-1 (* 2 n-2) (* 3 n-3))
                                 n-1
                                 n-2
                                 (dec counter))))
  (fib-lookalike-iter-helper 2 1 0 (- n 2)))

(println (fib-lookalike-iter 10))

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

; 1.17
(println "# 1.17")
(defn double [n] (+ n n))
(defn halve [n] (/ n 2))

(defn fast-mult
  "Multiplication using log n steps, recursive."
  [b n]
  (cond (= 0 n) 0
        (even? n) (double (fast-mult b (halve n)))
        :else (+ b (fast-mult b (- n 1)))))

(println "## fast-mult")
(println (fast-mult 2 1))
(println (fast-mult 2 2))
(println (fast-mult 2 3))
(println (fast-mult 2 4))
(println (fast-mult 2 5))
(println (fast-mult 2 8))
(println (fast-mult 2 10))
(println (fast-mult 2 16))
(println (fast-mult 2 20))

; 1.18
(println "# 1.18")
(defn double [n] (+ n n))
(defn halve [n] (/ n 2))

(defn fast-mult-iter
  "Exponentiation using log n steps, iterative."
  [b n]
  (defn fast-mult-iter-helper [a b n]
    (cond (= n 0) a
          (even? n) (fast-mult-iter-helper a (double b) (halve n))
          :else (fast-mult-iter-helper (+ a b) b (- n 1))))
  (fast-mult-iter-helper 0 b n))


(println "## fast-mult-iter")
(println (fast-mult-iter 2 1))
(println (fast-mult-iter 2 2))
(println (fast-mult-iter 2 3))
(println (fast-mult-iter 2 4))
(println (fast-mult-iter 2 5))
(println (fast-mult-iter 2 8))
(println (fast-mult-iter 2 10))
(println (fast-mult-iter 2 16))
(println (fast-mult-iter 2 20))

; 1.19
; To solve (Tpq)^2 = T(p'q')
; Resolve (Tpq)^2 as the following system:
; a' = bq + aq + ap
; b' = bp + aq
; a'' = b'q + a'q + a'p
; b'' = b'p + a'q
; By substitution and focusing on b'', it will resolve to:
; a'' = b(q^2 + 2qp) + a(q^2 + 2qp) + a(p^2 + q^2)
; b'' = b(p^2 + q^2) + a(q^2 + 2qp)
; Where it can be seen that:
; p' = (p^2 + q^2)
; q' = (q^2 + 2qp)
; Note: looking more into this, it's called the Fibonnaci Q-matrix and uses linear algebra.
(println "# 1.19")
(defn square [n] (* n n))

(defn fib-iter [a b p q count]
  ; (println a b p q count)
  (cond (= count 0) b
        (even? count) (fib-iter a
                                b
                                (+ (square p) (square q))
                                (+ (square q) (* 2 p q))
                                (/ count 2))
        :else (fib-iter (+ (* b q) (* a q) (* a p))
                        (+ (* b p) (* a q))
                        p
                        q
                        (- count 1))))

(defn fib [n]
  (fib-iter 1 0 0 1 n))

(println (fib 0))
(println (fib 1))
(println (fib 2))
(println (fib 3))
(println (fib 4))
(println (fib 5))
(println (fib 6))
(println (fib 7))
(println (fib 8))
(println (fib 9))
(println (fib 10))

; 1.20
; applicative order evaluation (rem is evaluated only on the tail call):
; 1 rem     (gcd 206 40)
; 1 rem     (gcd 40 6)
; 1 rem     (gcd 6 4)
; 1 rem     (gcd 4 2)
; 0 rem     (gcd 2 0)
; 4 total

; normal order evaluation (rem is evaluated on the if condition and on the return):
; 0 rem     (gcd 206 40) 
; 1 rem     (gcd 40 (rem 206 40))
; 2 rem     (gcd (rem 206 40) (rem 40 (rem 206 40)))       
; 4 rem     (gcd (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))       
; 7+4 rem   (gcd (rem (rem 206 40) (rem 40 (rem 206 40))) 
;                (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))) 
; 18 total

(println "# 1.20")

(defn gcd [a b]
  ; (println a b)
  (if (= b 0)
    a
    (gcd b (rem a b))))


(println (gcd 206 40))
