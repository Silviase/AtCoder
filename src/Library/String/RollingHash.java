package Library.String;

import java.util.Random;

public class RollingHash {

    private static final int MAX_LENGTH = 500000;
    private final static long MASK30 = (1L << 30) - 1;
    private final static long MASK31 = (1L << 31) - 1;
    private final static long MOD = (1L << 61) - 1;
    private final static long POSITIVER = MOD * 3;
    private static int Base;
    private static long[] pow = new long[MAX_LENGTH + 1];
    public long[] hash;

    static {
        Base = new Random().nextInt((int)1e9)+129;
        pow[0] = 1;
        for (int i = 1; i < pow.length; i++) {
            pow[i] = calc(mul(pow[i - 1], Base));
        }
    }

    public RollingHash(String s) {
        hash = new long[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            hash[i + 1] = calc(mul(hash[i], Base) + s.charAt(i));
        }
    }

    public long slice(int begin, int length) {
        return calc(hash[begin + length] + POSITIVER - mul(hash[begin], pow[length]));
    }

    private static long mul(long l, long r) {
        long lu = l >> 31;
        long ld = l & MASK31;
        long rd = r & MASK31;
        long ru = r >> 31;
        long mid = ld * ru + lu * rd;
        return ((lu * ru) << 1) + ld * rd + ((mid & MASK30) << 31) + (mid >> 30);
    }

    private static long calc(long v) {
        v = (v & MOD) + (v >> 61);
        if (v >= MOD) v -= MOD;
        return v;
    }


}
