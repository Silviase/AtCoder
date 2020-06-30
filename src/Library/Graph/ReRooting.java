package Library.Graph;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class ReRooting<T> {

    int size;
    T unit;
    BinaryOperator<T> operator;
    BiFunction<T, Integer, T> nodeOperator;
    Object[][] dp;
    Object[] res;
    ArrayList<Integer>[] adj;
    ArrayList<Integer>[] index4Adj;

    @SuppressWarnings("unchecked")
    public ReRooting(ArrayList<Edge> edges, int size, T unit, BinaryOperator<T> operator, BiFunction<T, Integer, T> nodeOperator) {
        this.size = size;
        adj = new ArrayList[size];
        index4Adj = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            adj[i] = new ArrayList<>();
            index4Adj[i] = new ArrayList<>();
        }
        for (Edge e : edges) {
            index4Adj[e.from].add(adj[e.to].size());
            index4Adj[e.to].add(adj[e.from].size());
            adj[e.to].add(e.from);
            adj[e.from].add(e.to);
        }
        this.unit = unit;
        this.operator = operator;
        this.nodeOperator = nodeOperator;
        dp = new Object[size][];
        res = new Object[size];
        for (int i = 0; i < size; i++) {
            dp[i] = new Object[adj[i].size()];
        }
        if (size > 1) init();
        else res[0] = this.nodeOperator.apply(unit, 0);
    }

    @SuppressWarnings("unchecked")
    private void init() {
        int[] par = new int[size];
        int[] ord = new int[size];

        int index = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        par[0] = -1;
        while (!stack.isEmpty()) {
            int node = stack.pop();
            ord[index++] = node;
            for (Integer nxt : adj[node]) {
                if (nxt == par[node]) continue;
                stack.push(nxt);
                par[nxt] = node;
            }
        }

        for (int i = ord.length - 1; i >= 1; i--) {
            int node = ord[i];
            int parent = par[node];
            T acc = unit;
            int parIdx = -1;
            for (int j = 0; j < adj[node].size(); j++) {
                if (adj[node].get(j) == parent) {
                    parIdx = j;
                } else {
                    acc = operator.apply(acc, (T) dp[node][j]);
                }
            }
            dp[parent][index4Adj[node].get(parIdx)] = nodeOperator.apply(acc, node);
        }

        // 葉に向かう
        for (int node : ord) {
            T acc = unit;
            Object[] fromLeaf = new Object[adj[node].size()];
            fromLeaf[fromLeaf.length - 1] = unit;
            for (int j = fromLeaf.length - 1; j >= 1; j--)
                fromLeaf[j - 1] = operator.apply((T) dp[node][j], (T)fromLeaf[j]);
            for (int j = 0; j < fromLeaf.length; j++) {
                dp[adj[node].get(j)][index4Adj[node].get(j)] = nodeOperator.apply(operator.apply(acc, (T)fromLeaf[j]), node);
                acc = operator.apply(acc, (T) dp[node][j]);
            }
            res[node] = nodeOperator.apply(acc, node);
        }


    }

    @SuppressWarnings("unchecked")
    public T query(int index) {
        return (T) res[index];
    }

}
