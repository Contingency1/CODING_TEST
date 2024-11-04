function solution(ingredient) {
  let answer = 0;

  for (let i = 0; i <= ingredient.length - 4; i++) {
    if (
      ingredient[i] === 1 &&
      ingredient[i + 1] === 2 &&
      ingredient[i + 2] === 3 &&
      ingredient[i + 3] === 1
    ) {
      ingredient.splice(i, 4);
      answer++;
      i === 0
        ? i--
        : i === 1
        ? (i -= 2)
        : i === 2
        ? (i -= 3)
        : i === 3
        ? (i -= 4)
        : (i -= 4);
    }
  }
  return answer;
}
