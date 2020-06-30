package Library.DataStructure.UnionFind;

public class WeightedUnionFind extends UnionFind {

    long[] dif;

    public WeightedUnionFind(int n) {
        super(n);
        dif = new long[n];
    }

    public void union(int x, int y, long w) {
        w = w + dif[x] - dif[y];
        x = find(x);
        y = find(y);
        if (height[x] < height[y]) {
            int tmp = x;
            x = y;
            y = tmp;
            w = -w;
        }
        if (height[x] == height[y]) {
            ++height[x];
        }
        parent[y] = x;
        dif[y] = w;
    }

    @Override
    public int find(int x) {
        if (parent[x] == x) return x;
        int r = find(parent[x]);
        dif[x] += dif[parent[x]];
        return parent[x] = r;
    }

    public long weight(int idx) {
        return dif[idx];
    }

    public long dif(int x, int y) {
        return dif[y] - dif[x];
    }


}
