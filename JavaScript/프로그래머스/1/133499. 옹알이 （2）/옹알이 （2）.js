function solution(babbling) {
  const AVAILABLE = ["aya", "ye", "woo", "ma"];

  return babbling.reduce((acc, cur) => {
    let prev = "";
    while (cur.length > 0) {
      for (let i = 0; i < AVAILABLE.length; i++) {
        if (cur.substr(0, AVAILABLE[i].length) === AVAILABLE[i]) {
          if (prev === AVAILABLE[i]) {
            return acc;
          }
          cur = cur.replace(AVAILABLE[i], "");
          prev = AVAILABLE[i];
          break;
        }

        if (i === AVAILABLE.length - 1) {
          return acc;
        }
      }
    }

    return ++acc;
  }, 0);
}
