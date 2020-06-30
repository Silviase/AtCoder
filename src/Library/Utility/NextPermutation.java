package Library.Utility;

public class NextPermutation<T extends Comparable<T>> {

    public T[] nextPermutation(T[] array) {
        int k = -1;
        int i = array.length - 2;
        while (i >= 0) {
            if (array[i].compareTo(array[i + 1]) < 0) {
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
            if (array[k].compareTo(array[i]) < 0) {
                l = i;
                break;
            }
            i--;
        }

        T tmp = array[k];
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
