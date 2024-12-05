let fs = require("fs");
const [[N, M], ...rest] = fs
  .readFileSync("./dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((x) => +x));

const A = rest.splice(0, N);
const B = rest.splice(0, N);
const answer = Array.from({length: N}, (x) => []);

for (let i = 0; i < N; i++) {
  for (let j = 0; j < M; j++) {
    answer[i].push(A[i][j] + B[i][j]);
  }
}

answer.forEach((x) => console.log(...x));
