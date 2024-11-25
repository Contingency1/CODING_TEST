let fs = require("fs");
let [arr, ...rest] = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((x) => +x));

const base = Array.from({length: arr[0]}, () => 0);

rest.forEach((x) => {
  for (let i = x[0] - 1; i <= x[1] - 1; i++) {
    base[i] = x[2];
  }
});

console.log(...base);
