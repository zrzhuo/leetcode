/*
 * @lc app=leetcode.cn id=1647 lang=java
 *
 * [1647] 字符频次唯一的最小删除次数
 */

// @lc code=start
class Solution {
    public int minDeletions(String s) {
        // 统计每个字符出现的频率
        int[] counter = new int[26];
        for(int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
        }
        // 排序
        Arrays.sort(counter);
        int result = 0;
        for(int i = 24; i >= 0; i--) {
            // 前一个频率已经为0时, 将当前频率减为0
            if(counter[i + 1] == 0) {
                result += counter[i] - 0;
                counter[i] = 0; 
            }
            // 当前频率大于等于前一个频率时, 将当前频率减为前一个频率-1
            else if (counter[i] >= counter[i + 1]) {
                result += counter[i] - (counter[i + 1] - 1);
                counter[i] = counter[i + 1] - 1;
            }
        }
        return result;
    }
}

// @lc code=end


class Solution {
    public int minDeletions(String s) {
        // 统计每个字符出现的频率
        int[] counter = new int[26];
        for(int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
        }
        int result = 0;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < 26; i++) {
            int freq = counter[i];
            if(freq == 0)
                continue;
            // 当前频率已经存在于set中时, 频率减1, 重新尝试
            while(freq > 0 && set.contains(freq)) {
                freq--;
                result++;
            }
            set.add(freq); // 当前频率不存在set中, 可以添加到set
        }
        return result;
    }
}