let fs = require("fs");
let [_, ...rest] = fs.readFileSync("./dev/stdin").toString().trim().split(`\n`);

rest.forEach((x) => {
  console.log(x[0] + x[x.length - 1]);
});
