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
        BModerateDifferences solver = new BModerateDifferences();
        solver.solve(1, in, out);
        out.close();
    }

    static class BModerateDifferences {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt() - 1;
            long a = in.nextLong();
            long b = in.nextLong();
            long c = in.nextLong();
            long d = in.nextLong();
            long dif = Math.abs(a - b);
            for (int i = 0; i <= n; i++) {
                // 右にi回,左にn-i回
                if (c * i - d * (n - i) <= dif && dif <= d * i - c * (n - i)) {
                    out.println("YES");
                    return;
                }
            }
            out.println("NO");
        }

    }
}

