import java.util.*;


/**
 * 参考文章:
 * 1. https://leetcode.cn/problems/path-with-maximum-probability/solution/dijkstra-suan-fa-xiang-jie-by-labuladong-8zhv/
 * 2. https://leetcode.cn/problems/network-delay-time/solution/gong-shui-san-xie-yi-ti-wu-jie-wu-chong-oghpz/
 */
public class Main {
    /**
     * 时间复杂度: O(V^2)
     * 空间复杂度: O(V)
     * 适合稠密图, 即边数E接近V^2
     */
    static int[] dijkstra(int[][] graph, int start) {
        int n = graph.length;
        int[] path = new int[n]; // 源点到每个节点的当前路径
        int[] dist = new int[n]; // 源点到每个结点的当前路径长度
        boolean[] visited = new boolean[n]; // 记录结点是否已经求出最短路径
        // 初始化
        Arrays.fill(path, n);
        Arrays.fill(dist, Integer.MAX_VALUE);
        path[start] = -1;
        dist[start] = 0;
        // 迭代n次, 每次可以求出一个结点的最短路径
        for(int i = 0; i < n; i++) {
            // 从"未曾确定最短路径的结点"中, 选择当前路径长度最短的顶点, 该结点的当前路径长度即为其最短路径长度
            int curr = -1;
            int min = Integer.MAX_VALUE;
            for(int node = 0; node < n; node++) {
                if(!visited[node] && dist[node] < min) {
                    curr = node;
                    min = dist[node];
                }
            }
            // 所有"未曾确定最短路径的结点"都不可达, 结束
            if(curr == -1)
                break;
            // 记录为"已经确定最短路径的结点"
            visited[curr] = true;
            // 遍历curr的所有"未曾确定最短路径的后续结点", 更新这些节点当前路径长度
            for(int next = 0; next < n; next++) {
                // "未曾确定最短路径的后续结点"
                if(graph[curr][next] < Integer.MAX_VALUE && !visited[next]) {
                    int newDist = dist[curr] + graph[curr][next];
                    if(newDist < dist[next]) {
                        path[next] = curr;
                        dist[next] = newDist; // 更新为更短的路径
                    }
                }
            }
        }
        // 通过path数组追溯最短路径
        printPath(path);
        return dist;
    }

    /**
     * 时间复杂度: O(E*logV)
     * 空间复杂度: O(V)
     * 适合稀疏图, 即边数E远小于V^2
     */
    static int[] dijkstra_heap(int[][] graph, int start) {
        int n = graph.length;
        int[] path = new int[n]; // 源点到每个节点的当前路径
        int[] dist = new int[n]; // 源点到每个结点的当前路径长度
        boolean[] visited = new boolean[n]; // 记录结点是否已经求出最短路径
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]); // 小根堆, 用于存放未确定结点, 堆顶是当前路径长度最小的未确定结点
        // 初始化
        Arrays.fill(path, n);
        Arrays.fill(dist, Integer.MAX_VALUE);
        path[start] = -1;
        dist[start] = 0;
        heap.offer(new int[]{start, 0});
        // 处理堆顶结点: 该结点若为已确定结点, 则跳过, 若为未确定结点, 则标记为已确定结点, 并处理其后续结点
        while(!heap.isEmpty()) {
            int curr = heap.poll()[0]; // 堆顶结点
            if(visited[curr])
                continue; // 该结点为已确认结点, 跳过
            visited[curr] = true; // 记录为已确认结点
            // 遍历curr的所有"未确定后续结点", 更新这些结点当前路径长度
            for(int next = 0; next < n; next++) {
                if(graph[curr][next]  < Integer.MAX_VALUE && !visited[next]) {
                    int newDist = dist[curr] + graph[curr][next];
                    if(newDist < dist[next]) {
                        path[next] = curr;
                        dist[next] = newDist; // 更新为更短的路径
                        heap.offer(new int[]{next, newDist}); // 加入堆
                    }
                }
            }
        }
        printPath(path);
        return dist;
    }

    static void printPath(int[] path) {
        // 通过path数组追溯最短路径
        int n = path.length;
        for(int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            if(path[i] == n) {
                // 无路径
                list.add(-1);
            } else {
                // 追溯最短路径
                int p = i;
                while(p != -1) {
                    list.add(p);
                    p = path[p];
                }
                Collections.reverse(list);
            }
            System.out.println(list);
        }
    }

    public static void main(String[] args) {
        int inf = Integer.MAX_VALUE;
        int[][] graph = {
            {inf, 10, inf, 30, 100},
            {inf, inf, 50, inf, inf},
            {inf, inf, inf, inf, 10},
            {inf, inf, inf, inf, 60},
            {inf, inf, inf, inf, inf}
        };
        int[] dist = dijkstra_heap(graph, 0);
        System.out.println(Arrays.toString(dist));
    }
}
