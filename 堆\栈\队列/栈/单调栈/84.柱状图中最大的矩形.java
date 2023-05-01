/*
 * @lc app=leetcode.cn id=84 lang=java
 *
 * [84] 柱状图中最大的矩形
 */

// @lc code=start
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        // left[i]: 位置i左侧最近的比heights[i]小的柱子位置
        // right[i]: 位置i右侧最近的比heights[i]小的柱子位置
        int[] left = new int[n];
        int[] right = new int[n];
        // 单调栈, 存放下标, 从栈底到栈顶对应元素单调递增
        Deque<Integer> stack = new LinkedList<>(); 
        Arrays.fill(right, n);
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        stack.clear();
        Arrays.fill(left, -1);
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        // 获取结果
        int result = 0;
        for(int i = 0; i < n; i++) {
            int w = right[i] - left[i] - 1; // 以heights[i]为高，位置i向左右拓展出的矩阵的最大宽度
            result = Math.max(result, w * heights[i]);
        }
        return result;
    }
}
// @lc code=end

