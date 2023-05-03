/*
 * @lc app=leetcode.cn id=857 lang=java
 *
 * [857] 雇佣 K 名工人的最低成本
 */

// @lc code=start
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = wage.length;
        // 封装每个工人
        Woker[] wokers = new Woker[n];
        for(int i = 0; i < n; i++) {
            wokers[i] = new Woker(quality[i], wage[i]);
        }
        // 将工人按wpq从小到大排序，贪心的选择wpq较小的工人
        Arrays.sort(wokers, (a, b) -> {
            if(a.wpq < b.wpq) // 注意wpq的类型是double
                return -1;
            else
                return 1;
        });
        // 当前工人组的应付总薪水 = wpq的最大值 * quailty之和， 故需: wpq的最大值尽可能的小，quailty之和尽可能的小
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a); // 最大堆, 用于记录当前工人组的quailty最大值
        int sum = 0; // 记录当前工人组的quailty之和
        for(int i = 0; i < k; i++) {
            heap.add(wokers[i].quality);
            sum += wokers[i].quality;
        } 
        double result = wokers[k-1].wpq * sum; // 当前工人组的应付总薪水
        for(int i = k; i < n; i++) {
            // 从当前工人组中剔除quality最大的，加上下一个工人
            sum = sum - heap.poll() + wokers[i].quality;
            heap.add(wokers[i].quality);
            result = Math.min(result, wokers[i].wpq * sum); // 更新应付总薪水的最小值
        }
        return result;
    }

    class Woker {
        int quality, wage;
        double wpq; // 单位质量需支付的薪水
        public Woker(int quality, int wage) {
            this.quality = quality;
            this.wpq = wpq;
            this.wpq = 1.0 * wage / quality;
        }
    }
}
// @lc code=end

