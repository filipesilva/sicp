(ns sicp.exercises.section_1_2
  (:require [cljs.test :refer-macros [deftest is]]))

; 1.9
(defn new+
  [a b]
  (if (= a 0)
    b
    (inc (new+ (dec a) b))))

(deftest e-1-9-new+
  (is (= (new+ 4 5) 9)))

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

(deftest e-1-9-another-new+
  (is (= (another-new+ 4 5) 9)))

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

(deftest e-1-10-A
  (is (= (A 2 4) 65536))
  (is (= (A 3 3) 65536)))

(defn k
  "Computes 5n^2"
  [n]
  (* 5 n n))

(deftest e-1-10-k
  (is (= (k 2) 20)))


(defn f
  "Computes 2n"
  [n]
  (A 0 n))

(deftest e-1-10-f
  (is (= (f 1) 2))
  (is (= (f 2) 4))
  (is (= (f 3) 6))
  (is (= (f 4) 8))
  (is (= (f 5) 10))
  (is (= (f 6) 12))
  (is (= (f 7) 14)))

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

(deftest e-1-10-g
  (is (= (g 1) 2))
  (is (= (g 2) 4))
  (is (= (g 3) 8))
  (is (= (g 4) 16))
  (is (= (g 5) 32))
  (is (= (g 6) 64))
  (is (= (g 7) 128)))

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

(deftest e-1-10-h
  (is (= (h 1) 2))
  (is (= (h 2) 4))
  (is (= (h 3) 16))
  (is (= (h 4) 65536)))

; 1.11

(defn fib-lookalike-recur
  [n]
  (if (< n 3)
    n
    (+ (fib-lookalike-recur (- n 1))
       (* 2 (fib-lookalike-recur (- n 2)))
       (* 3 (fib-lookalike-recur (- n 3))))))

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

(deftest e-1-11
  (is (= (fib-lookalike-recur 10) 1892))
  (is (= (fib-lookalike-iter 10) 1892)))

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

(deftest e-1-12
  (is (= (pascal-recur 0 0) 1))
  (is (= (pascal-recur 1 0) 1)) (is (= (pascal-recur 1 1) 1))
  (is (= (pascal-recur 2 0) 1)) (is (= (pascal-recur 2 1) 2)) (is (= (pascal-recur 2 2) 1))
  (is (= (pascal-recur 3 0) 1)) (is (= (pascal-recur 3 1) 3)) (is (= (pascal-recur 3 2) 3))
  (is (= (pascal-recur 3 3) 1))
  (is (= (pascal-recur 4 0) 1)) (is (= (pascal-recur 4 1) 4)) (is (= (pascal-recur 4 2) 6))
  (is (= (pascal-recur 4 3) 4)) (is (= (pascal-recur 4 4) 1)))

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
(defn square [n] (* n n))

(defn fast-expt
  "Exponentiation using log n steps, recursive."
  [b n]
  (cond (= 0 n) 1
        (even? n) (square (fast-expt b (/ n 2)))
        :else (* b (fast-expt b (- n 1)))))

(deftest e-1-16-fast-expt
  (is (= (fast-expt 2 1) 2))
  (is (= (fast-expt 2 2) 4))
  (is (= (fast-expt 2 3) 8))
  (is (= (fast-expt 2 4) 16))
  (is (= (fast-expt 2 5) 32))
  (is (= (fast-expt 2 8) 256))
  (is (= (fast-expt 2 10) 1024))
  (is (= (fast-expt 2 16) 65536))
  (is (= (fast-expt 2 20) 1048576)))


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

(deftest e-1-16-fast-expt-iter
  (is (= (fast-expt-iter 2 1) 2))
  (is (= (fast-expt-iter 2 2) 4))
  (is (= (fast-expt-iter 2 3) 8))
  (is (= (fast-expt-iter 2 4) 16))
  (is (= (fast-expt-iter 2 5) 32))
  (is (= (fast-expt-iter 2 8) 256))
  (is (= (fast-expt-iter 2 10) 1024))
  (is (= (fast-expt-iter 2 16) 65536))
  (is (= (fast-expt-iter 2 20) 1048576)))


