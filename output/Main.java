import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author silviase
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        C solver = new C();
        solver.solve(1, in, out);
        out.close();
    }

    static class C {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            // 尺取り
            int n = in.nextInt();
            int k = in.nextInt();
            long[] a = new long[n];
            int res = 0;

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                if (a[i] == 0) {
                    out.println(n);
                    return;
                }
            }

            int left = 0;
            int right = 0;
            long cmp = 1;
            // [l, r)として考える
            // 全て非零であるとしてよい、つまり単調増加
            while (left != n && right != n) {
                if (cmp * a[right] <= k) {
                    cmp *= a[right];
                    right++;
                    res = Math.max(res, right - left);
                } else {
                    cmp /= a[left];
                    left++;
                    if (left > right) {
                        right = left;
                        cmp = 1;
                    }
                }
            }
            out.println(res);

        }

    }
}

