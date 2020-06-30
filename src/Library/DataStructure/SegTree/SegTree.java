package Library.DataStructure.SegTree;

import java.util.ArrayList;


// fは毎回実装する.RMQならMath::minでよい
public abstract class SegTree<T> {
    public ArrayList<T> dat;
    public int size;
    public T unit;

    public void init(int size, T unit) {
        this.unit = unit;
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
            dat.set(i, f(dat.get(i * 2 + 1), dat.get(i * 2 + 2)));
        }
    }

    public T get(int i) {
        return dat.get(i + size - 1);
    }

    public T query(int a, int b){return query(a,b,0,0,size);}

    public T query(int a, int b, int k, int l, int r){
        if(b <= l || r <= a) { return unit; }
        if(a <= l && r <= b) { return dat.get(k); }
        return f(query(a, b, k * 2 + 1, l, (l + r) / 2), query(a, b, k * 2 + 2, (l + r) / 2, r));
    }

    abstract public T f(T t1, T t2);
}
