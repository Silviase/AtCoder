package Library;

public class Arith {
    public int modPerm(int n, int k, int MOD) {
        long res = 1;
        for (int i = 0; i < k; i++) {
            res *= (n - i);
            res %= MOD;
        }
        return (int) res;
    }

    public int modPow(int a, int x, int MOD) {
        long res = 1;
        long mul = a;
        while (x != 0) {
            if (x % 2 != 0) {
                res *= mul;
                res %= MOD;
            }
            mul *= mul;
            mul %= MOD;
            x /= 2;
        }
        return (int) res;
    }

    public static long gcd(long a, long b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }



}
