function solution(a, b) {
  const DAY = {
    1: "MON",
    2: "TUE",
    3: "WED",
    4: "THU",
    5: "FRI",
    6: "SAT",
    0: "SUN",
  };

  const date = new Date(2016, a - 1, b, 0, 0, 0);

  return DAY[date.getDay()];
}
