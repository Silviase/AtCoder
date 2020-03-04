package Library.Modular;

public class ModInt {
    long val;
    int MOD = (int) 1e9 + 7;

    public ModInt(long i) {
        this.val = (int) ((i + MOD) % MOD);
    }

    public ModInt add(long l) {
        return new ModInt(this.val + l);
    }

    public ModInt sub(long l) {
        return new ModInt(this.val - l);
    }

    public ModInt mul(long l) {
        return new ModInt(this.val * l);
    }

    public ModInt div(long l) {
        return this.mul(new ModInt(l).inv().val);
    }

    public ModInt add(ModInt m) {
        return new ModInt(this.val + m.getVal());
    }

    public ModInt sub(ModInt m){
        return new ModInt(this.val - m.getVal());
    }

    public ModInt mul(ModInt m){
        return  new ModInt(this.val * m.getVal());
    }

    public ModInt div(ModInt m){
        return this.mul(m.inv());
    }

    public ModInt inv() {
        return this.pow(MOD - 2);
    }

    public ModInt pow(long l) {
        ModInt res = new ModInt(1);
        ModInt v = new ModInt(this.val);
        while (l > 0) {
            if (l % 2 != 0) {
                res = res.mul(v.val);
            }
            v = v.mul(v.val);
            l /= 2;
        }
        return res;
    }


    public long getVal() {
        return val;
    }
}
