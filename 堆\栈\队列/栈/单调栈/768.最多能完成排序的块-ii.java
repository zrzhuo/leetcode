/*
 * @lc app=leetcode.cn id=768 lang=java
 *
 * [768] 最多能完成排序的块 II
 */

// @lc code=start
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        // 单调栈：栈底到栈顶不严格递增，存放每一块的最大值
        Deque<Integer> stack = new ArrayDeque<>(); 
        for(int num : arr) {
            // 当前元素大于等于上一块的最大值时，当前元素单独作为新的块
            if(stack.isEmpty() || num >= stack.peek()) {
                stack.push(num);
            } 
            // 当前元素小于上一块的最大值时，不断向前合并块，直到当前元素大于等于上一块的最大值
            else {
                int max = stack.peek(); // 记录最后一块的最大值
                // 向前合并块
                while(!stack.isEmpty() && num < stack.peek()) {
                    stack.pop();
                }
                // 合并完的块添加回去
                stack.push(max);
            }
        }
        return stack.size();
    }
}
// @lc code=end

class Solution {
    public int maxChunksToSorted(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int ans = 0;
        long sum = 0, total = 0;
        for(int i = 0; i < arr.length; ++i){
            sum += arr[i];
            total += sorted[i];
            // 当sum==total时，当前区间就可以当作一个块，详见https://leetcode.cn/problems/max-chunks-to-make-sorted/solutions/1614270/by-zrzhuo-xvm6/
            if(sum == total)
                ++ans;
        }
        return ans;
    }
}
