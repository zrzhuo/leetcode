// 朴素并查集
class UnionFind {
    private int[] father; // 父结点

    public UnionFind(int n){
        father = new int[n];
        for(int i = 0; i < n; ++i){
            father[i] = i; // 初始时，每个结点为一个集合
        }
    }

    public int find(int x){
        if(father[x] == x)
            return x; // 递归出口
        father[x] = find(father[x]); // 路径压缩：使parent[x]存放的是x所处集合的根结点
        return father[x];
    }

    public void union(int x, int y){
        int px = find(x), py = find(y);
        if(px != py){
            father[px] = py; // 将px的父结点置为py
        }
    }
}

// 维护size和count的并查集
class UnionFind {
    private int[] father; // 父结点
    private int[] size; // 集合大小（只针对根结点有效，即根结点所处集合的结点个数）
    private int count; // 集合个数

    public UnionFind(int n){
        father = new int[n];
        size = new int[n];
        for(int i = 0; i < n; ++i){
            father[i] = i;
            size[i] = 1; // 集合的大小为1
            count = n; // 集合数量为n
        }
    }

    public int find(int x){
        if(father[x] == x)
            return x; // 递归出口
        father[x] = find(father[x]); // 路径压缩：使father[x]存放的是x所处集合的根结点
        return father[x];
    }

    public void union(int x, int y){
        int px = find(x), py = find(y);
        if(px != py){
            father[px] = py; // 将px的父结点置为py
            size[py] += size[px]; // 维护集合大小
            count--; // 集合数量减1
        }
    }

    public int getSize(int x) {
        return size[find(x)]; // 返回结点所在集合的大小
    }

    public int getCount() {
        return count; // 返回集合数量
    }
}