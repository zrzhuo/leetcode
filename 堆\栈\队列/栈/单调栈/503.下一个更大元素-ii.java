/*
 * @lc app=leetcode.cn id=503 lang=java
 *
 * [503] 下一个更大元素 II
 */

// @lc code=start
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, - 1);
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < 2 * n; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                int j = stack.pop();
                res[j] = nums[i % n];
            }
            stack.push(i % n);
        }
        return res;
    }
}
// @lc code=end

