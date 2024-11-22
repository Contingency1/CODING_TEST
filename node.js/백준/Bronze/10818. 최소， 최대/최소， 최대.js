let fs = require("fs");
let input = fs.readFileSync("./dev/stdin").toString().trim();

const [first, second] = input.split("\n");

const answer = second.split(" ").sort((a, b) => +a - +b);

console.log(answer[0], answer[+first - 1]);