; 1.17
(defn halve [n] (/ n 2))
(defn dbl [n] (* n 2))

(defn fast-mult
  "Multiplication using log n steps, recursive."
  [b n]
  (cond (= 0 n) 0
        (even? n) (dbl (fast-mult b (halve n)))
        :else (+ b (fast-mult b (- n 1)))))

(deftest e-1-17
  (is (= (fast-mult 2 1) 2))
  (is (= (fast-mult 2 2) 4))
  (is (= (fast-mult 2 3) 6))
  (is (= (fast-mult 2 4) 8))
  (is (= (fast-mult 2 5) 10))
  (is (= (fast-mult 2 8) 16))
  (is (= (fast-mult 2 10) 20))
  (is (= (fast-mult 2 16) 32))
  (is (= (fast-mult 2 20) 40)))

; 1.18

(defn fast-mult-iter
  "Exponentiation using log n steps, iterative."
  [b n]
  (defn fast-mult-iter-helper [a b n]
    (cond (= n 0) a
          (even? n) (fast-mult-iter-helper a (dbl b) (halve n))
          :else (fast-mult-iter-helper (+ a b) b (- n 1))))
  (fast-mult-iter-helper 0 b n))

(deftest e-1-18
  (is (= (fast-mult-iter 2 1) 2))
  (is (= (fast-mult-iter 2 2) 4))
  (is (= (fast-mult-iter 2 3) 6))
  (is (= (fast-mult-iter 2 4) 8))
  (is (= (fast-mult-iter 2 5) 10))
  (is (= (fast-mult-iter 2 8) 16))
  (is (= (fast-mult-iter 2 10) 20))
  (is (= (fast-mult-iter 2 16) 32))
  (is (= (fast-mult-iter 2 20) 40)))

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

(deftest e-1-19
  (is (= (fib 0) 0))
  (is (= (fib 1) 1))
  (is (= (fib 2) 1))
  (is (= (fib 3) 2))
  (is (= (fib 4) 3))
  (is (= (fib 5) 5))
  (is (= (fib 6) 8))
  (is (= (fib 7) 13))
  (is (= (fib 8) 21))
  (is (= (fib 9) 34))
  (is (= (fib 10) 55)))


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

(defn gcd [a b]
  ; (println a b)
  (if (= b 0)
    a
    (gcd b (rem a b))))

(deftest e-1-20
  (is (= (gcd 206 40) 2)))

; 1.21

(defn divides? [a b]
  (= (rem b a) 0))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(deftest e-1-21
  (is (= (smallest-divisor 199) 199))
  (is (= (smallest-divisor 1999) 1999))
  (is (= (smallest-divisor 19999) 7)))

; 1.22
; Used 10000 as the smallest because computation is so fast that bigger numbers are necessary to
; get timing numbers.
; The timing data does not confirm the premise that each 10x increase in number size results in a
; ~3x (roughly (sqrt 10)) increase in time. The timing for 10000000 shows a 10x increase over
; timing for 10000, but it should show ~30x instead (roughly (sqrt 1000));
; Might also be that the timing functions can't capture benchmark such small intervals correctly,
; with overheads for timing influencing the result. 
; The first prime always takes way longer to compute. This likely has to do with the JS JIT
; compiler optimizes these code paths after the first run.

(defn prime? [n]
  (= n (smallest-divisor n)))

; Variation on the function in the book, because clojure doesn't have the runtime function.
(defn report-prime [n]
  (println n)
  (time (prime? n)))

(defn start-prime-test [n]
  (if (prime? n)
    (report-prime n)
    false))


