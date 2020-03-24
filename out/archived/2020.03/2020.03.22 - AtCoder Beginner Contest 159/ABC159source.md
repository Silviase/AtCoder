# A - The Number of Even Pairs
```java
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
        ATheNumberOfEvenPairs solver = new ATheNumberOfEvenPairs();
        solver.solve(1, in, out);
        out.close();
    }

    static class ATheNumberOfEvenPairs {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            out.println(n * (n - 1) / 2 + m * (m - 1) / 2);
        }

    }
}
```

# B - String Palindrome

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        BStringPalindrome solver = new BStringPalindrome();
        solver.solve(1, in, out);
        out.close();
    }

    static class BStringPalindrome {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            String s = in.next();
            String t = s.substring(0, s.length() / 2);
            String u = s.substring(s.length() / 2 + 1);

            if (isKaibun(s) && isKaibun(t) && isKaibun(u)) {
                out.println("Yes");
            } else {
                out.println("No");
            }
        }

        boolean isKaibun(String s) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                    return false;
                }
            }
            return true;
        }

    }
}
```

# C - Maximum Volume
```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        CMaximumVolume solver = new CMaximumVolume();
        solver.solve(1, in, out);
        out.close();
    }

    static class CMaximumVolume {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            double l = Math.pow(in.nextInt(), 3);
            out.println(l / 27);
        }
    }
}
```

# D - Banned K

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        DBannedK solver = new DBannedK();
        solver.solve(1, in, out);
        out.close();
    }

    static class DBannedK {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            HashMap<Integer, Integer> hm = new HashMap<>();
            int n = in.nextInt();
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                hm.merge(a[i], 1, Integer::sum);
            }

            ArrayList<Integer> al = new ArrayList<>(hm.values());
            long sum = 0;
            for (int i = 0; i < al.size(); i++) {
                sum += (long)al.get(i) * (long)(al.get(i) - 1) / 2;
            }

            for (int i = 0; i < n; i++) {
                out.println(sum - hm.get(a[i]) + 1);
            }

        }

    }
}

```

# E - Dividing Chocolate
```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
        EDividingChocolate solver = new EDividingChocolate();
        solver.solve(1, in, out);
        out.close();
    }

    static class EDividingChocolate {
        public void solve(int testNumber, Scanner in, PrintWriter out) {

            int h = in.nextInt();
            int w = in.nextInt();
            int k = in.nextInt();
            int res = Integer.MAX_VALUE;
            char[][] c = new char[h][w];
            int[][] acc = new int[h + 1][w + 1];

            for (int i = 0; i < h; i++) {
                c[i] = in.next().toCharArray();
            }

            for (int j = 0; j <= w; j++) {
                for (int i = 0; i <= h; i++) {
                    if (i > 0 && j > 0) {
                        if (c[i - 1][j - 1] == '1') {
                            acc[i][j] = acc[i - 1][j] + acc[i][j - 1] - acc[i - 1][j - 1] + 1;
                        } else {
                            acc[i][j] = acc[i - 1][j] + acc[i][j - 1] - acc[i - 1][j - 1];
                        }
                    } else {
                        acc[i][j] = 0;
                    }
                }
            }

            for (int i = 0; i < Math.pow(2, h - 1); i++) {
                int cmp = func(acc, k, i);
                res = Math.min(res, cmp);
            }

            out.println(res);

        }

        int func(int[][] acc, int k, int x) {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(0);
            int cnt = 1;
            while (x > 0) {
                if (x % 2 == 1) {
                    al.add(cnt);
                }
                cnt++;
                x /= 2;
            }
            al.add(acc.length - 1);

            int res = al.size() - 2;
            int last = 0;

            for (int i = 0; i < acc[0].length; i++) {
                for (int j = 1; j < al.size(); j++) {
                    if ((acc[al.get(j)][i] - acc[al.get(j)][last])
                            - (acc[al.get(j - 1)][i] - acc[al.get(j - 1)][last]) > k) {
                        if (last == i - 1) {
                            return 114514;
                        }
                        res++;
                        last = i - 1;
                    }
                }
            }
            return res;
        }

    }
}

```