let fs = require("fs");
let input = fs.readFileSync("./dev/stdin").toString().trim();

const answer = Array.from({length: 26}, () => -1);
const unique = [...new Set(input.split(""))];
//97 - 122
unique.forEach((x) => answer.splice(x.charCodeAt() - 97, 1, input.indexOf(x)));

console.log(...answer);
