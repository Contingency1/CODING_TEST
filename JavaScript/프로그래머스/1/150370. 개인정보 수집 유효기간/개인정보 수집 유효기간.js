function solution(today, terms, privacies) {
  let answer = [];

  const EXPIRED_AT = {};

  terms.forEach((x) => {
    EXPIRED_AT[x.split(" ")[0]] = +x.split(" ")[1];
  });

  const TODAY = new Date(today + ".00:00:00+00:00");

  privacies.forEach((x, i) => {
    const date = new Date(x.split(" ")[0] + ".00:00:00+00:00");

    // console.log(date);
    date.setMonth(date.getUTCMonth() + EXPIRED_AT[x.split(" ")[1]]);

    // console.log(date);
    console.log(TODAY.getDate(), date.getDate());
    if (
      TODAY.getFullYear() > date.getFullYear() ||
      (TODAY.getFullYear() === date.getFullYear() &&
        TODAY.getMonth() > date.getMonth()) ||
      (TODAY.getFullYear() === date.getFullYear() &&
        TODAY.getMonth() === date.getMonth() &&
        TODAY.getDate() >= date.getDate())
    ) {
      answer.push(i + 1);
    }
  });

  return answer;
}