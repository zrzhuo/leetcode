/*
 * @lc app=leetcode.cn id=895 lang=java
 *
 * [895] 最大频率栈
 */

// @lc code=start
class FreqStack {
    Map<Integer, Integer> freqMap = new HashMap<>(); // 元素 --> 频率
    Map<Integer, List<Integer>> groupMap = new HashMap<>(); // 频率 --> 元素列表
    int maxFreq = 0; // 当前最大频率
    public FreqStack() {}
    
    public void push(int val) {
        // 修改频率
        int freq = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, freq); 
        // 将该元素放入对应频率的组
        List<Integer> list = groupMap.getOrDefault(freq, new ArrayList<>());
        list.add(val);
        groupMap.put(freq, list);
        // 更新最大频率
        maxFreq = Math.max(maxFreq, freq);
    }
    
    public int pop() {
        // 获取出现频率最高且最近接栈顶（即最新）的元素
        List<Integer> list = groupMap.get(maxFreq);
        int val = list.remove(list.size() - 1);
        // 修改频率
        if(maxFreq - 1 == 0)
            freqMap.remove(val);
        else
            freqMap.put(val, maxFreq - 1);
        // 更新最大频率
        if(list.size() == 0)
            maxFreq--;
        return val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
// @lc code=end

