/*
 * @lc app=leetcode.cn id=42 lang=java
 *
 * [42] 接雨水
 */

// 动态规划
class Solution {
    public int trap(int[] height) {
        // 位置i能接到的雨水，取决于其两侧最高柱子中的较小值
        int n = height.length;
        // left[i]: i位置左侧最高柱子
        // right[i]: i位置右侧最高柱子
        int[] left = new int[n], right = new int[n];
        for(int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }
        for(int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }
        // 获取结果
        int count = 0;
        for(int i = 0; i < n; i++) {
            int min = Math.min(left[i], right[i]);
            if(min > height[i])
                count += min - height[i];
        }
        return count;
    }
}


// 双指针
class Solution {
    public int trap(int[] height) {
        // 位置i能接到的雨水，取决于其两侧最高柱子中的较小值
        int n = height.length, count = 0;
        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0; // leftMax记录left左侧最高柱子, rightMax记录right右侧最高柱子
        while(left <= right) {
            // left的左侧最高柱子一定是leftMax, 而右侧最高柱子至少是rightMax
            // 故当leftMax < rightMax时, left两侧最高柱子中的较小值一定是leftMax
            if(leftMax < rightMax) {
                if(leftMax > height[left])
                    count += leftMax - height[left];
                leftMax = Math.max(leftMax, height[left]); // 更新leftMax
                left++;
            }
            // right的右侧最高柱子一定是rightMax, 而左侧最高柱子至少是leftMax
            // 故当rightMax < leftMax时, right两侧最高柱子中的较小值一定是rightMax
            else {
                if(rightMax > height[right])
                    count += rightMax - height[right]; 
                rightMax = Math.max(rightMax, height[right]); // 更新rightMax
                right--;
            }
        }
        return count;
    }
}

// @lc code=start
// 单调栈
class Solution {
    public int trap(int[] height) {
        int n = height.length, count = 0;
        // 单调栈, 存储的是下标，从栈底到栈顶的对应元素单调递减
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            // height[i] >= height[stack.peek()], 即i可以作为右侧挡板时
            while(!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                int bottom = stack.pop(); // 底板高度
                if(stack.isEmpty())
                    continue; // 此时没有左侧挡板, 跳过
                int left = stack.peek(), right = i; // 左侧挡板和右侧挡板
                int w = right - left - 1; // 宽度
                int h = Math.min(height[left], height[right]) - height[bottom]; // 高度
                count += w * h;
            }
            stack.push(i);
        }
        return count;
    }
}
// @lc code=end
