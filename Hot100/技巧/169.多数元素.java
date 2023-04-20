/*
 * @lc app=leetcode.cn id=169 lang=java
 *
 * [169] 多数元素
 */


// Boyer-Moore投票算法, 详见 https://leetcode.cn/problems/majority-element/solution/boyer-moore-tou-piao-suan-fa-by-zrzhuo-vki7/
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++) {
            if(count == 0)
                candidate = nums[i];
            if(nums[i] == candidate)
                count++;
            else
                count--;
        }
        return candidate;
    }
}


// @lc code=start
// 随机算法, 最坏时间为无穷大, 平均时间复杂度为O(n)
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        Random rand = new Random();
        while (true) {
            int candidate = nums[rand.nextInt(n)]; // 随机选择一个数
            int count = 0;
            for(int num : nums) {
                if(num == candidate)
                    count++;
            }
            if(count > n / 2)
                return candidate;
        }
    }
}
// @lc code=end
