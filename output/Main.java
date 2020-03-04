import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;
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
        FSilverFoxVsMonster solver = new FSilverFoxVsMonster();
        solver.solve(1, in, out);
        out.close();
    }

    static class FSilverFoxVsMonster {
        public void solve(int testNumber, Scanner in, PrintWriter out) {

            int n = in.nextInt();
            long range = in.nextInt();
            long atk = in.nextInt();
            long[] point = new long[n];
            long[] health = new long[n];
            ArrayList<Pair> ph = new ArrayList<>();
            long ans = 0;
            for (int i = 0; i < n; i++) {
                ph.add(new Pair(in.nextInt(), in.nextInt()));
            }
            ph.sort(Comparator.comparing(i -> i.x));

            // out.println(ph.toString());


            long[] accAtk = new long[n + 1];
            Arrays.fill(accAtk, 0);
            // 残っているものについて、左から順に右に2Dの範囲で攻撃する.
            int nowToAtk = 0;
            while (nowToAtk != n) {
                accAtk[nowToAtk] = nowToAtk == 0 ? 0 : accAtk[nowToAtk - 1] + accAtk[nowToAtk];
                if (accAtk[nowToAtk] >= ph.get(nowToAtk).y) {
                    // すでに倒れている
                    nowToAtk++;
                    continue;
                }
                long key = ph.get(nowToAtk).x + 2 * range;
                long time = (ph.get(nowToAtk).y - accAtk[nowToAtk] - 1 + atk) / atk;
                int ng = -1;
                int ok = point.length;
                while (Math.abs(ok - ng) > 1) {
                    int mid = (ok + ng) / 2;
                    if (ph.get(mid).x > key) {
                        ok = mid;
                    } else {
                        ng = mid;
                    }
                }
                // out.println("ok:" + ok);
                accAtk[nowToAtk] += atk * time;
                accAtk[ok] -= atk * time;
                ans += time;
                nowToAtk++;
                // System.out.println(Arrays.toString(accAtk));

            }
            out.println(ans);

        }

    }

    static class Pair {
        public long x;
        public long y;

        public Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

    }
}

