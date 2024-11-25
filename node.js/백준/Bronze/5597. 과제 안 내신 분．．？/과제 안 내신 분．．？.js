let fs = require("fs");
let input = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => +x)
  .sort((a, b) => a - b);

const base = Array.from({length: 30}, (_, i) => ++i);
base.forEach((x, i) => {
  if (x !== input[i]) {
    console.log(x);
    input.splice(i, 0, x);
  }
});
