let fs = require("fs");
let input = fs.readFileSync("./dev/stdin").toString().trim();

console.log(input.split(" ").filter((x) => x !== "").length);
