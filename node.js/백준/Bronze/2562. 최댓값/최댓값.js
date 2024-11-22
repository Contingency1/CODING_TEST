let fs = require("fs");
let input = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => +x);

const MAX = Math.max(...input);

for (let i = 0; i < input.length; i++) {
  if (input[i] === MAX) {
    console.log(`${MAX}
${i + 1}`);
    break;
  }
}
