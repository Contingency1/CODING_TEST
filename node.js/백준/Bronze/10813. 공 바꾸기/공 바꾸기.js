let fs = require("fs");
let [[n], ...second] = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((x) => +x));

const base = Array.from({length: n}, (x, i) => ++i);

second.forEach((x) => {
  const k = base[x[0] - 1];
  base[x[0] - 1] = base[x[1] - 1];
  base[x[1] - 1] = k;
});

console.log(...base);
