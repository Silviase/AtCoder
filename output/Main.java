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
        EFriendships solver = new EFriendships();
        solver.solve(1, in, out);
        out.close();
    }

    static class EFriendships {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int max = (n - 1) * (n - 2) / 2;

            if (k > max) {
                out.println(-1);
            } else {
                out.println(n - 1/*ウニの分*/ + (max - k));
                // 1を核としたウニをつくる
                for (int i = 2; i <= n; i++) {
                    out.println(1 + " " + i);
                }
                for (int i = 2; i <= n; i++) {
                    for (int j = i + 1; j <= n; j++) {
                        if (k == max) return;
                        max--;
                        out.println(i + " " + j);
                    }
                }
            }
        }

    }
}

