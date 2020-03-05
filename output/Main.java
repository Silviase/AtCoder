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
        add solver = new add();
        solver.solve(1, in, out);
        out.close();
    }

    static class add {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int a = in.nextInt();
            int b = in.nextInt();
            int res = 0;
            if (Arith.gcd(a, b) != 1) {
                if (a == 1 || b == 1) {
                    out.println(0);
                    return;
                } else {
                    out.println(-1);
                    return;
                }
            }

            for (int i = 1; i < a * b; i++) {
                if (!canMake(a, b, i)) {
                    res++;
                }
            }
            out.println(res);

        }

        private boolean canMake(int a, int b, int key) {
            for (int i = 0; i <= b; i++) {
                for (int j = 0; j <= a; j++) {
                    if (a * i + b * j == key) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    static class Arith {
        public static long gcd(long a, long b) {
            return a % b == 0 ? b : gcd(b, a % b);
        }

    }
}

