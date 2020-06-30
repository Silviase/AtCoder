package Library.Graph;

public class Edge {
    public int from, to;
    public long cost;

    public Edge(int to){
        this.to = to;
    }

    public Edge(int to, long cost){
        this.to = to;
        this.cost = cost;
    }

    public Edge(int from, int to, long cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public Edge inv(){
        return new Edge(to, from, cost);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
    }
}
