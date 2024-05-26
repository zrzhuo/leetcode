/*
 * @lc app=leetcode.cn id=3008 lang=java
 *
 * [3008] 找出数组中的美丽下标 II
 */

// @lc code=start
class Solution {
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> res = new ArrayList<>();
        List<Integer> sa = kmpGetIdx(s, a), sb = kmpGetIdx(s, b); // kmp算法求下标列表
        int m = sa.size(), n = sb.size();
        if(m == 0 || n == 0) {
            return res;
        }
        // 双指针
        int i = 0, j = 0;
        while(i < m) {
            while(j < n && sa.get(i) - sb.get(j) > k) {
                j++;
            }
            if(j < n && sb.get(j) - sa.get(i) <= k) {
                res.add(sa.get(i));
            }
            i++;
        }
        return res;
    }

    List<Integer> kmpGetIdx(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int m = s.length(), n = p.length();
        if(m < n) {
            return res;
        }
        int[] next = getNext(p); // 获取next数组
        int i = 0, j = 0;
        while(i < m) {
            if(s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else if(j > 0) {
                j = next[j];
            } else {
                i++;
            }
            // 匹配成功，记录下标，回退j以继续寻找
            if(j == n) {
                res.add(i - n);
                j = next[j];
            }
        }
        return res;
    }

    int[] getNext(String p) {
        int[] next = new int[p.length() + 1];
        next[0] = -1;
        for(int i = 1; i <= p.length(); i++) {
            int c = next[i - 1];
            while(c != -1 && p.charAt(c) != p.charAt(i - 1)) {
                c = next[c];
            }
            next[i] = c + 1;
        }
        return next;
    }
}
// @lc code=end

