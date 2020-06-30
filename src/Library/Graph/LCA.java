package Library.Graph;

import java.util.Arrays;

public class LCA {
    // 0を根とする根付き木
    Graph graph;
    int[][] par; // [n][k], nのk次の親
    int[] depth; // [i], iの深さ
    int k = 1;

    public LCA(Graph g) {
        this.graph = g;
        int v = g.size;
        while ((1 << k) < v) k++;
        par = new int[v][k + 1];
        depth = new int[v];
        for (int i = 0; i < v; i++) {
            Arrays.fill(par[i], -1);
        }
        Arrays.fill(depth, -1);
        dfs(0, -1, 0);
        for (int i = 0; i < k - 1; i++) {
            for (int j = 0; j < v; j++) {
                if (par[j][i] < 0) {
                    par[j][i + 1] = -1;
                } else {
                    par[j][i + 1] = par[par[j][i]][i];
                }
            }
        }
    }

    void dfs(int v, int p, int d) {
        par[v][0] = p;
        depth[v] = d;
        for (Edge e : graph.adj[v]) {
            if (e.to != p) dfs(e.to, v, d + 1);
        }

    }

    public int query(int l, int r) {
        // lを深くする
        if (depth[l] < depth[r]) {
            int tmp = l;
            l = r;
            r = tmp;
        }

        // depth を等しくする
        for (int i = 0; i < k; i++) {
            if ((((depth[l] - depth[r]) >> i) & 1) == 1) {
                l = par[l][i];
            }
        }

        if (l == r) return l;

        for (int i = k - 1; i >= 0; i--) {
            if (par[l][i] != par[r][i]) {
                l = par[l][i];
                r = par[r][i];
            }
        }
        return par[l][0];
    }

    public int dist(int u, int v) {
        return depth[u] + depth[v] - depth[query(u, v)] * 2;
    }

}