(defn search-for-primes
  "Print the 6 taken to compute the 10 first primes found after n."
  [n]
  (defn search-for-primes-helper [n counter]
    (if (> counter 0)
      (let [was-prime (start-prime-test n)]
        (search-for-primes-helper (+ n 2) (- counter (if was-prime 1 0))))))
  (search-for-primes-helper (if (odd? n) n (+ n 1)) 3))

; (search-for-primes 10000) ; each prime takes between 0.1 and 0.3 msecs
; (search-for-primes 100000) ; 
; (search-for-primes 1000000) ; 
; (search-for-primes 10000000) ; each prime takes between 1 and 3 msecs
; (search-for-primes 100000000) ; Maximum call stack exceeded

; 1.23
; Saw ratios between 10x and 1.5x (prime?/not-so-fast-prime?). not-so-fast-prime? is faster.
; But there's so much variance in these measurements that it's hard to get good data.
; Would need bigger numbers and a way to not hit the stack limit to get accurate numbers.

(defn report-prime-with-fn [n prime-fn]
  (println n)
  (time (prime-fn n)))

(defn test-known-primes [prime-fn]
  (report-prime-with-fn 10007 prime-fn)
  (report-prime-with-fn 10009 prime-fn)
  (report-prime-with-fn 10037 prime-fn)
  (report-prime-with-fn 100003 prime-fn)
  (report-prime-with-fn 100019 prime-fn)
  (report-prime-with-fn 100043 prime-fn)
  (report-prime-with-fn 1000003 prime-fn)
  (report-prime-with-fn 1000033 prime-fn)
  (report-prime-with-fn 1000037 prime-fn)
  (report-prime-with-fn 10000019 prime-fn)
  (report-prime-with-fn 10000079 prime-fn)
  (report-prime-with-fn 10000103 prime-fn))

(defn next-divisor [test-divisor]
  (if (= test-divisor 2) 3 (+ test-divisor 2)))

(defn fast-find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (next-divisor test-divisor))))

(defn fast-smallest-divisor [n]
  (fast-find-divisor n 2))

(defn not-so-fast-prime? [n]
  (= n (fast-smallest-divisor n)))

; (println "prime?")
; (test-known-primes prime?)
; (println "not-so-fast-prime?")
; (test-known-primes not-so-fast-prime?)


; 1.24
; n=10,000,000 is 1,000 times bigger than n=10,000. According to O(log(n)) growth, doubling the
; problem size only increases the resources by a constant amount.
; 1000 times bigger is roughly 2^10 (1024), so you can also say it has doubled 10 times.
; I don't really know what the constant is but I could find out by subtracting the time it takes
; to calculate (fast-prime?-5-iter n) from (fast-prime?-5-iter 2n). That constant times 10 is what
; I expect primes near 1,000,000 to take to calculate versus primes near 1,000. But the numbers
; vary so much between even consecutive primes (e.g. 0.096300 followed by 0.155900 msecs) that it is
; difficult to calculate.
; fast-prime?-5-iter took 0.155900 msecs for 10037 and 0.188599 msecs for 10000079.
; I expected something a bit less modest, like 2x, because log2(10000) is 13.2 and log2 (10000000)
; is 23.
; By comparison, prime? took 0.109700 msecs for 10037 and 1.175900 msecs for 10000079.
; We can see that fast-prime?-5-iter is much faster, but the difference between calculating the
; two primes is so small that overheads, like the timing code, take most of the time.
; The real time it takes for the computer to perform these computations might also be much smaller
; than predicted due to the type of calculations involved. Doubling is an highly optimized operation
; because of the nature of binary representations.

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        :else  (rem (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (rand-int (- n 1)))))

(defn fast-prime? [n times]
  (cond (= times 0) true
        (fermat-test n) (fast-prime? n (- times 1))
        :else false))

(defn fast-prime?-5-iter [n]
  (fast-prime? n 5))

; (println "fast-prime?-5-iter")
; (test-known-primes fast-prime?-5-iter)

