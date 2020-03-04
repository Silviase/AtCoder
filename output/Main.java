import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.AbstractCollection;
import java.util.Scanner;
import java.util.ArrayList;

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
        DFaceProducesUnhappiness solver = new DFaceProducesUnhappiness();
        solver.solve(1, in, out);
        out.close();
    }

    static class DFaceProducesUnhappiness {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            String s = in.next();
            ArrayList<Integer> al = new ArrayList<>();
            int cnt = 1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == s.charAt(i - 1)) {
                    cnt++;
                } else {
                    al.add(cnt);
                    cnt = 1;
                }
            }
            al.add(cnt);

            out.println(al.toString());

            if (al.size() <= 2 * k + 1) {
                out.println(n - 1);
            } else {
                int sum = 0;
                for (int i = 0; i < 2 * k + 1; i++) {
                    sum += al.get(i);
                }
                int max = sum;
                out.println(sum);
                for (int i = 1; i + 2 * k < al.size(); i++) {
                    sum = sum + al.get(i + 2 * k) - al.get(i);
                    out.println(sum);
                    max = Math.max(max, sum);
                }
                out.println(max - 1);
            }
        }

    }
}

