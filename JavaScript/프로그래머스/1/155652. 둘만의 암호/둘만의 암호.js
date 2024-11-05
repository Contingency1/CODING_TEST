function solution(s, skip, index) {
  let answer = "";

  const SKIP = skip
    .split("")
    .map((x) => x.charCodeAt())
    .sort((a, b) => a - b);

  const AVAILABLE = [];

  for (let i = 97; i < 123; i++) {
    if (i === SKIP[0]) {
      SKIP.shift();
      continue;
    }
    AVAILABLE.push(String.fromCharCode(i));
  }

  for (let i = 0; i < s.length; i++) {
    const INDEX = AVAILABLE.findIndex((x) => x === s[i]);

    if (INDEX + index > AVAILABLE.length - 1) {
      answer += AVAILABLE[(INDEX + index) % AVAILABLE.length];
      continue;
    }

    answer += AVAILABLE[INDEX + index];
  }

  return answer;
}