package Library;

import java.util.Objects;

public class Pair<T,U> {
    public T t;
    public U u;

    public Pair(T t , U u){
        this.t = t;
        this.u = u;
    }

    public void setT(T t){
        this.t = t;
    }

    public void setU(U u) {
        this.u = u;
    }

    public void set(T t, U u){
        this.t = t;
        this.u = u;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "t=" + t.toString() +
                ", u=" + u.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(t, pair.t) &&
                Objects.equals(u, pair.u);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t, u);
    }


}
