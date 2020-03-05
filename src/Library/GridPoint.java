package Library;

import Library.Graph.Graph;

public class GridPoint {
    public long x, y;

    public GridPoint(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "grid:(" + x + ", " + y + ')';
    }

}
