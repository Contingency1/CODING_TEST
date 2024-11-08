function solution(n, m, section) {
  let answer = 0;

  for (let i = 0; i < section.length; i++) {
    const key = section[i];
    for (let j = i + 1; j < section.length; j++) {
      if (section[j] <= key + m - 1) {
        section.shift(section[j]);
        j--;
      }
    }

    section.shift(section[i]);
    i--;
    answer++;
  }

  return answer;
}