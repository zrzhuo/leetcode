/*
 * @lc app=leetcode.cn id=34 lang=java
 *
 * [34] 在排序数组中查找元素的第一个和最后一个位置
 */

// @lc code=start
class Solution {

    // 查找数组中出现的第一个target的位置
    int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }

    public int[] searchRange(int[] nums, int target) {
        int left = binarySearch(nums, target);
        int right = binarySearch(nums, target + 1) - 1;
        if(left <= right)
            return new int[]{left, right};
        return new int[]{-1, -1};
    }
}
// @lc code=end

