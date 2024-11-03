
function solution(players, callings) {
  const metaData = {};

  players.forEach((x, i) => {
    metaData[x] = i;
  });

  callings.forEach((x, i) => {
    [players[metaData[x] - 1], players[metaData[x]]] = [
      players[metaData[x]],
      players[metaData[x] - 1],
    ];

    metaData[x]--;
    metaData[players[metaData[x] + 1]]++;
  });

  return players;
}
