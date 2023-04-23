/*
 * @lc app=leetcode.cn id=49 lang=java
 *
 * [49] 字母异位词分组
 */

// @lc code=start
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            int[] counter = new int[26];
            for(int i = 0; i < str.length(); i++) {
                counter[str.charAt(i) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 26; i++) {
                if(counter[i] > 0) {
                    sb.append((char)('a' + i));
                    sb.append(String.valueOf(counter[i]));
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        List<List<String>> res = new ArrayList<>();
        for(List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }
}
// @lc code=end

