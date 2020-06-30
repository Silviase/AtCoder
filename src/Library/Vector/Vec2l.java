package Library.Vector;

import java.util.Objects;

public class Vec2l {
    public long x, y;

    public Vec2l(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public void set(long x, long y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vec2l{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2l vec2l = (Vec2l) o;
        return x == vec2l.x &&
                y == vec2l.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

