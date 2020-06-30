package Library.Vector;

import java.util.Objects;

public class Vec2i {
    public int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Vec2i v){
        // xの降順 -> yの降順
        if(this.x < v.x) return 1;
        else if(this.x > v.x) return -1;
        else if(this.y < v.y) return 1;
        else if(this.y > v.y) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Vec2i{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2i vec2i = (Vec2i) o;
        return x == vec2i.x &&
                y == vec2i.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static Vec2i add(Vec2i v1, Vec2i v2) {
        return new Vec2i(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vec2i sub(Vec2i v1, Vec2i v2) {
        return new Vec2i(v1.x - v2.x, v1.y - v2.y);
    }

    public static double dist(Vec2i v1, Vec2i v2) {
        return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }

}
