## arrayList
- remove 
    遅い
    
## TreeMap
- 二分探索に便利
- lowerKey:より小さいkeyのうち最大のものをlog Nで見つける
- **重複を許さない**ので個数の管理をvalueで行うことによって疑似的に複数の重複する要素を扱うことができる


## SegmentTree
- 抽象化しているので遅い
- なのでEasySegmentTreeみたいにbinaryOperatorあたりをいい感じに書き直してやる必要がある
- EasySegmentTreeをそのまま書き換えてもいい

## UnionFind


## 正規表現
"[abc]":a or b or c

## 数学
xor: a+b+c >= a^b^c
等号が成立するときはbitの立っている位置がそれぞれすべて異なるとき

- nextPermutation (Library.Util.Operation)
次の順列を生成する(このときに,引数に用いた配列は変更される)
以下のように使用する.
```java
package main.java.tasks;
import Library.Util.Operation;
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
```

## 計算量
**10^8スケールは間に合う！**
これは間に合う
```java 
package main.java.tasks;
import Library.GridPoint;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.PrintWriter;
public class DAxisParallelRectangle {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n,k;
        ArrayList<GridPoint> al = new ArrayList<>();
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    for (int m = 0; m < n; m++) {
                        long xl,xr,yl,yh,cnt;
                        for (GridPoint gp: al){
                            if(xl <= gp.x && gp.x <= xr && yl <= gp.y && gp.y <= yh){
                                cnt++;
                            }
                        }
                        if(cnt == k){
                            res = Math.min(res, (xr-xl)*(yh-yl));
                        }
                    }
                }
            }
        }
    }
}
```