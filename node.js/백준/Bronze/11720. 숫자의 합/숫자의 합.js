let fs = require("fs");
let [_, number] = fs.readFileSync("./dev/stdin").toString().trim().split("\n");

console.log(number.split("").reduce((acc, cur) => +acc + +cur, 0));
