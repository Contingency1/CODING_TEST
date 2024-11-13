function solution(nums) {
  const limit = nums.length / 2;

  nums = [...new Set(nums)];

  return nums.length > limit ? limit : nums.length;
}