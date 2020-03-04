package Library;

public class Operation {

    public boolean isOK(int index, int key) {
        if (true /* 何らかの条件 */) return true;
        else return false;
    }

    int binarySearch(int key) {
        // 条件を満たすindexのうち最小のindexをreturnする
        // <--false->. こっち-> .<--true-->
        // 必ず右側が条件を満たすようにする必要があることに注意しなければならない
        int ng = -1;
        int ok = (int) 1e9; /*配列のsize*/

        while (Math.abs(ok - ng) > 1) {
            int mid = (ok + ng) / 2;
            if (isOK(mid, key)) {
                ok = mid;
            } else {
                ng = mid;
            }
        }
        return ok;

    }

}
