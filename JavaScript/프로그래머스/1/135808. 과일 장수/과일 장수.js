function solution(k, m, score) {
  if (!(score.length / m)) return 0;

  return score
    .sort((a, b) => b - a)
    .reduce(
      (acc, cur, idx) =>
        idx !== 0 && idx % m === m - 1 ? (acc += cur * m) : acc,
      0
    );
}
