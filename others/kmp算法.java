public class Main {
    public static void main(String[] args) {
        String s = "abcdabcdefabc", p = "abcde";
        System.out.println(new KMP().indexOf(s, p));
    }
}

class KMP {
    public int indexOf(String s, String p) {
        return kmp(s,p);
    }

    private int kmp(String s, String p) {
        int m = s.length(), n = p.length();
        if(m < n)
            return -1;
        int[] next = getNext(p); // 计算next数组
        int i = 0, j = 0;
        while (i < m && j < n) {
            if(s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else if(j > 0) {
                j = next[j]; // 匹配失败, 回退j
            } else {
                i++; // 匹配失败，且j已经无法再回退，此时前进i
            }
            if(j == n)
                return i - n; // 匹配成功，返回起始下标
        }
        return -1;
    }

    private int[] getNext(String p) {
        int n = p.length();
        // next[i]: 子串p[0...i-1]的"相等前后缀"的最大长度
        int[] next = new int[n + 1];
        // 初始化为-1, 作为哨兵
        next[0] = -1;
        // 递推
        for (int i = 1; i <= n; i++) {
            int now = next[i - 1];
            while (now != -1 && p.charAt(now) != p.charAt(i - 1)) {
                now = next[now];
                // 注意: 若统计这里的while循环次数, 会发现while循环总次数(整个外层for循环)一定是n - 1
                // 故整体时间复杂度为O(n)
            }
            next[i] = now + 1;
        }
        return next;
    }
}