const { equal, throws } = require('assert');

// 1.1
equal(10, 10);
equal(5 + 3 + 4, 12);
equal(9 - 1, 8);
equal((6 / 2), 3);
equal((2 * 4) + (4 - 6), 6);

const a = 3;
equal(a, 3);
const b = a + 1;
equal(b, 4);

equal(a + b + (a * b), 19);
equal(a == b, false);

// Language notes: ternary is looks a lot like lisp if.
equal((a > b) && (b < (a * b)) ? a : b, 4);

// Language notes: there's no good cond in JS unfortunately. Maybe chained ternary.
equal((() => {
  switch (4) {
    case a:
      return 6;
    case b:
      return 6 + 7 + a;
    default:
      return 25;
  }
})(), 16);

equal(2 + ((b > a) ? b : a), 6);

// Language notes: chained ternary mixed with other operations gets confusing fast.
equal((a + 1) *
  ((a > b)
    ? a : (a < b)
      ? b : -1),
  16);

// 1.2
// Language notes: comma between args makes this less legible. 
equal(
  (5 + 4 + (2 - (3 - (6 - 4 / 5))))
  / (3 * (6 - 2) * (2 - 7)),
  -0.22);

// 1.3
// Return the sum of the squares of the to larger numbers.
const squareSumLargerTwo = (x, y, z) =>
  [x, y, z].sort().slice(1).map(x => x * x).reduce((acc, curr) => acc + curr, 0);

equal(squareSumLargerTwo(4, 5, 6), 61);

// 1.4
const aPlusAbsB = (a, b) => b > 0 ? a + b : a - b;

equal(aPlusAbsB(1, 1), 2);
equal(aPlusAbsB(1, -1), 2);

// 1.5
const p = () => p();

const test = (x, y) => x == 0 ? 0 : y;

// RangeError: Maximum call stack size exceeded
throws(() => test(0, p()), /Maximum call stack size exceeded/);

// 1.6
// Not applicable because JS uses keywords and operators instead of special forms and you can't 
// make new drop-in ones. Thus it wouldn't make sense to try and replace `if` in the first place.
// We could make a `if` function and call that, and it would suffer from the same problem as the
// Lisp implementation (eagerly evaluating the else expression).
// Still worth it to list the functions though.
const average = (x, y) => (x + y) / 2;
const square = x => x * x;
const abs = x => x > 0 ? x : -x;
const improve = (guess, x) => average(guess, (x / guess));
const goodEnough = (guess, x) => abs(square(guess) - x) < 0.001;
const sqrtIter = (guess, x) =>
  goodEnough(guess, x)
    ? guess
    : sqrtIter(improve(guess, x), x);
const sqrt = x => sqrtIter(1.0, x);

equal(sqrt(9), 3.00009155413138);

// 1.7
// Same answer as the clojure version, since the algorithm doesn't change.

// 1.8
const cube = x => x * x * x;
const goodEnoughCube = (guess, x) => abs(cube(guess) - x) < 0.001;
const improveCube = (guess, x) => ((x / square(guess)) + 2 * guess) / 3;
const cbrtIter = (guess, x) =>
  goodEnoughCube(guess, x)
    ? guess
    : cbrtIter(improveCube(guess, x), x);
const cbrt = x => cbrtIter(1.0, x);

equal(cbrt(9), 2.0801035255095734);
