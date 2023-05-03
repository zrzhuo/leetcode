/*
 * @lc app=leetcode.cn id=2216 lang=java
 *
 * [2216] 美化数组的最少删除数
 */

// @lc code=start
class Solution {
    public int minDeletion(int[] nums) {
        int n = nums.length;
        // 贪心的为美丽数组依次选择数字, pre是上一个选择的数字, idx为当前下标
        int pre = nums[0], idx = 1;
        for(int i = 1; i < n; i++) {
            // 为奇数下标选择数字时, 要考虑与上一个数字是否相等
            if(idx % 2 == 1) {
                if(nums[i] != pre) {
                    pre = nums[i];
                    idx++;
                }
            }
            // 为偶数下标选择数字时, 无需考虑与上一个数字是否相等
            else {
                pre = nums[i];
                idx++;
            }
        }
        // 美丽数组要求长度为偶数
        if(idx % 2 == 1)
            idx--;
        // 需要删除的元素数量 = 总数量 - 保留的元素数量
        return n - idx;
    }
}
// @lc code=end

