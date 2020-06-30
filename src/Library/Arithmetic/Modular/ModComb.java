package Library.Arithmetic.Modular;

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

    public ModInt combination(int n, int k) {
        if (n < k || k < 0) return new ModInt(0);
        return fac[n].mul(inv[k].getVal()).mul(inv[n - k].getVal());
    }

    public ModInt permutate(int n, int k) {
        return fac[n].mul(inv[k]);
    }

    public ModInt getFac(int n) {
        return fac[n];
    }

    public ModInt getInv(int n) {
        return inv[n];
    }

}
