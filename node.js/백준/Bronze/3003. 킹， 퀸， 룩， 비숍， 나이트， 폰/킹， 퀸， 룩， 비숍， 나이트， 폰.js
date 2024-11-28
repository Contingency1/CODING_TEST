let fs = require("fs");
let input = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .split(" ")
  .map((x) => +x);

const base = [1, 1, 2, 2, 2, 8];
const answer = [];
input.forEach((x, i) => {
  answer.push(base[i] - x);
});

console.log(...answer);
