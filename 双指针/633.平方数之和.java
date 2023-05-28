/*
 * @lc app=leetcode.cn id=633 lang=java
 *
 * [633] 平方数之和
 */

// @lc code=start
class Solution {
    public boolean judgeSquareSum(int c) {
        int left = 0, right = (int)Math.sqrt(c);
        while(left <= right) {
            long sum = 1L * left * left + 1L * right * right;
            if(sum < c)
                left++;
            else if(sum > c)
                right--;
            else
                return true;
        }
        return false;
    }
}
// @lc code=end

