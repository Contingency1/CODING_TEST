function solution(keymap, targets) {
  let answer = Array.from({length: targets.length}, () => 0);
  keymap = keymap.map((x) => x.split(""));
  targets = targets.map((x) => x.split(""));

  const OBJ = {};

  for (let i = 0; i < keymap.length; i++) {
    for (let j = 0; j < keymap[i].length; j++) {
      if (!OBJ[keymap[i][j]]) {
        OBJ[keymap[i][j]] = j + 1;
        continue;
      }

      if (OBJ[keymap[i][j]] > j + 1) {
        OBJ[keymap[i][j]] = j + 1;
      }
    }
  }

  targets.forEach((x, i) => {
    for (let k = 0; k < x.length; k++) {
      if (OBJ[x[k]]) {
        answer[i] += OBJ[x[k]];
      } else {
        answer[i] = -1;
        break;
      }
    }
  });

  return answer;
}
