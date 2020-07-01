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
        CAtCoDeer solver = new CAtCoDeer();
        solver.solve(1, in, out);
        out.close();
    }

    static class CAtCoDeer {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            long x = in.nextInt();
            long y = in.nextInt();
            for (int i = 0; i < n - 1; i++) {
                long t = in.nextInt();
                long a = in.nextInt();
                long k = Math.max(MathUtil.ceil(x, t), MathUtil.ceil(y, a));
                x = t * k;
                y = a * k;
            }
            out.println(x + y);
        }

    }

    static class MathUtil {
        public static long ceil(long l1, long l2) {
            return (l1 - 1 + l2) / l2;
        }

    }
}

