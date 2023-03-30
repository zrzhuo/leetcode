/*
 * @lc app=leetcode.cn id=167 lang=java
 *
 * [167] 两数之和 II - 输入有序数组
 */

// @lc code=start
class Solution {
    int binarySearch(int[] numbers, int target){
        int left = 0, right = numbers.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(numbers[mid] < target)
                left = mid + 1;
            else if(numbers[mid] > target)
                right = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        for(int i = 0; i < n; ++i){
            int j = binarySearch(numbers, target - numbers[i]);
            if(j != -1 && j != i){
                int[] ans = new int[]{i + 1, j + 1};
                Arrays.sort(ans);
                return ans;
            }
        }
        return null;
    }
}
// @lc code=end