; 1.25
; The original expmod uses the following property: x * y mod m = ((x mod m)*(y mod m)) mod m.
; Using this property when computing the intermediate values ensures that no intermediate value
; is much bigger than m, as described in footnote 46.
; expmod-mod doesn't use this property and attempts to compute the full exponential up front.
; Dependending on the language implementation, numbers might have different maximum values and 
; precision. It's much easier to reach the maximum or lose precision with expmod-mod because
; it computes a much larger number.
; CLJS compiles down to JS and is subject to its number precision. Computing (expmod 10 10000 4)
; works, but using expmod-mod for the same computation does not yield a number.
; This is because calculating (fast-expt 10 10) is still accurate (10000000000), while 
; (fast-expt 10 100) loses precision (9.999999999999992e+99) and (fast-expt 10 1000) returns 
; the value for Infinity.

(defn expmod-mod [base exp m]
  (rem (fast-expt base exp) m))

(deftest e-1-25
  (is (= (expmod 10 10000 4) 0))
  (is (js/isNaN (expmod-mod 10 10000 4)))
  (is (= (fast-expt 10 10000) js/Infinity))
  (is (= (fast-expt 10 10) 10000000000))
  (is (= (fast-expt 10 100) 9.999999999999992e+99))
  (is (= (fast-expt 10 1000) js/Infinity)))

; 1.26
; The purpose of halving the exponent followed by square the result in the even case was to
; repeatedly halve the number of operations needed to perform the computation, thus achieving
; O(log n).
; But in this variation, the even case also doubles the number of expmod calls, negating the 
; benefit. The even case is now performing the same number of operations as the else case,
; bringing it back to O(log n).

; 1.27
; Can confirm, the Carmichael numbers do fool the fermat test.

(defn full-fermat-test [n]
  (defn helper [a]
    (cond (= a 0) true
          (= (expmod a n n) a) (helper (dec a))
          :else false))
  (helper (dec n)))

(deftest e-1-27
  (is (= (full-fermat-test 561) true))
  (is (= (prime? 561) false))
  (is (= (full-fermat-test 1105) true))
  (is (= (prime? 1105) false))
  (is (= (full-fermat-test 1729) true))
  (is (= (prime? 1729) false))
  (is (= (full-fermat-test 2465) true))
  (is (= (prime? 2465) false))
  (is (= (full-fermat-test 2821) true))
  (is (= (prime? 2821) false))
  (is (thrown? js/RangeError (full-fermat-test 6601))) ; should be true, but busts the stack.
  (is (= (prime? 6601) false)))

; 1.28
; Can confirm the miller-rabin variant works. 
; Also found the exercise very hard to understand.

; Separate the test itself from the random attempt, so we can do a random and a full version.
(defn miller-rabin-fermat-test [n a]
  (defn nontrivial-sqrt [x m]
    (cond (= x 1) false
          (= x (dec m)) false
          (= (rem (square x) m) 1) true
          :else false))
  (defn maybe-exit-early [x m]
    (if (nontrivial-sqrt x m)
      ; Exit early by returning 0 directly. This will always fail the test.
      0
      (rem (square x) m)))
  (defn mr-expmod [base exp m]
    (cond (= exp 0) 1
          (even? exp) (maybe-exit-early (mr-expmod base (/ exp 2) m) m)
          :else  (rem (* base (mr-expmod base (- exp 1) m)) m)))
  (= (mr-expmod a (dec n) n) 1))

(defn random-mr-fermat-test [n]
  (miller-rabin-fermat-test n (+ 1 (rand-int (- n 1)))))

(defn full-mr-fermat-test [n]
  (letfn [(helper [a]
            (cond (= a 0) true
                  (miller-rabin-fermat-test n a) (helper (dec a))
                  :else false))]
    (helper (dec n))))

