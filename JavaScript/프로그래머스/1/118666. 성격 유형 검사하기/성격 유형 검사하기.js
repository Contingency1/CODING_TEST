function solution(survey, choices) {
  let answer = "";

  const TYPE = {
    R: 0,
    T: 0,
    C: 0,
    F: 0,
    J: 0,
    M: 0,
    A: 0,
    N: 0,
  };

  choices.forEach((x, i) => {
    if (x < 4) {
      TYPE[survey[i].split("")[0]] = TYPE[survey[i].split("")[0]] - (x - 4);
    } else if (x > 4) {
      TYPE[survey[i].split("")[1]] = TYPE[survey[i].split("")[1]] + x - 4;
    }
  });

  const keys = Object.keys(TYPE);
  const values = Object.values(TYPE);

  values.forEach((x, i) => {
    if (i % 2 === 0) {
      if (x > values[i + 1]) {
        answer += keys[i];
      } else if (x < values[i + 1]) {
        answer += keys[i + 1];
      } else {
        keys[i] < keys[i + 1] ? (answer += keys[i]) : (answer += keys[i + 1]);
      }
    }
  });

  return answer;
}