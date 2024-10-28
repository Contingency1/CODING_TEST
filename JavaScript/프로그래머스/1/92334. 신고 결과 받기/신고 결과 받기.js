function solution(id_list, report, k) {
  const answer = Array.from(id_list, () => 0);
  const ID_LIST = {};
  const banned = [];

  id_list.forEach((element) =>
    Object.assign(ID_LIST, {
      [element]: {count: 0, mail: 0, send: [], get: []},
    })
  );

  report.forEach((element) => {
    const [sender, receiver] = element.split(" ");
    ID_LIST[sender].send.push(receiver);
    ID_LIST[receiver].get.push(sender);
  });

  for (let key in ID_LIST) {
    ID_LIST[key].get = [...new Set(ID_LIST[key].get)];
    ID_LIST[key].count = ID_LIST[key].get.length;
    ID_LIST[key].count >= k ? banned.push(key) : null;
  }

  let i = 0;

  for (let key in ID_LIST) {
    for (let j = 0; j < banned.length; j++) {
      if (ID_LIST[key].send.includes(banned[j])) answer[i]++;
    }

    i++;
  }

  return answer;
}