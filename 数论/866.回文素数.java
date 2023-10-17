/*
 * @lc app=leetcode.cn id=866 lang=java
 *
 * [866] 回文素数
 */

// @lc code=start
class Solution {
    public int primePalindrome(int n) {
        int res = n;
        while(true) {
            if(isPrime(res) && isPalind(res))
                return res;
            res++;
            // 长度为偶数的回文数中, 只有11是素数, 故可以跳过长度为偶数的数
            if(12 <= res && res <= 99)
                res = 100;
            if(1000 <= res && res <= 9999)
                res = 10000;
            if(100000 <= res && res <= 999999)
                res = 1000000;
            if (10000000 <= res && res <= 99999999)
                res = 100000000;
        }
    }

    private boolean isPrime(int n) {
        if(n == 1)
            return false; // 1不是素数
        for(int i = 2; i <= (int)Math.sqrt(n); i++) {
            if(n % i == 0)
                return false;
        }
        return true;
    }

    private boolean isPalind(int n) {
        int a = n, b = 0;
        while(n > 0) {
            b *= 10;
            b += n % 10;
            n /= 10;
        }
        return a == b;
    }
}
// @lc code=end

