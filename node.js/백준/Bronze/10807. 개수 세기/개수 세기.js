const fs = require('fs');
const input = fs.readFileSync("/dev/stdin").toString();

function solution(input) {
  const [arr, target] = input.split("\n").splice(1, 2);

  console.log(
    arr.split(" ").reduce((acc, cur) => (cur === target ? ++acc : acc), 0)
  );
}

solution(input)