function solution(X, Y) {
  let answer = [];
  const X_META = {};
  const Y_META = {};
  for (let i = 0; i < X.length; i++) {
    X_META[X[i]] ? X_META[X[i]]++ : (X_META[X[i]] = 1);
  }

  for (let j = 0; j < Y.length; j++) {
    Y_META[Y[j]] ? Y_META[Y[j]]++ : (Y_META[Y[j]] = 1);
  }

  for (let z = 9; z >= 0; z--) {
    if (X_META[z] > 0 && Y_META[z] > 0) {
      X_META[z]--;
      Y_META[z]--;
      answer.push(z);
      z++;
    }
  }

  if (answer[0] === 0) return "0";

  if (!answer[0]) return "-1";

  return answer.reduce((acc, cur) => acc + cur, "");
}