let fs = require("fs");
let input = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .toUpperCase()
  .split("");

const obj = {};

input.forEach((x) => {
  obj[x] >= 0 ? obj[x]++ : (obj[x] = 1);
});

const array = Object.values(obj);
const max = Math.max(...array);

const answer = Object.keys(obj).filter((x) => obj[x] === max);
answer.length === 1 ? console.log(answer[0]) : console.log("?");
