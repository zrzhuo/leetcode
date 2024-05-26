public class Main {
    public static void main(String[] args) {
        int[] z = new ZAlgo().zAlgo_simple("aabaaa");
    }
}

class ZAlgo {
    // 参考文章：https://oi-wiki.org/string/z-func/
    public int[] zAlgo(String s) {
        int n = s.length();
        int[] z = new int[n];
        z[0] = 0; // z[0]一般初始化为0或者n
        int left = 0, right = 0; // 维护z-box
        for(int i = 1; i < n; i++) {
            // i落在z-box内，此时s[i...]与s[i-left...]的前right-i+1个字符相同
            if(i <= right) {
                if(z[i - left] < right - i + 1) {
                    z[i] = z[i - left]; // 由于s[i-left...]的前缀最多能匹配z[i-left]个字符，故s[i...]的前缀最多能也只能匹配z[i-left]个字符
                } else {
                    z[i] = right - i + 1; // 由于s[i...]与s[i-left...]的前right-i+1个字符相同，而z[i-left]大于等于right-i+1，故z[i]至少为right-i+1
                    // 超出z-box的部分向后暴力匹配
                    int p = right + 1 - i, q = right + 1;
                    while(q < n && s.charAt(p) == s.charAt(q)) {
                        z[i]++; // 每匹配成功一个字符，z[i]加1
                        p++;
                        q++;
                    }
                }
            }
            // i落在z-box外，暴力匹配
            else {
                int p = 0, q = i;
                while(q < n && s.charAt(p) == s.charAt(q)) {
                    z[i]++; // 每匹配成功一个字符，z[i]加1
                    p++;
                    q++;
                }
            }
            // 更新z-box
            if(i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }
        return z;
    }

    // 简化版
    public int[] zAlgo_simple(String s) {
        int n = s.length();
        int[] z = new int[n];
        z[0] = 0; // z[0]一般初始化为0或者n
        int left = 0, right = 0; // 维护z-box
        for(int i = 1; i < n; i++) {
            if(i <= right) {
                z[i] = Math.min(z[i - left], right - i + 1); // i落在z-box内
            } else {
                z[i] = 0; // i落在z-box外
            }
            // 超出z-box的部分向后暴力匹配
            while(z[i] + i < n && s.charAt(z[i]) == s.charAt(z[i] + i)) {
                z[i]++;
            }
            // 更新z-box
            if(i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }
        return z;
    }
}
