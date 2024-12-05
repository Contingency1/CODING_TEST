let fs = require("fs");
const input = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .filter((x) => x[x.length - 1] !== "P");

const GRADE = {
  "A+": 4.5,
  A0: 4.0,
  "B+": 3.5,
  B0: 3.0,
  "C+": 2.5,
  C0: 2.0,
  "D+": 1.5,
  D0: 1.0,
  F: 0.0,
};

let score = 0;

console.log(
  input.reduce((acc, cur) => {
    const space = cur.split(" ");
    score += +space[1];
    return (acc += +space[1] * GRADE[space[2]]);
  }, 0) / score
);
