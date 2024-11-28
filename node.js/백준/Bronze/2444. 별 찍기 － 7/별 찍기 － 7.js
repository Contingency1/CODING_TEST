let fs = require("fs");
let input = +fs.readFileSync("./dev/stdin").toString().trim();

let star = "*";

for (let i = 1; i <= 2 * input - 1; i++) {
  let space = "";
  if (i <= input) {
    for (let j = input - i - 1; j >= 0; j--) {
      space += " ";
    }
    i !== 1 ? (star += "**") : null;

    console.log(space + star);
  } else {
    for (let j = 1; j <= i - input; j++) {
      space += " ";
    }
    star = star.substring(0, star.length - 2);
    console.log(space + star);
  }
}
