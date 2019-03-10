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
function squareSumLargerTwo(x, y, z) {
  return [x, y, z].sort().slice(1).map(x => x * x).reduce((acc, curr) => acc + curr, 0);
}

console.log(squareSumLargerTwo(4, 5, 6));

// 1.4
function aPlusAbsB(a, b) {
  return b > 0 ? a + b : a - b;
}
console.log(aPlusAbsB(1, 1));
console.log(aPlusAbsB(1, -1));

// 1.5
function p() {
  return p();
}

function test(x, y) {
  return x == 0 ? 0 : y;
}

// RangeError: Maximum call stack size exceeded
// console.log(test(0, p()));