/*
 * @lc app=leetcode.cn id=153 lang=java
 *
 * [153] 寻找旋转排序数组中的最小值
 */

// @lc code=start
class Solution {
    public int findMin(int[] nums) {
        // 最小值是左到右第一个小于等于nums[n-1]的数
        int n = nums.length;
        // x和f(x): x为下标，当nums[x]>nums[n-1]时，f(x)为1，否则为0
        // 由nums[]的性质可得，随着x的增大，f(x)由1减为0
        int left = 0, right = n; 
        int  target = 1;
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

