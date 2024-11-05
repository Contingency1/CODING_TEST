
function solution(s) {
  let answer = 0;

  const req = s.split("");

  let dulpicated = "";
  let count1 = 0;
  let count2 = 0;

  for (let i = 0; i < req.length; i++) {
    if (!dulpicated) {
      dulpicated = req[i];
      count1++;
      answer++;
      continue;
    }

    dulpicated === req[i] ? count1++ : count2++;

    if (count1 > 0 && count1 === count2) {
      dulpicated = "";
      count1 = 0;
      count2 = 0;
    }
  }

  return answer;
}
