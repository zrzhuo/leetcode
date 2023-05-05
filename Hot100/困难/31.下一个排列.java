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
        // s大于0, 即整个nums并非完全递减时, 进行如下操作
        if(s > 0) {
            int idx = binarySearch(nums, s, n - 1, nums[s - 1]); // 从nums[s...n-1]中查找最后一个比nums[s-1]大的数
            swap(nums, s - 1, idx); // 交换
        }
        reverse(nums, s, n - 1); // 再将nums[s...n-1]进行一次翻转
    }

    int binarySearch(int[] nums, int start, int stop, int target) {
        int left = start, right = stop + 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] <= target) 
                right = mid;
            else
                left = mid + 1;
        }
        return right - 1;
    }

    void reverse(int[] nums, int start, int stop) {
        int i = start, j = stop;
        while(i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// @lc code=end

示例: 
12345
12354   末尾的最长递减序列为[5,4],   前一个数为3, 在该序列中最后一个比3大的数为4, 交换3和4, 数组变为[1,2,4,5,3], 再翻转末尾[5,3],   得到[1,2,4,3,5]
12435
12453   ...
12534
12543   末尾的最长递减序列为[5,4,3], 前一个数为2, 在该序列中最后一个比2大的数为3, 交换2和3, 数组变为[1,3,5,4,2], 再翻转末尾[5,4,2], 得到[1,3,2,4,5]

13245   末尾的最长递减序列为[5],     前一个数为4, 在该序列中最后一个比4大的数为5, 交换4和5, 数组变为[1,3,2,5,4], 再翻转末尾[4],     得到[1,3,2,5,4]
13254   
13425   ...
13452
13524
13542   末尾的最长递减序列为[5,4,2], 前一个数为3, 在该序列中最后一个比3大的数为4, 交换3和4, 数组变为[1,4,5,3,2], 再翻转末尾[5,3,2], 得到[1,4,2,3,5]

14235
 ...