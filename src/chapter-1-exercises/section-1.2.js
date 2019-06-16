// 1.9
// Answers are the same as in the clojure version.
// In JS we neither neither can redefine primitives like +, nor can + be a function name, so we
// use the name `add` instead.
const newAdd = (a, b) =>
  a == 0
    ? b
    : newAdd(a - 1, b) + 1;

// console.log(newAdd(4, 5));

const anotherNewAdd = (a, b) =>
  a == 0
    ? b
    : anotherNewAdd(--a, ++b);

// console.log(anotherNewAdd(4, 5));

// 1.10
// This is mostly a analysis question that isn't related to the language.
// Below is Ackermans function, but the clojure version contains the analysis proper.
const A = (x, y) => {
  if (y == 0) { return 0; }
  else if (x == 0) { return (2 * y); }
  else if (y == 1) { return 2; }
  else {
    return A(x - 1, A(x, y - 1));
  }
}

// 1.11

const fibLookalikeRecur = n =>
  n < 3
    ? n
    : fibLookalikeRecur(n - 1) + 2 * fibLookalikeRecur(n - 2) + 3 * fibLookalikeRecur(n - 3);

// console.log(fibLookalikeRecur(10));

const fibLookalikeIter = n => {
  const fibLookalikeIterHelper = (nMinus1, nMinus2, nMinus3, counter) =>
    counter == 0
      ? nMinus1
      : fibLookalikeIterHelper(
        nMinus1 + 2 * nMinus2 + 3 * nMinus3,
        nMinus1,
        nMinus2,
        counter - 1
      );

  return fibLookalikeIterHelper(2, 1, 0, n - 2);
}

// console.log(fibLookalikeIter(10));

// 1.12
const pascalRecur = (line, column) => {
  if (column == 0) {
    return 1;
  } else if (column == line) {
    return 1;
  } else if (column > line) {
    return 0;
  } else {
    return pascalRecur(line - 1, column - 1) + pascalRecur(line - 1, column);
  }
}

// console.log(pascalRecur(0, 0));
// console.log(pascalRecur(1, 1), pascalRecur(1, 1));
// console.log(pascalRecur(2, 0), pascalRecur(2, 1), pascalRecur(2, 2));
// console.log(pascalRecur(3, 0), pascalRecur(3, 1), pascalRecur(3, 2), pascalRecur(3, 3));
// console.log(pascalRecur(4, 0), pascalRecur(4, 1), pascalRecur(4, 2), pascalRecur(4, 3),
//   pascalRecur(4, 4));

// 1.13
// Math proof, see clojure version.

// 1.14
// Drawing and analysis, see clojure version.

// 1.15
// Analysis, see clojure version.

// 1.16
const square = x => x * x;
const even = x => x % 2 == 0;

const fastExpt = (b, n) => {
  if (n == 0) {
    return 1;
  } else if (even(n)) {
    return square(fastExpt(b, n / 2));
  } else {
    return b * fastExpt(b, n - 1);
  }
}

// console.log('## fastExpt');
// console.log(fastExpt(2, 1));
// console.log(fastExpt(2, 2));
// console.log(fastExpt(2, 3));
// console.log(fastExpt(2, 4));
// console.log(fastExpt(2, 5));
// console.log(fastExpt(2, 8));
// console.log(fastExpt(2, 10));
// console.log(fastExpt(2, 16));
// console.log(fastExpt(2, 20));

const fastExptIter = (b, n) => {
  const fastExptIterHelper = (a, b, n) => {
    if (n == 0) {
      return a;
    } else if (even(n)) {
      return fastExptIterHelper(a, square(b), n / 2);
    } else {
      return fastExptIterHelper(a * b, b, n - 1);
    }
  }

  return fastExptIterHelper(1, b, n);
}

// console.log('## fastExptIter');
// console.log(fastExptIter(2, 1));
// console.log(fastExptIter(2, 2));
// console.log(fastExptIter(2, 3));
// console.log(fastExptIter(2, 4));
// console.log(fastExptIter(2, 5));
// console.log(fastExptIter(2, 8));
// console.log(fastExptIter(2, 10));
// console.log(fastExptIter(2, 16));
// console.log(fastExptIter(2, 20));

// 1.17 and 1.18
// These two are the same as 1.16, but replacing `square` with `double`, and the
// neutral element of `1` with `0`. There isn't much point in redoing these in JS.

// 1.19
// This exercise is mostly the math bit, which is described in the cljs resolution.

const fibIter = (a, b, p, q, n) => {
  if (n == 0) {
    return b;
  } else if (even(n)) {
    return fibIter(
      a,
      b,
      square(p) + square(q),
      square(q) + 2 * p * q,
      n / 2,
    );
  } else {
    return fibIter(
      b * q + a * q + a * p,
      b * p + a * q,
      p,
      q,
      n - 1,
    );
  }
}

const fib = n => fibIter(1, 0, 0, 1, n);

// console.log(fib(0));
// console.log(fib(1));
// console.log(fib(2));
// console.log(fib(3));
// console.log(fib(4));
// console.log(fib(5));
// console.log(fib(6));
// console.log(fib(7));
// console.log(fib(8));
// console.log(fib(9));
// console.log(fib(10));

