function solution(bandage, health, attacks) {
  let continuity = 0;
  const MAX_HEALTH = health;
  const LAST_TIME = attacks[attacks.length - 1][0];

  for (let i = 0; i <= LAST_TIME; i++) {
    for (let j = 0; j < attacks.length; j++) {
      if (attacks[j][0] === i) {
        health -= attacks[j][1];
        if (health <= 0) return -1;
        continuity = 0;
        attacks.shift();
      } else if (continuity === bandage[0]) {
        continuity = 0;
        health += bandage[1] + bandage[2];
        if (health > MAX_HEALTH) health = MAX_HEALTH;
      } else {
        continuity++;
        if (continuity === bandage[0]) {
          health += bandage[1] + bandage[2];
          continuity = 0;
        } else {
          health += bandage[1];
        }
        if (health > MAX_HEALTH) health = MAX_HEALTH;
      }
      break;
    }
  }

  return health;
}
