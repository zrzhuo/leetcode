/*
 * @lc app=leetcode.cn id=1015 lang=java
 *
 * [1015] 可被 K 整除的最小整数
 */

// @lc code=start
class Solution {
    public int smallestRepunitDivByK(int k) {
        // 偶数和5的倍数一定不能整除11111这样的数, 余数一定是1
        if(k % 2 == 0 || k % 5 == 0)
            return -1;
        if(k == 1)
            return 1;
        int remainder = 1;  // 当前的余数
        int len = 1; // 当前的数长
        while(remainder != 0) {
            remainder = (remainder * 10 + 1) % k;
            len++;
        }
        return len;
    }
}
// @lc code=end


a > 0, b > 0, k > 0, 关于取模运算的一些规则: 
    1. (a + b) % k = (a % k + b % k) % k
    2. (a + 1) % k = (a % k + 1 % k) % k = (a % k + 1) % k
    3. (a * b) % k = ((a % k) * b) % k

则有:
    1    % k = 1
    11   % k = (1 * 10 + 1)   % k = ((1   * 10) % k + 1) % k = (((1   % k) * 10) % k + 1) % k = ((1   % k) * 10 + 1) % k
    111  % k = (11 * 10 + 1)  % k = ((11  * 10) % k + 1) % k = (((11  % k) * 10) % k + 1) % k = ((11  % k) * 10 + 1) % k
    1111 % k = (111 * 10 + 1) % k = ((111 * 10) % k + 1) % k = (((111 % k) * 10) % k + 1) % k = ((111 % k) * 10 + 1) % k
简化之后:
    1    % k = 1
    11   % k = ((1   % k) * 10 + 1) % k
    111  % k = ((11  % k) * 10 + 1) % k
    1111 % k = ((111 % k) * 10 + 1) % k
