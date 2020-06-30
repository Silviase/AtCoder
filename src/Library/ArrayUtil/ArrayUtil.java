package Library.ArrayUtil;

import java.io.PrintWriter;
import java.util.Arrays;

public class ArrayUtil {

    public static void fill(boolean[] array, boolean value) {
        Arrays.fill(array, value);
    }

    public static void fill(boolean[][] arrays, boolean value) {
        for (boolean[] array : arrays) Arrays.fill(array, value);
    }

    public static void fill(int[] array, int val) {
        Arrays.fill(array, val);
    }

    public static void fill(int[][] array, int val) {
        for (int[] a : array) Arrays.fill(a, val);
    }

    public static void fill(int[][][] array, int val) {
        for (int[][] ar : array) for (int[] a : ar) Arrays.fill(a, val);
    }

    public static void fill(long[] array, long val) {
        Arrays.fill(array, val);
    }

    public static void fill(long[][] array, long val) {
        for (long[] a : array) Arrays.fill(a, val);
    }

    public static void fill(long[][][] array, long val) {
        for (long[][] ar : array) for (long[] a : ar) Arrays.fill(a, val);
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + (i == array.length - 1 ? "\n" : " "));
    }

    public static void print(long[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + (i == array.length - 1 ? "\n" : " "));
    }

    public static void print(int[][] array) {
        for (int[] ints : array) print(ints);
    }

    public static void print(long[][] array) {
        for (long[] longs : array) print(longs);
    }

}
