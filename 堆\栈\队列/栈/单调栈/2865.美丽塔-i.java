/*
 * @lc app=leetcode.cn id=2865 lang=java
 *
 * [2865] 美丽塔 I
 */

// @lc code=start
class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        Integer[] heights = maxHeights.toArray(new Integer[maxHeights.size()]);
        int n = heights.length;
        long[] dp1 = new long[n]; // dp1[i]: 以maxHeights[i]为最高塔，塔[0...i]之和
        long[] dp2 = new long[n]; // dp2[i]: 以maxHeights[i]为最高塔，塔[i...n-1]之和
        
        Deque<Integer> stack = new ArrayDeque<>(); // 单调栈：存放的是下标，栈底到栈顶对应元素递增
        dp1[0] = heights[0];
        stack.push(0);
        for(int i = 1; i < n; i++) {
            if(heights[i] >= heights[i - 1]) {
                dp1[i] = dp1[i - 1] + heights[i];
            } else {
                while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                    stack.pop(); // 维护单调栈
                }
                if(stack.isEmpty()) {
                    dp1[i] = 1L * heights[i] * (i + 1); // heights[i]左侧元素全部大于heights[i]
                } else {
                    int j = stack.peek(); // heights[i]左侧第一个小于等于heights[i]的元素下标
                    dp1[i] = dp1[j] + 1L * heights[i] * (i - j);
                }
            }
            stack.push(i);
        }
        
        stack.clear(); // 单调栈：存放的是下标，栈底到栈顶对应元素递增
        dp2[n - 1] = heights[n - 1];
        stack.push(n - 1);
        for(int i = n - 2; i >= 0; i--) {
            if(heights[i] >= heights[i + 1]) {
                dp2[i] = dp2[i + 1] + heights[i];
            } else {
                while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                    stack.pop(); // 维护单调栈
                }
                if(stack.isEmpty()) {
                    dp2[i] = 1L * heights[i] * (n - i); // heights[i]右侧元素全部大于heights[i]
                } else {
                    int j = stack.peek(); // heights[i]右侧第一个小于等于heights[i]的元素下标
                    dp2[i] = dp2[j] + 1L * heights[i] * (j - i);
                }
            }
            stack.push(i);
        }

        long res = 0;
        for(int i = 0; i < n; i++) {
            res = Math.max(res, dp1[i] + dp2[i] - heights[i]);
        }
        return res;
    }
}
// @lc code=end

