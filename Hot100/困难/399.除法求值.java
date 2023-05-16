/*
 * @lc app=leetcode.cn id=399 lang=java
 *
 * [399] 除法求值
 */

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 构建字符串变量到下标的映射, 方便建图
        Map<String, Integer> index = new HashMap<>();
        int idx = 0;
        for(List<String> equ : equations) {
            if(!index.containsKey(equ.get(0)))
                index.put(equ.get(0), idx++);
            if(!index.containsKey(equ.get(1)))
                index.put(equ.get(1), idx++);
        }
        // 建图, 邻接表, 有向图, a->b的权重为a/b
        int n = index.size();
        List<double[]>[] graph = new List[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < values.length; i++) {
            int prev = index.get(equations.get(i).get(0));
            int next = index.get(equations.get(i).get(1));
            graph[prev].add(new double[]{next, values[i]});
            graph[next].add(new double[]{prev, 1.0 / values[i]});
        }
        // query
        int m = queries.size();
        double[] result = new double[m];
        for(int i = 0; i < m; i++) {
            int prev = index.getOrDefault(queries.get(i).get(0), -1);
            int next = index.getOrDefault(queries.get(i).get(1), -1);
            if(prev == -1 || next == -1)
                result[i] = -1.0; // 不存在相应的字符串
            else if(prev == next) 
                result[i] = 1.0; // 除数和被除数相同
            else
                result[i] = bfs(graph, prev, next); // bfs求解query
        }
        return result;
    }

    double bfs(List<double[]>[] graph, int start, int target) {
        int n = graph.length;
        double[] dist = new double[n]; // 记录start到各结点的路径距离, 路径距离为权重之积
        Queue<Integer> queue = new LinkedList<>();
        // 初始化
        Arrays.fill(dist, -1.0);
        dist[start] = 1.0;
        queue.offer(start);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(double[] node : graph[curr]) {
                int next = (int) node[0];
                double d = node[1];
                if(dist[next] == -1) {
                    dist[next] = dist[curr] * d; // 路径距离为权重之积
                    if(next == target)
                        return dist[target]; // 找到target, 直接返回
                    queue.offer(next);
                }
            }
        }
        return dist[target];
    }
}



// @lc code=start
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 构建字符串变量到下标的映射, 方便建图
        Map<String, Integer> index = new HashMap<>();
        int idx = 0;
        for(List<String> equ : equations) {
            if(!index.containsKey(equ.get(0)))
                index.put(equ.get(0), idx++);
            if(!index.containsKey(equ.get(1)))
                index.put(equ.get(1), idx++);
        }
        // 建图, 邻接矩阵, 有向图, a->b的权重即为a/b的值
        int n = index.size();
        double[][] graph = new double[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], -1.0);
        }
        for(int i = 0; i < values.length; i++) {
            int prev = index.get(equations.get(i).get(0));
            int next = index.get(equations.get(i).get(1));
            graph[prev][next] = values[i];
            graph[next][prev] = 1.0 / values[i];
        }
        // floyd
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    // i/k存在, 且k/j存在, 则i/j即存在
                    if(graph[i][k] > 0 && graph[k][j] > 0)
                        graph[i][j] = graph[i][k] * graph[k][j]; // i/j = i/k * k/j
                }
            }
        }
        // query
        int m = queries.size();
        double[] result = new double[m];
        for(int i = 0; i < m; i++) {
            int prev = index.getOrDefault(queries.get(i).get(0), -1);
            int next = index.getOrDefault(queries.get(i).get(1), -1);
            if(prev == -1 || next == -1)
                result[i] = -1.0; // 不存在相应的字符串
            else if(prev == next)
                result[i] = 1.0; // 除数和被除数相同
            else
                result[i] = graph[prev][next]; 
        }
        return result;
    }
}
// @lc code=end