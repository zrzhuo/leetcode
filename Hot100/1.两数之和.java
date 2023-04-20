/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 */

// @lc code=start
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        // 记录已经出现的数字,即该数字在nums中的下标
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;  i < n; i++) {
            int pre = target - nums[i];
            if(map.containsKey(pre)) {
                return new int[]{map.get(pre), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
// @lc code=end

