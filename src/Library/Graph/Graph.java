package Library.Graph;

import Library.DataStructure.UnionFind.UnionFind;

import java.util.*;

public class Graph {
    public int size;
    public ArrayList<Edge>[] adj;
    public int[] inDeg;
    public int[] vertexCost;
    public boolean hasNegativeLoop = false;

    @SuppressWarnings("unchecked")
    public Graph(int size) {
        this.size = size;
        adj = new ArrayList[size];
        inDeg = new int[size];
        vertexCost = new int[size];
        for (int i = 0; i < size; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to, long cost){
        addEdge(new Edge(from, to, cost));
    }

    public void addUnDirectedEdge(int from, int to, long cost){
        addEdge(from, to, cost);
        addEdge(to, from, cost);
    }

    public void addEdge(Edge e) {
        adj[e.from].add(e);
        inDeg[e.to]++;
    }

    public void addUnDirectedEdge(Edge e) {
        addEdge(e);
        addEdge(e.inv());
    }

    public ArrayList<Edge>[] getAdj() {
        return adj;
    }

    public long[] bellmanFord(int from) {
        hasNegativeLoop = false;
        long[] res = new long[size];
        for (int i = 0; i < size; i++) {
            res[i] = i == from ? 0 : (long) 1e30;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (Edge e : adj[j]) {
                    int f = j;
                    int t = e.to;
                    if (res[t] > res[f] + e.cost) {
                        res[t] = res[f] + e.cost;
                        if (i == size - 1) {
                            hasNegativeLoop = true;
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    public long[] dijkstra(int from) {
        long[] d = new long[size];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(e -> d[e]));
        for (int i = 0; i < size; i++) {
            d[i] = (i == from) ? 0 : (long) 1e18;
            pq.add(i);
        }
        // System.out.println(Arrays.toString(pq.toArray()));
        while (!pq.isEmpty()) {
            int search = pq.poll();
            // System.out.println("now: " + search);
            ArrayList<Edge> edges = adj[search];
            for (Edge e : edges) {
                // System.out.println(e.toString());
                long cmp = d[search] + e.cost;
                if (d[e.to] > d[search] + e.cost) {
                    // 更新が入る
                    // 処理を入れたい場合はここに入れる
                    d[e.to] = d[search] + e.cost;
                    pq.add(e.to);
                }
            }
            //  System.out.println(Arrays.toString(pq.toArray()));
        }
        return d;
    }

    public ArrayList<Edge> kruscal() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(e -> e.cost));
        ArrayList<Edge> minimumBoundedTree = new ArrayList<>();
        UnionFind uf = new UnionFind(size);

        for (ArrayList<Edge> list : adj)
            pq.addAll(list);

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (!uf.same(e.from, e.to)) {
                minimumBoundedTree.add(e);
                uf.union(e.from, e.to);
            }
        }
        return minimumBoundedTree;
    }


    public long[][] warshallFloyd() {
        long[][] dist = new long[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE/3);
            dist[i][i] = 0;
        }

        for (ArrayList<Edge> ale : adj) {
            for (Edge e : ale) {
                dist[e.from][e.to] = Math.min(e.cost, dist[e.from][e.to]);
            }
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }

        return dist;
    }

    public void setVertexCost(int index, int cost){
        vertexCost[index] = cost;
    }


}
