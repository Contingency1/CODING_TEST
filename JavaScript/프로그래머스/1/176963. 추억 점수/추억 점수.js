function solution(name, yearning, photo) {
  const OBJ = {};

  name.forEach((x, i) => {
    OBJ[x] = yearning[i];
  });

  return photo.map((x) => {
    return x.reduce((acc, cur) => (OBJ[cur] ? acc + OBJ[cur] : acc + 0), 0);
  });
}
