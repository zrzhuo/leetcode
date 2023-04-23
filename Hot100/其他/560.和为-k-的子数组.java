/*
 * @lc app=leetcode.cn id=560 lang=java
 *
 * [560] 和为 K 的子数组
 */

// @lc code=start
class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int pre = 0; // 前缀和
        map.put(0, 1);
        for(int i = 0; i < n; i++) {
            pre += nums[i];
            count += map.getOrDefault(pre - k, 0);
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
// @lc code=end

