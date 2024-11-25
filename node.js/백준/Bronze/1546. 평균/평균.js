let fs = require("fs");
let [[amount], score] = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((x) => +x));

const max = Math.max(...score);

// 점수/M*100
score.forEach((x, i) => {
  score[i] = (x / max) * 100;
});
console.log(score.reduce((acc, cur) => acc + cur, 0) / amount);
