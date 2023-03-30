/*
 * @lc app=leetcode.cn id=33 lang=java
 *
 * [33] 搜索旋转排序数组
 */

// @lc code=start
class Solution {
    // 从nums[left...right]中查找target
    int binarySearch1(int[]nums, int target, int left, int right){
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target)
                left = mid + 1;
            else if(nums[mid] > target)
                right = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    // 从nums中查找分割点
    int binarySearch2(int[] nums){
        // x和f(x): x为下标，当nums[x]>=nums[0]时，f(x)为1，否则f(x)为0
        // 根据nums的性质，随着x的增大，f(x)由1减为0
        int left = 0, right = nums.length; 
        // target设为nums[0]
        int target = 0; 
        // 从left到right，查找第一个使得f(x)<=target的x
        while(left < right){
            int mid = left + (right - left) / 2;
            int now = nums[mid]>=nums[0]? 1: 0; 
            if(now <= target)
                right = mid;
            else 
                left = mid + 1;
        }
        return right;
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        // 二分查找分割点p，得到两个递增区间[0, p-1]、[p, n-1];
        int p = binarySearch2(nums);
        if(target >= nums[0])
            return binarySearch1(nums, target, 0, p - 1);
        else
            return binarySearch1(nums, target, p, n - 1);
    }
}
// @lc code=end