; Language notes: I tried adding a deftest inside this function but it doesn't work (e.g. 
; a false assertion doesn't fail the test process).
(defn compare-prime-results [n & {:keys [charmichaelNr?] :or {charmichaelNr? false}}]
  "Compares the results of three prime checking functions, one of which is fooled by 
  Charmichael numbers."
  (and
   ((if charmichaelNr? not identity) (full-mr-fermat-test n))
   (full-fermat-test n)
   ((if charmichaelNr? not identity) (prime? n))))

(deftest e-1-28
  (is (compare-prime-results 13))
  (is (compare-prime-results 1009))
  (is (compare-prime-results 1013))
  (is (compare-prime-results 1019))
  (is (compare-prime-results 561 :charmichaelNr? true))
  (is (compare-prime-results 1105 :charmichaelNr? true))
  (is (compare-prime-results 1729 :charmichaelNr? true))
  (is (compare-prime-results 2465 :charmichaelNr? true))
  (is (compare-prime-results 2821 :charmichaelNr? true))
  (is (thrown? js/RangeError (compare-prime-results 6601 :charmichaelNr? true))))

; 1.29
(defn cube [x]
  (* x x x))

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn integral [f a b dx]
  (letfn [(add-dx [x] (+ x dx))]
    (* (sum f (+ a (/ dx 2.0)) add-dx b)
       dx)))

; Language notes: 
; - the right way to store the value of h here would be let, but we haven't
; yet learned it in the book. Using a helper closure does the same thing.
; - using let would also help reduce duplication of (y k).
; - using or would be better than two cond cases. Or maybe it wouldn't really.
(defn simpson-integral [f a b n]
  (letfn [(helper [h]
            (letfn [(y [k] (f (+ a (* k h))))
                    (term [k]
                      (cond (= k 0) (y k)
                            (= k n) (y k)
                            (even? k) (* 2 (y k))
                            :else (* 4 (y k))))]
              (* (/ h 3)
                 (sum term 0 inc n))))]
    (helper (/ (- b a) n))))

(deftest e-1-29
  (is (= (integral cube 0 1 0.01) 0.24998750000000042))
  (is (= (integral cube 0 1 0.001) 0.249999875000001))
  (is (= (simpson-integral cube 0 1 100) 0.24999999999999992))
  (is (= (simpson-integral cube 0 1 1000) 0.2500000000000003)))

; 1.30

; Language notes: In most Lisps, we would have iter instead of recur in the recursion call.
; recur is a clojure special form to enable similar functionality to tail-call 
; elimination (TCE) without TCE actually being supported in the host language.
; Javascript does not have TCE but because of recur we can avoid Maximum call stack exceeded errors.
; Chapter 1.2.1 (Linear recursion and Iteration) does a great job explaining what TCE is all about. 
(defn sum-iter [term a next b]
  (defn sum-iter-helper [a result]
    (if (> a b)
      result
      (recur (next a) (+ result (term a)))))
  (sum-iter-helper a 0))

(defn integral-given-sum [f a b dx sum]
  (letfn [(add-dx [x] (+ x dx))]
    (* (sum f (+ a (/ dx 2.0)) add-dx b)
       dx)))

(deftest e-1-30
  (is (= (integral-given-sum cube 0 1 0.001 sum) 0.249999875000001))
  (is (= (integral-given-sum cube 0 1 0.001 sum-iter) 0.24999987500000073)))

; 1.31

; No need to define it as its already in cljs.core. But this is what it would look like.
; (defn identity [x] x)

(defn product [term a next b]
  (if (> a b)
    1
    (* (term a)
       (product term (next a) next b))))

(defn product-iter [term a next b]
  (defn product-iter-helper [a result]
    (if (> a b)
      result
      (recur (next a) (* result (term a)))))
  (product-iter-helper a 1))

(defn factorial-given-product [n product]
  (product identity 1 inc n))

(deftest e-1-31
  (is (= (factorial-given-product 5 product) 120))
  (is (=  (factorial-given-product 5 product-iter) 120)))