
function solution(friends, gifts) {
  const friendsOBJ = {};
  const record = {};

  friends.forEach((element) => (record[element] = 0));

  friends.forEach((element) => {
    friendsOBJ[element] = {POINT: 0, ...record};
  });

  gifts.forEach((element) => {
    const name = element.split(" ");

    friendsOBJ[name[0]]["POINT"]++;
    friendsOBJ[name[1]]["POINT"]--;

    friendsOBJ[name[0]][name[1]]++;
  });

  for (let i = 0; i < friends.length; i++) {
    for (let j = i + 1; j < friends.length; j++) {
      if (
        friendsOBJ[friends[i]][friends[j]] > friendsOBJ[friends[j]][friends[i]]
      ) {
        record[friends[i]]++;
      } else if (
        friendsOBJ[friends[i]][friends[j]] < friendsOBJ[friends[j]][friends[i]]
      ) {
        record[friends[j]]++;
      } else {
        if (friendsOBJ[friends[i]]["POINT"] > friendsOBJ[friends[j]]["POINT"]) {
          record[friends[i]]++;
        } else if (
          friendsOBJ[friends[i]]["POINT"] < friendsOBJ[friends[j]]["POINT"]
        ) {
          record[friends[j]]++;
        }
      }
    }
  }

  const keys = Object.keys(record);

  let max = 0;

  for (let i = 0; i < keys.length; i++) {
    let value = record[keys[i]];
    if (value > max) {
      max = record[keys[i]];
    }
  }

  return max;
}
