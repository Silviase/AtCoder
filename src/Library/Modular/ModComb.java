package Library.Modular;

public class ModComb {
    private int size;
    private ModInt[] fac;
    private ModInt[] inv;

    public ModComb(int n) {
        size = n;
        fac = new ModInt[n + 1];
        inv = new ModInt[n + 1];
        makeFac();
    }

    private void makeFac() {
        for (int i = 0; i <= size; i++) {
            if (i == 0) {
                fac[i] = new ModInt(1);
            } else {
                fac[i] = fac[i - 1].mul(i);
            }
            inv[i] = fac[i].inv();
        }
    }

    public ModInt combFac(int n, int k) {
        if (n < k) {
            return new ModInt(0);
        }
        return fac[n].mul(inv[k].getVal()).mul(inv[n - k].getVal());
    }

}
