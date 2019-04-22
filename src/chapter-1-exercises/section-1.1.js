// 1.1
console.log(10);
console.log(5 + 3 + 4);
console.log(9 - 1);
console.log(6 / 2);
console.log((2 * 4) + (4 - 6));

const a = 3;
console.log(a);
const b = a + 1;
console.log(b);

console.log(a + b + (a * b));
console.log(a == b);

console.log((a > b) && (b < (a * b)) ? a : b);

console.log((() => {
  switch (4) {
    case a:
      return 6;
    case b:
      return 6 + 7 + a;
    default:
      return 25;
  }
})());

console.log(2 + ((b > a) ? b : a));

console.log((a + 1) *
  ((a > b) ? a : (a < b) ? b : -1)
);

// 1.2
console.log(
  (5 + 4 + (2 - (3 - (6 - 4 / 5))))
  / (3 * (6 - 2) * (2 - 7))
);

// 1.3
// Return the sum of the squares of the to larger numbers.
const squareSumLargerTwo = (x, y, z) =>
  [x, y, z].sort().slice(1).map(x => x * x).reduce((acc, curr) => acc + curr, 0);

console.log(squareSumLargerTwo(4, 5, 6));

// 1.4
const aPlusAbsB = (a, b) => b > 0 ? a + b : a - b;

console.log(aPlusAbsB(1, 1));
console.log(aPlusAbsB(1, -1));

// 1.5
const p = () => p();

const test = (x, y) => x == 0 ? 0 : y;

// RangeError: Maximum call stack size exceeded
// console.log(test(0, p()));

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

console.log(sqrt(9));

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

console.log(cbrt(9));
