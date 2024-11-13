function solution(number, limit, power) {
  const answer = [];

  for (let i = 1; i <= number; i++) {
    let key = [1];

    i !== 2 && i % 2 == 0 ? key.push(2) : null;

    for (let j = 3; j <= Math.sqrt(i); j++) {
      i % j === 0 ? key.push(j) : null;
    }

    const half = key.length;

    for (let j = half - 1; j >= 0; j--) {
      i === key[j] ** 2 ? null : key.push(i / key[j]);
    }

    answer.push(key.length);
  }

  return answer.reduce(
    (acc, cur) => (cur > limit ? acc + power : acc + cur),
    0
  );
}