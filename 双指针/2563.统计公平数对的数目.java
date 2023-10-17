/*
 * @lc app=leetcode.cn id=2563 lang=java
 *
 * [2563] 统计公平数对的数目
 */

// 二分查找
class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        Arrays.sort(nums);
        long res = 0L;
        for(int i = 0; i < n; i++) {
            // 二分查找寻找区间[l, r]
            int l = binarySearch1(nums, lower - nums[i]);
            int r = binarySearch2(nums, upper - nums[i]);
            res += r - l + 1;
            // i在区间内，需将nums[i]排除
            if(l <= i && i <= r) {
                res -= 1;
            }
        }
        return res / 2; // 去重
    }   

    private int binarySearch1(int[] nums, int target) {
        int left = 0, right = nums.length;
        while(left < right) {
            int mid = (right - left) / 2 + left;
            if(nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private int binarySearch2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while(left < right) {
            int mid = (right - left) / 2 + left;
            if(nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right - 1;
    }
}

// @lc code=start

满足lower <= nums[i]+nums[j] <= upper的数对个数
等于
满足nums[i]+nums[j] <= upper的数对个数 
减去
满足nums[i]+nums[j] <= lower-1的数对个数
class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return count(nums, upper) - count(nums, lower - 1);
    }   

    // 双指针, 求满足nums[i]+nums[j] <= target的数对个数 
    private long count(int[] nums, int target) {
        long res = 0L;
        int j = nums.length - 1;
        for(int i = 0; i < nums.length; i++) {
            while(j > i && nums[i] + nums[j] > target) {
                j--;
            }
            if(j > i) {
                res += j - i;
            } else {
                break; // 直接退出
            }
        }
        return res;
    }
}
// @lc code=end

