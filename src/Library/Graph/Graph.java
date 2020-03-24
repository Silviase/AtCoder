package Library.Graph;

import java.util.*;

public class Graph {
    int n;
    ArrayList<Edge>[] adj;
    long[] vertexCost;
    boolean hasNegativeLoop = false;

    public Graph(int size) {
        n = size;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        vertexCost = new long[n];
    }

    public void addEdge(Edge e) {
        adj[e.from].add(e);
    }

    public void addUnDirectedEdge(Edge e) {
        addEdge(e);
        addEdge(e.inv());
    }

    public void setVertexCost(int index, long newCost){
        vertexCost[index] = newCost;
    }

    public void addVertexCost(int index, int costToAdd){
        vertexCost[index] += costToAdd;
    }

    public ArrayList<Edge>[] getAdj() {
        return adj;
    }

    public long[] bellmanFord(int from) {
        hasNegativeLoop = false;
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            res[i] = i == from ? 0 : (long) 1e30;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (Edge e : adj[j]) {
                    int f = j;
                    int t = e.to;
                    if (res[t] > res[f] + e.cost) {
                        res[t] = res[f] + e.cost;
                        if (i == n - 1) {
                            hasNegativeLoop = true;
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    public boolean HasNegativeLoop() {
        return this.hasNegativeLoop;
    }

    public long[] dijkstra(int from) {
        long[] d = new long[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(e -> d[e]));
        for (int i = 0; i < n; i++) {
            d[i] = (i == from) ? 0 : (long) 1e18;
            pq.add(i);
        }
        // System.out.println(Arrays.toString(pq.toArray()));
        while(!pq.isEmpty()){
            int search = pq.poll();
            // System.out.println("now: " + search);
            ArrayList<Edge> edges = adj[search];
            for (Edge e: edges){
                // System.out.println(e.toString());
                long cmp = d[search] + e.cost;
                if(d[e.to] > d[search] + e.cost){
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

}
