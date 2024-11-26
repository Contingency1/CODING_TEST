let fs = require("fs");
let [_, ...rest] = fs.readFileSync("./dev/stdin").toString().trim().split("\n");

rest.map((x) => {
  let answer = "";
  x = x.split(" ");

  [...x[1]].forEach((y) => {
    answer += y.repeat(x[0]);
  });

  console.log(answer);
});
