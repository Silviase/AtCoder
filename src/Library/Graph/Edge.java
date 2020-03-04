package Library.Graph;

public class Edge {
    int from, to;
    long cost;
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

    public long getCost() {
        return cost;
    }

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
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
