/*
 * @lc app=leetcode.cn id=155 lang=java
 *
 * [155] 最小栈
 */

// @lc code=start
class MinStack {
    Deque<Integer> stack; // 主栈
    Deque<Integer> minStack; // 辅助单调栈: 栈底到栈顶单调递减, 栈顶时刻记录着最小值

    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }
    
    public void push(int val) {
        stack.push(val);
        // minStack为空, 或者val小于等于minStack的栈顶元素时, 才将val压入minStack
        // 等号尤其重要: 当主栈内同时存在多个最小值, 某个最小值被pop掉时, minStack也要pop掉该最小值, 因此若minStack只存放一个该最小值, 则最小值就丢失了
        if(minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }
    
    public void pop() {
        int cur = stack.pop();
        // 当前被pop掉的主栈栈顶元素为最小值时, 从minStack中也pop掉一个最小值
        if(cur == minStack.peek()) {
            minStack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
// @lc code=end

