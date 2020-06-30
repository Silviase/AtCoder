package Library.Arithmetic;

import java.util.ArrayList;
import java.util.Arrays;

public class MathUtil {

    public static int ceil(int i1, int i2){
        return (i1 - 1 + i2) / i2;
    }

    public static long ceil(long l1, long l2){
        return (l1 - 1 + l2) / l2;
    }

    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static long nmPow2(long a) {
        return a & -a;
    }

    public static long factorial(int k) {
        return k == 0 ? 1 : factorial(k - 1) * k;
    }

    public static ArrayList<Integer> eratosthenesSieve(int n) {
        // n 以下の素数を列挙する, O(N*log(log N))
        ArrayList<Integer> prime = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            nums.add(i);
        }
        while (nums.get(0) <= Math.sqrt(n)) {
            int nxtPrime = nums.get(0);
            prime.add(nxtPrime);
            nums.removeIf(x -> (x % nxtPrime == 0));
        }
        prime.addAll(nums);
        return prime;
    }

    public static long ADD(long a, long b, long MOD) {
        return a + b >= MOD ? a + b - MOD : a + b;
    }

    public static long pow(long a, long n, long MOD) {
        return n == 0 ? 1 : (pow(a * a % MOD, n / 2, MOD) * (n % 2 == 1 ? a : 1)) % MOD;
    }

    public static long inv(long a, long MOD) {
        return pow(a, MOD - 2, MOD);
    }

    public static double[][] convolve(int[] a, int[] b) {
        // 次数上界がna(>0)である多項式A(x)の係数ベクトルをa[]、
        // 次数上界がnb(>0)である多項式B(x)の係数ベクトルをb[]とした時に、
        // A(x)とB(x)の乗算の結果である多項式C(x)の係数ベクトルを戻り値として返す。
        // C(x)の次数上界はna+nb-1。
        // a, bの配列は変更しない
        // 複素演算を用いたFFT。(再帰なし。アルゴリズムイントロダクション30.3参照)
        int na = a.length;
        int nb = b.length;
        int m = Math.max(na, nb);
        int n = Integer.highestOneBit(2 * m - 1) << 1;

        double[][] ca = new double[2][n];
        double[][] cb = new double[2][n];
        for (int i = 0; i < na; i++) {
            ca[0][i] = a[i];
        }
        for (int i = 0; i < nb; i++) {
            cb[0][i] = b[i];
        }

        ca = FFT(ca, false);
        cb = FFT(cb, false);

        for (int i = 0; i < n; i++) {
//			ca[i] = ca[i].multiply(cb[i]);
            double rtmp = ca[0][i] * cb[0][i] - ca[1][i] * cb[1][i];
            double itmp = ca[0][i] * cb[1][i] + ca[1][i] * cb[0][i];
            ca[0][i] = rtmp;
            ca[1][i] = itmp;
        }

//		Complex[] ret = Arrays.copyOf(FFT(ca, true), na + nb - 1);
        double[][] ret = FFT(ca, true);
        ret[0] = Arrays.copyOf(ret[0], na + nb - 1);
        ret[1] = Arrays.copyOf(ret[1], na + nb - 1);
        for (int i = 0; i < na + nb - 1; i++) {
            ret[0][i] /= n;
            ret[1][i] /= n;
        }

        return ret;
    }

    private static double[][] FFT(double[][] a, boolean inverse) {
        // 複素数の係数ベクトルaを持つ多項式を、
        // 1の原始n乗根のべき乗の点で評価(evaluation)する
        // inverseがtrueの時は、補間(interporation)になる。(1/nはしない)
        // aの内容は戻り値に変更される。
        int n = a[0].length;

//		Complex[] ret = new Complex[n];
        double[][] ret = a;
//		double[][] ret = new double[2][n];
        for (int k = 0; k < n; k++) {
            int rev = Integer.reverse(k);
            rev >>>= Integer.numberOfLeadingZeros(n) + 1;

            if (k < rev) {
                double tmp = ret[0][k];
                ret[0][k] = ret[0][rev];
                ret[0][rev] = tmp;
                tmp = ret[1][k];
                ret[1][k] = ret[1][rev];
                ret[1][rev] = tmp;
            }
//			ret[0][rev] = a[0][k];
//			ret[1][rev] = a[1][k];
        }

        for (int m = 2; m <= n; m *= 2) {
            double th = 2 * Math.PI / m;
            double[] wn = {Math.cos(th), Math.sin(th)};
            if (inverse) {
                wn[1] = -wn[1];
            }

            for (int k = 0; k < n; k += m) {
//				Complex w = new Complex(1, 0);
                double[] w = new double[2];
                w[0] = 1;

//				Complex t = new Complex(0, 0);
                double[] t = new double[2];
                for (int j = 0; j < m / 2; j++) {
//					Complex t = w.multiply(ret[k + j + m / 2]);
                    double tmp0 = ret[0][k + j + m / 2];
                    double tmp1 = ret[1][k + j + m / 2];
                    t[0] = w[0] * tmp0 - w[1] * tmp1;
                    t[1] = w[0] * tmp1 + w[1] * tmp0;
                    ret[0][k + j + m / 2] = ret[0][k + j] - t[0];
                    ret[1][k + j + m / 2] = ret[1][k + j] - t[1];
                    ret[0][k + j] = ret[0][k + j] + t[0];
                    ret[1][k + j] = ret[1][k + j] + t[1];

//					w = w.multiply(wn);
                    double rtmp = w[0] * wn[0] - w[1] * wn[1];
                    double itmp = w[0] * wn[1] + w[1] * wn[0];
                    w[0] = rtmp;
                    w[1] = itmp;
                }
            }
        }

        return ret;
    }

}
