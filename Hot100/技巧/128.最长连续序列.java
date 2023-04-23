/*
 * @lc app=leetcode.cn id=128 lang=java
 *
 * [128] 最长连续序列
 */

// @lc code=start
class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }
        int max = 0;
        for(int num : set) {
            // 只枚举连续序列的开头元素: num-1不存在时, num即为开头元素
            if(!set.contains(num - 1)) {
                int len = 1;
                while(set.contains(num + len)) {
                    len++;
                }
                max = Math.max(max, len);
            }
        }
        return max;
    }
}
// @lc code=end

