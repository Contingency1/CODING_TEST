let fs = require("fs");
let [number1, number2] = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .split(" ")
  .map((x) => x.split("").reverse().join(""));

console.log(number1 > number2 ? number1 : number2);
