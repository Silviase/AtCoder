package Library;

import java.util.ArrayList;
import java.util.function.BinaryOperator;

public class SegmentTree<T> {

    protected ArrayList<T> dat;
    protected BinaryOperator<T> bo;
    protected T unit;
    protected int size;

    public SegmentTree(BinaryOperator<T> binaryOperator, T unit) {
        bo = binaryOperator;
        this.unit = unit;
    }

    public void setSize(int size) {
        int sz = 1;
        while (sz < size) {
            sz <<= 1;
        }
        this.size = sz;
        dat = new ArrayList<>();
        for (int i = 0; i < 2 * this.size - 1; i++) {
            dat.add(unit);
        }
    }

    public void update(int i, T x) {
        i += size - 1;
        dat.set(i, x);
        while (i > 0) {
            i = (i - 1) / 2;
            dat.set(i, bo.apply(dat.get(i * 2 + 1), dat.get(i * 2 + 2)));
        }
    }

    public T query(int l, int r) {
        int ll = l + size - 1;
        int rr = r + size - 1;
        T res = unit;
        while (ll < rr) {
            if ((rr & 1) == 0) {
                rr--;
                res = bo.apply(res, dat.get(rr));
            }
            if ((ll & 1) == 0) {
                res = bo.apply(res, dat.get(ll));
                ll++;
            }
            ll = (ll - 1) >> 1;
            rr = (rr - 1) >> 1;
        }
        return res;
    }


}