// 1.20
const gcd = (a, b) => b == 0 ? a : gcd(b, a % b);

// console.log(gcd(206, 40));

// 1.21
const divides = (a, b) => b % a == 0;
const findDivisor = (n, testDivisor) => square(testDivisor) > n ? n :
  divides(testDivisor, n) ? testDivisor : findDivisor(n, testDivisor + 1);

const smallestDivisor = n => findDivisor(n, 2);

// console.log(smallestDivisor(199));
// console.log(smallestDivisor(1999));
// console.log(smallestDivisor(19999));

// 1.22
// The timing data does not confirm the premise that each 10x increase in number size results in a
// ~3x (roughly (sqrt 10)) increase in time. The timing for 10000000 shows a 3x to 8x increase over
// timing for 10000, but it should show ~30x instead (roughly (sqrt 1000));
// Might also be that the timing functions can't capture benchmark such small intervals correctly,
// with overheads for timing influencing the result.

const odd = x => x % 2 != 0;
const time = fn => {
  const start = process.hrtime.bigint();
  const res = fn();
  const end = process.hrtime.bigint();
  console.log(`Elapsed time: ${end - start} nanoseconds`)
  return res;
}
const prime = n => n == smallestDivisor(n);
const reportPrime = n => time(() => prime(n));
const startPrimeTest = n => prime(n) ? reportPrime(n) : false;
const timedPrimeTest = n => {
  console.log(n);
  return startPrimeTest(n);
}
const searchForPrimes = n => {
  const searchForPrimesHelper = (n, counter) => {
    if (counter > 0) {
      const wasPrime = timedPrimeTest(n);
      return searchForPrimesHelper(n + 2, counter - (wasPrime ? 1 : 0));
    }
  }
  return searchForPrimesHelper(odd(n) ? n : n + 1, 10);
}

// searchForPrimes(10000); // each prime takes between 8900 and 33000 nanoseconds
// searchForPrimes(100000);
// searchForPrimes(1000000);
// searchForPrimes(10000000); // each prime takes between 24800 and 272000 nanoseconds
// searchForPrimes(1000000000); // Maximum call stack exceeded

// 1.23
// Saw ratios between 8x and 1.5x (prime/notSoFastPrime). notSoFastPrime is faster.

const reportPrimeWithFn = (n, primeFn) => time(() => primeFn(n));
const testKnownPrimes = primeFn => {
  reportPrimeWithFn(10007, primeFn);
  reportPrimeWithFn(10009, primeFn);
  reportPrimeWithFn(10037, primeFn);
  reportPrimeWithFn(100003, primeFn);
  reportPrimeWithFn(100019, primeFn);
  reportPrimeWithFn(100043, primeFn);
  reportPrimeWithFn(1000003, primeFn);
  reportPrimeWithFn(1000033, primeFn);
  reportPrimeWithFn(1000037, primeFn);
  reportPrimeWithFn(10000019, primeFn);
  reportPrimeWithFn(10000079, primeFn);
  reportPrimeWithFn(10000103, primeFn);
}

// JS notes: this function syntax is very succint. More succint than CLJS.
const nextDivisor = testDivisor => testDivisor == 2 ? 3 : testDivisor + 2;
const fastFindDivisor = (n, testDivisor) => square(testDivisor) > n ? n :
  divides(testDivisor, n) ? testDivisor : fastFindDivisor(n, nextDivisor(testDivisor));
const fastSmallestDivisor = n => fastFindDivisor(n, 2);
const notSoFastPrime = n => n == fastSmallestDivisor(n);

// console.log('prime');
// testKnownPrimes(prime);
// console.log('notSoFastPrime');
// testKnownPrimes(notSoFastPrime);

// 1.24
// Similar results as the CLJS version. Can't calculate the constant well, but it takes way
// less time than the regular prime.

const randInt = max => Math.floor(Math.random() * Math.floor(max));

// JS notes: 
// - chained ternaries with the colon on separate paragraphs look a lot like CLJS conds. But the
// auto formatter doesn't let the colons be beneath each other.
// - CLJS prefix notation makes it much easier to reason about operations. The second and third
// (else) conditions below look different because of how square is a function but multiplying by
// base is using an operators. But in CLJS it's very clear that they are similar because
// the structure is the same.
// - nested function definitions prevent using the expression (block-less) syntax for arrow 
// functions.
const expmod = (base, exp, m) => exp == 0 ? 1
  : even(exp) ? square(expmod(base, exp / 2, m)) % m
    : (base * expmod(base, exp - 1, m)) % m;
const fermatTest = n => {
  const tryIt = a => expmod(a, n, n) == a;
  return tryIt(1 + randInt(n - 1));
}
const fastPrime = (n, times) => times == 0 ? true
  : fermatTest(n) ? fastPrime(n, times - 1)
    : false;
const fastPrime5Iter = n => fastPrime(n, 5);

// console.log('fastPrime5Iter');
// testKnownPrimes(fastPrime5Iter);