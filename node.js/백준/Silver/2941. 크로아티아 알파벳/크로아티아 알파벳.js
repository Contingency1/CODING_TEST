let fs = require("fs");
const input = fs.readFileSync("./dev/stdin").toString().trim().split("");

const compare3 = ["d", "z", "="];
const compare2 = [
  ["c", "="],
  ["c", "-"],
  ["d", "-"],
  ["l", "j"],
  ["n", "j"],
  ["s", "="],
  ["z", "="],
];

for (let i = 0; i < input.length; i++) {
  if (i <= input.length - 3) {
    for (let j in compare3) {
      if (input[i + +j] !== compare3[j]) break;

      if (+j === 2) {
        input.splice(i, 3, "1");
        i--;
      }
    }
  }

  if (i <= input.length - 2) {
    compare2.forEach((x) => {
      for (let j in x) {
        if (input[i + +j] !== x[j]) break;

        if (+j === 1) {
          input.splice(i, 2, "1");
          i--;
        }
      }
    });
  }
}

console.log(input.length);
