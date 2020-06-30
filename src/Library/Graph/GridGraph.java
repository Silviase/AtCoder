package Library.Graph;

public class GridGraph extends Graph {

    public int h, w;

    public GridGraph(int h, int w) {
        super(h * w + 5);
        this.h = h;
        this.w = w;
    }

    public void connect4() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i > 0)      this.addUnDirectedEdge(new Edge(i * w + j, (i - 1) * w + j, 0));
                if (i < h - 1)  this.addUnDirectedEdge(new Edge(i * w + j, (i + 1) * w + j, 0));
                if (j > 0)      this.addUnDirectedEdge(new Edge(i * w + j, i * w + (j - 1), 0));
                if (j < w - 1)  this.addUnDirectedEdge(new Edge(i * w + j, i * w + (j + 1), 0));
            }
        }
    }

}
