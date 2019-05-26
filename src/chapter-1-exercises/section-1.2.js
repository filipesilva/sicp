// 1.9
// Answers are the same as in the clojure version.
// In JS we neither neither can redefine primitives like +, nor can + be a function name, so we
// use the name `add` instead.
const newAdd = (a, b) =>
  a == 0
    ? b
    : newAdd(a - 1, b) + 1;

console.log(newAdd(4, 5));

const anotherNewAdd = (a, b) =>
  a == 0
    ? b
    : anotherNewAdd(--a, ++b);

console.log(anotherNewAdd(4, 5));

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

console.log(fibLookalikeRecur(10));

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

console.log(fibLookalikeIter(10));

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

console.log(pascalRecur(0, 0));
console.log(pascalRecur(1, 1), pascalRecur(1, 1));
console.log(pascalRecur(2, 0), pascalRecur(2, 1), pascalRecur(2, 2));
console.log(pascalRecur(3, 0), pascalRecur(3, 1), pascalRecur(3, 2), pascalRecur(3, 3));
console.log(pascalRecur(4, 0), pascalRecur(4, 1), pascalRecur(4, 2), pascalRecur(4, 3),
  pascalRecur(4, 4));

// 1.13
// Math proof, see clojure version.

// 1.14
// Drawing and analysis, see clojure version.

// 1.15
// Analysis, see clojure version.

// 1.16
console.log('# 1.16');
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

console.log('## fastExpt');
console.log(fastExpt(2, 1));
console.log(fastExpt(2, 2));
console.log(fastExpt(2, 3));
console.log(fastExpt(2, 4));
console.log(fastExpt(2, 5));
console.log(fastExpt(2, 8));
console.log(fastExpt(2, 10));
console.log(fastExpt(2, 16));
console.log(fastExpt(2, 20));

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

console.log('## fastExptIter');
console.log(fastExptIter(2, 1));
console.log(fastExptIter(2, 2));
console.log(fastExptIter(2, 3));
console.log(fastExptIter(2, 4));
console.log(fastExptIter(2, 5));
console.log(fastExptIter(2, 8));
console.log(fastExptIter(2, 10));
console.log(fastExptIter(2, 16));
console.log(fastExptIter(2, 20));