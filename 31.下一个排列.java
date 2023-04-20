/*
 * @lc app=leetcode.cn id=31 lang=java
 *
 * [31] 下一个排列
 */

// @lc code=start
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        // 寻找nums末尾的最长递减序列nums[s...n-1]
        int s = n - 1;
        while(s > 0 && nums[s - 1] >= nums[s]) {
            s--;
        }
        if(s > 0) {

        }


    }

    int binarySearch(int[] nums, int left, int right, int target) {
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid]) 
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }

    void reverse(int[] nums, int i, int j) {
        while(i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
// @lc code=end

12345
12354
12435
12453
12534
12543
13
13
13
13
13
14
14
14
