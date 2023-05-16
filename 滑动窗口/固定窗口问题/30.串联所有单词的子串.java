/*
 * @lc app=leetcode.cn id=30 lang=java
 *
 * [30] 串联所有单词的子串
 */

// @lc code=start
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length(), m = words.length, k = words[0].length();
        List<Integer> ans = new ArrayList<>();
        // 枚举偏移量
        for(int offset = 0; offset < k; offset++) {
            // 剩余字符串长度不够
            if(n - offset < m * k)
                break;
            // 固定窗口问题
            int left = 0, right = 0;
            Map<String, Integer> counter = new HashMap<>(); // 统计每个单词出现的个数
            for(String word : words) {
                counter.put(word, counter.getOrDefault(word, 0) + 1);
            }
            int count = counter.size(); // 当前窗口未覆盖的单词个数
            // 初始化窗口
            while(right < m) {
                String next = s.substring(offset + right * k, offset + right * k + k); // 下一个单词
                if(counter.containsKey(next)) {
                    int num = counter.get(next);
                    if(num == 1)
                        count--;
                    counter.put(next, num - 1);
                }
                right++;
            }
            if(count == 0)
                ans.add(offset + left * k);
            // 滑动
            while(right < (n - offset) / k) {
                String next = s.substring(offset + right * k, offset + right * k + k); // 下一个单词
                if(counter.containsKey(next)) {
                    int num = counter.get(next);
                    if(num == 1)
                        count--;
                    counter.put(next, num - 1);
                }
                right++;
                String prev = s.substring(offset + left * k, offset + left * k + k); // 上一个单词
                if(counter.containsKey(prev)) {
                    int num = counter.get(prev);
                    if(num == 0)
                        count++;
                    counter.put(prev, num + 1);
                }
                left++;
                if(count == 0)
                    ans.add(offset + left * k);
            }
        }
        return ans;
    }
}
// @lc code=end

