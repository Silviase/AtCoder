package main.java.tasks;

import Library.Operation;

import java.util.Arrays;

public class LibCheck {
    public static void main(String[] args) {
        Operation op = new Operation();
        int[] array = {1, 2, 3, 4};
        int i = 0;
        for (int j = 0; j < 24; j++) {
            System.out.println(Arrays.toString(op.nextPermutation(array)));
        }
    }
}
