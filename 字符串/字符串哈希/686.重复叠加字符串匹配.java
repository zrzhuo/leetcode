/*
 * @lc app=leetcode.cn id=686 lang=java
 *
 * [686] 重复叠加字符串匹配
 */

// @lc code=start
// 字符串哈希
class Solution {
    public int repeatedStringMatch(String a, String b) {
        int m = a.length(), n = b.length();
        long base = 31, mod = (int)1e9 + 7, mul = 1; // 字符串哈希b
        for(int i = 0; i < n - 1; i++) {
            mul = (mul * base) % mod;
        }
        // 计算b的哈希值
        long bHash = 0;
        for(int i = 0; i < n; i++) {
            bHash = (bHash * base + b.charAt(i)) % mod;
        }
        // 滚动计算子串的哈希值
        long aHash = 0;
        int left = 0, right = 0, len = 0;
        while(len < n) {
            aHash = (aHash * base + a.charAt(right % m)) % mod;
            right++;
            len++;
        }
        if(aHash == bHash) {
            return (right - 1) / m + 1; // 哈希值相等，根据right计算a的重复次数
        }
        while(left < m) {
            aHash = (aHash - mul * a.charAt(left % m) % mod + mod) % mod; // 注意存在减法时的取模操作
            left++;
            aHash = (aHash * base + a.charAt(right % m)) % mod;
            right++;
            if(aHash == bHash) {
                return (right - 1) / m + 1; // 哈希值相等，根据right计算a的重复次数
            }
        }
        return -1;
    }
    }
}
// @lc code=end

// kmp
class Solution {
    public int repeatedStringMatch(String a, String b) {
        int m = a.length(), n = b.length();
        // 计算b的next数组
        int[] next = getNext(b);
        // 在a的重复叠加串中寻找b
        int i = 0, j = 0;
        while(j < n) {
            if(a.charAt(i % m) == b.charAt(j)) { // i取模
                i++;
                j++;
            } else if(j > 0) {
                j = next[j];
            } else {
                i++;
            }
            if(i - j >= m) {
                return - 1; // 无法匹配成功
            }
        }
        return (i - 1) / m + 1;
    }

    int[] getNext(String s) {
        int n = s.length();
        int[] next = new int[n + 1];
        next[0] = -1;
        for(int i = 1; i <= n; i++) {
            int cur = next[i - 1];
            while(cur != -1 && s.charAt(cur) != s.charAt(i - 1)) {
                cur = next[cur];
            }
            next[i] = cur + 1;
        }
        return next;
    }
}
