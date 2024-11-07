function solution(nums) {
  let available = [];

  for (let i = 0; i < nums.length - 2; i++) {
    for (let j = i + 1; j < nums.length - 1; j++) {
      for (let x = j + 1; x < nums.length; x++) {
        available.push(nums[i] + nums[j] + nums[x]);
      }
    }
  }

  available = available.sort((a, b) => a - b);

  for (let i = 2; i <= Math.sqrt(available[available.length - 1]); i += 2) {
    available = available.filter((x) => x === i || x % i !== 0);

    i === 2 ? i-- : null;
  }

  return available.length;
}
