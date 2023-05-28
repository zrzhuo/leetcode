/*
 * @lc app=leetcode.cn id=1962 lang=java
 *
 * [1962] 移除石子使总数最小
 */

// @lc code=start
class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int sum = 0;
        for(int pile : piles) {
            sum += pile;
            heap.offer(pile);
        }
        for(int i = 0; i < k; i++) {
            if(heap.size() == 0)
                break;
            int max = heap.poll();
            sum -= max / 2;
            heap.offer(max - max / 2);
        }
        return sum;
    }
}
// @lc code=end


class Solution {
    public int minStoneSum(int[] piles, int k) {
        int cnt[] = new int[10001];
        int sum = 0, max = Integer.MIN_VALUE;
        for(int pile:piles){
            ++cnt[pile];
            sum += pile;
            max = Math.max(max, pile);
        }
        int i = max;
        while(i > 0 && k > 0){
            int count = Math.min(cnt[i], k);
            sum -= i/2 * count;
            cnt[i - i/2] += count;
            k -= count;
            --i;
        }
        return sum;
    }
}