let fs = require("fs");
let input = fs.readFileSync("./dev/stdin").toString().trim().split("");

const base = [...input];
const reverse = input.reverse();
for (let i = 0; i < base.length; i++) {
  if (base[i] !== reverse[i]) {
    console.log("0");
    return 0;
  }
}
console.log("1");
