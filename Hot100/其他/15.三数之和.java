/*
 * @lc app=leetcode.cn id=15 lang=java
 *
 * [15] 三数之和
 */

// @lc code=start
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0)
                break; // 若第一个数字已经大于0，则剩余数字都大于0，无法凑成和为0的三元组
            if(i > 0 && nums[i] == nums[i - 1])
                continue; // 为第一个数字去重
            // 双指针
            int left = i + 1, right = n - 1;
            while(left < right) {
                int sum = nums[left] + nums[right];
                // 偏小
                if(sum < -nums[i]) {
                    left++;
                }
                // 偏大
                else if(sum > -nums[i]) {
                    right--;
                }
                // 正好
                else {
                    List<Integer> tuple = new ArrayList<>();
                    tuple.add(nums[i]);
                    tuple.add(nums[left]);
                    tuple.add(nums[right]);
                    result.add(tuple);
                    left++;
                    while(left < right && nums[left] == nums[left - 1]) {
                        left++; // 为第二个数字去重
                    }
                    right--;
                    while(left < right && nums[right] == nums[right + 1]) {
                        right--; // 为第三个数字去重
                    }
                }
            }
        }
        return result;
    }
}
// @lc code=end

