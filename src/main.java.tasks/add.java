package main.java.tasks;

import Library.Arith;

import java.util.Scanner;
import java.io.PrintWriter;

public class add {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int res = 0;
        if (Arith.gcd(a, b) != 1) {
            if(a == 1 || b == 1){
                out.println(0);
                return;
            }else{
                out.println(-1);
                return;
            }
        }

        for (int i = 1; i < a*b; i++) {
            if(!canMake(a,b,i)){
                res++;
            }
        }
        out.println(res);

    }

    private boolean canMake(int a, int b, int key){
        for (int i = 0; i <= b; i++) {
            for (int j = 0; j <= a; j++) {
                if(a * i + b * j == key){
                    return true;
                }
            }
        }
        return false;
    }

}
