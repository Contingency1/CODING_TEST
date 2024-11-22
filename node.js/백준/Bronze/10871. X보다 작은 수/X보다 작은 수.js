let fs = require("fs");
let input = fs.readFileSync("./dev/stdin").toString().trim();

const [arr, target] = input.split("\n");

console.log(...target.split(" ").filter((x) => +x < +arr.split(" ")[1]));
