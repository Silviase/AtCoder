package Library;

public class Operation {

    public boolean isOK(int index, int key) {
        if (true /* 何らかの条件 */) return true;
        else return false;
    }

    public int binarySearch(int key) {
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

    public int[] nextPermutation(int[] array) {
        int k = -1;
        int i = array.length - 2;
        while (i >= 0) {
            if (array[i] < array[i + 1]) {
                k = i;
                break;
            }
            i--;
        }

        // 存在しない場合nullを返す
        if (k == -1) return null;
        int l = k + 1;
        i = array.length - 1;
        while (i > k + 1) {
            if (array[k] < array[i]) {
                l = i;
                break;
            }
            i--;
        }

        int tmp = array[k];
        array[k] = array[l];
        array[l] = tmp;
        int[] res = new int[array.length];
        // k+1 <-> n-1
        for (int j = k + 1; j < array.length + k - j; j++) {
            tmp = array[j];
            array[j] = array[array.length + k - j];
            array[array.length + k - j] = tmp;
        }
        return array;
    }
}
