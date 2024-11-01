function solution(park, routes) {
  let base = park.map((x) => x.split(""));
  const ROUTES = routes.map((x) => x.split(" "));

  let answer = [];

  const MAX_HEIGHT = base.length - 1;
  const MAX_WIDTH = base[0].length - 1;

  for (let i = 0; i <= MAX_HEIGHT; i++) {
    for (let j = 0; j <= MAX_WIDTH; j++) {
      if (base[i][j] === "S") {
        answer = [i, j];
        i = MAX_HEIGHT + 1;
        break;
      }
    }
  }

  let bufferBase = JSON.parse(JSON.stringify(base));
  let bufferAnswer = [...answer];

  for (let i = 0; i < ROUTES.length; i++) {
    bufferBase = JSON.parse(JSON.stringify(base));
    bufferAnswer = [...answer];

    for (let j = +ROUTES[i][1]; j > 0; j--) {
      switch (ROUTES[i][0]) {
        case "E":
          if (
            bufferAnswer[1] + 1 < bufferBase[0].length &&
            bufferBase[bufferAnswer[0]][bufferAnswer[1] + 1] === "O"
          ) {
            bufferBase[bufferAnswer[0]][bufferAnswer[1]] = "O";
            bufferBase[bufferAnswer[0]][bufferAnswer[1] + 1] = "S";
            bufferAnswer[1]++;

            if (j === 1) {
              base = JSON.parse(JSON.stringify(bufferBase));
              answer = [...bufferAnswer];
            }
          } else j = 0;

          break;
        case "S":
          if (
            bufferAnswer[0] + 1 < bufferBase[0].length &&
            bufferBase[bufferAnswer[0] + 1][bufferAnswer[1]] === "O"
          ) {
            bufferBase[bufferAnswer[0]][bufferAnswer[1]] = "O";
            bufferBase[bufferAnswer[0] + 1][bufferAnswer[1]] = "S";
            bufferAnswer[0]++;

            if (j === 1) {
              base = JSON.parse(JSON.stringify(bufferBase));
              answer = [...bufferAnswer];
            }
          } else j = 0;
          break;
        case "N":
          if (
            bufferAnswer[0] >= 1 &&
            bufferBase[bufferAnswer[0] - 1][bufferAnswer[1]] === "O"
          ) {
            bufferBase[bufferAnswer[0]][bufferAnswer[1]] = "O";
            bufferBase[bufferAnswer[0] - 1][bufferAnswer[1]] = "S";
            bufferAnswer[0]--;

            if (j === 1) {
              base = JSON.parse(JSON.stringify(bufferBase));
              answer = [...bufferAnswer];
            }
          } else j = 0;
          break;
        case "W":
          if (
            bufferAnswer[1] >= 1 &&
            bufferBase[bufferAnswer[0]][bufferAnswer[1] - 1] === "O"
          ) {
            bufferBase[bufferAnswer[0]][bufferAnswer[1]] = "O";
            bufferBase[bufferAnswer[0]][bufferAnswer[1] - 1] = "S";
            bufferAnswer[1]--;

            if (j === 1) {
              base = JSON.parse(JSON.stringify(bufferBase));
              answer = [...bufferAnswer];
            }
          } else j = 0;
      }
    }
  }
  return answer;
}
