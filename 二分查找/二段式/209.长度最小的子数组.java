/*
 * @lc app=leetcode.cn id=209 lang=java
 *
 * [209] 长度最小的子数组
 */

// @lc code=start
class Solution {
    int binarySearch(int[] prefix, int target) {
        int left = 0, right = prefix.length;
        while(left < right) {
            int mid = left + (right - left) / 2;
            int now = prefix[mid];
            if(now >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int[] prefix =  new int[n + 1];
        for(int i = 0; i < n; ++i) 
            prefix[i + 1] = prefix[i] + nums[i];
        int min = n + 1;
        for(int i = 0; i < n + 1; ++i) {
            // prefix[j] - prefix[i] >= target
            int j = binarySearch(prefix, target + prefix[i]);
            if(j == n + 1)
                continue; // j == n + 1 时，查找失败
            min = Math.min(min, j - i);
        }
        return min == n + 1 ? 0 : min;
    }
}
// @lc code=end

