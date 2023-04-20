/*
 * @lc app=leetcode.cn id=75 lang=java
 *
 * [75] 颜色分类
 */

// 两趟遍历
class Solution {
    public void sortColors(int[] nums) {
        int[] counter = new int[3];
        for(int i = 0; i < nums.length; i++) {
            counter[nums[i]]++;
        }
        int i = 0;
        while(i < counter[0]) {
            nums[i++] = 0;
        }
        while(i < counter[0] + counter[1]) {
            nums[i++] = 1;
        }
        while(i < counter[0] + counter[1] + counter[2]) {
            nums[i++] = 2;
        }
        return;
    }
}

// @lc code=start
// 一趟遍历
class Solution {
    public void sortColors(int[] nums) {
        int ptr0 = 0, ptr1 = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0)
                swap(nums, i, ptr0++);
            if(nums[i] == 1)
                swap(nums, i, ptr1++);
            if(ptr1 < ptr0)
                ptr1 = ptr0;
        }
        return;
    }

     void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// @lc code=end
