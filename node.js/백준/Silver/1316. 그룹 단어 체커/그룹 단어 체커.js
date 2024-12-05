let fs = require("fs");
const [_, ...rest] = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(""));

const unique = rest.map((x) => [...new Set(x)]);
let answer = 0;

for (let i = 0; i < rest.length; i++) {
  let index = 0;
  for (let j = 0; j < rest[i].length; j++) {
    if (unique[i][index] !== rest[i][j]) {
      index++;
      j--;
    }
    if (j === rest[i].length - 1) {
      answer++;
      break;
    }
    if (index > unique[i].length - 1) {
      break;
    }
  }
}

console.log(answer);
