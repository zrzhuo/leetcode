/*
 * @lc app=leetcode.cn id=35 lang=java
 *
 * [35] 搜索插入位置
 */

// @lc code=start
class Solution {
    public int searchInsert(int[] nums, int target) {
        // x和f(x)：x为下标, (x)为nums[x], f(x)单调递增
        // 左右边界
        int left = 0, right = nums.length;
        // 目标值:target
        // 查找目标：从left到right，第一个使得f(x)>=target的x
        while(left < right){
            int mid =  left + (right - left) / 2;
            int now = nums[mid];
            if(now >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }
}
// @lc code=end

