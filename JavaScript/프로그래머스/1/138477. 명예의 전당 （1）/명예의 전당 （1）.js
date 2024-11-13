function solution(k, score) {
  let answer = [];
  const rank = [];

  score.forEach((x) => {
    rank.push(x);
    rank.sort((a, b) => a - b);

    rank.length > k ? rank.shift() : null;

    answer.push(rank[0]);
  });

  return answer;
}
