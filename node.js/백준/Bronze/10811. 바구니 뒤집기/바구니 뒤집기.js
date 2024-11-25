let fs = require("fs");
let [[n], ...rest] = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((x) => +x));

const base = Array.from({length: n}, (_, i) => ++i);

rest.forEach((x) => {
  const reverse = base.slice(x[0] - 1, x[1]).reverse();
  base.splice(x[0] - 1, x[1] - x[0] + 1, ...reverse);
});

console.log(...base);
