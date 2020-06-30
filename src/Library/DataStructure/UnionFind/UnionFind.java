package Library.DataStructure.UnionFind;

public class UnionFind {
    protected int[] parent;
    protected int[] height;
    protected int[] size;

    public UnionFind(int size) {
        this.parent = new int[size];
        this.height = new int[size];
        this.size = new int[size];

        for (int i = 0; i < size; i++) {
            makeSet(i);
        }
    }

    private void makeSet(int i) {
        parent[i] = i;
        height[i] = 0;
        size[i] = 1;
    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {

        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) { //既に結合済み
            return;
        }

        if (height[rootX] > height[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];

        } else if (height[rootX] < height[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];

        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
            height[rootX]++;
        }
    }

    public int getSize(int x) {
        int rootX = find(x);
        return size[rootX];
    }

    public boolean same(int x, int y){
        return find(x) == find(y);
    }

}
