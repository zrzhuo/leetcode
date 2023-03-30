/*
 * @lc app=leetcode.cn id=81 lang=java
 *
 * [81] 搜索旋转排序数组 II
 */

// @lc code=start
class Solution {

    // 从nums[left...right]中查找target
    boolean binarySearch1(int[]nums, int target, int left, int right){
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target)
                left = mid + 1;
            else if(nums[mid] > target)
                right = mid - 1;
            else
                return true;
        }
        return false;
    }

    // 从nums中查找到分割点
    int binarySearch2(int[] nums){
        // x和f(x): x为下标，当nums[x]>nums[0]时，f(x)为1，否则f(x)为0
        int left = 0, right = nums.length; // right扩大1
        // 寻找合适的left，这里会使得最坏情况下的时间复杂度变为O(n)
        while(left < nums.length && nums[left] == nums[0])
            ++left;
        // 此时，nums[left, right]的性质可以得到：随着x的增大，f(x)由1减为0
        int target = 0;
        // 从left到right，查找第一个使得f(x)<=target的x
        while(left < right){
            int mid = left + (right - left) / 2;
            int now = nums[mid]>nums[0]? 1: 0;
            if(now <= target)
                right = mid;
            else 
                left = mid + 1;
        }
        return right;
    }

    public boolean search(int[] nums, int target) {
        int n = nums.length;
        // 二分查找分割点p，得到两个递增区间[0, p-1]、[p, n-1];
        int p = binarySearch2(nums);
        // 分别从两个递增区间中查找
        return binarySearch1(nums, target, 0, p - 1) || binarySearch1(nums, target, p, n - 1);
    }
}
// @lc code=end

