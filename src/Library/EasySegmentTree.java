package Library;

import java.util.ArrayList;
import java.util.function.BinaryOperator;

public class EasySegmentTree {
    protected ArrayList<Boolean> dat;
    protected BinaryOperator<Boolean> bo;
    protected int size;

    public EasySegmentTree(int size) {
        setSize(size);
    }

    public void setSize(int size) {
        int sz = 1;
        while (sz < size) {
            sz <<= 1;
        }
        this.size = sz;
        dat = new ArrayList<>();
        for (int i = 0; i < 2 * this.size - 1; i++) {
            dat.add(false);
        }
    }

    public void update(int i, boolean x) {
        i += size - 1;
        dat.set(i, x);
        while (i > 0) {
            i = (i - 1) / 2;
            dat.set(i, dat.get(i*2+1)|| dat.get(i*2+2));
        }
    }

    public boolean query(int l, int r) {
        int ll = l + size - 1;
        int rr = r + size - 1;
        boolean res = false;
        while (ll < rr) {
            if ((rr & 1) == 0) {
                rr--;
                res = res || dat.get(rr);
            }
            if ((ll & 1) == 0) {
                res = res || dat.get(ll);
                ll++;
            }
            ll = (ll - 1) >> 1;
            rr = (rr - 1) >> 1;
        }
        return res;
    }

}
