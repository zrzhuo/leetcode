/*
 * @lc app=leetcode.cn id=154 lang=java
 *
 * [154] 寻找旋转排序数组中的最小值 II
 */

// @lc code=start
class Solution {
    public int findMin(int[] nums) {
        // 最小值是左到右第一个小于等于nums[n-1]的数，这里需排除“最左侧的所有与nums[n-1]相等的数”
        int n = nums.length;
        // x和f(x): x为下标，当nums[x]>nums[n-1]时，f(x)为1，否则为0
        // 由nums[]的性质可得，随着x的增大，f(x)由1减为0
        int left = 0, right = n - 1; 
        // 排除“最左侧的所有与nums[n-1]相等的数”
        while(left < n && nums[left] == nums[n-1])
            left++;
        // target设为1
        int target = 1;
        // 从left到right，查找第一个使得f(x)<target的x
        while(left < right){
            int mid = left + (right - left) / 2;
            int now = nums[mid] > nums[n-1]? 1: 0;
            if(now < target)
                right = mid;
            else
                left = mid + 1;
        }
        return nums[right];
    }
}
// @lc code=end

