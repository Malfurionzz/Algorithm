package linearTimeSort;

import java.util.Arrays;

public class countingSort {
    public static void countingSort(int[] a, int k) {
        int[] b = new int[a.length];
        int[] c = new int[k+1];
        for (int i = 0; i < 1+k; ++i) {
            c[i] = 0;
        }
        for (int j : a) {
            c[j]++;
        }
        for (int i = 1; i < k; ++i) {
            c[i] += c[i - 1];
        }
        for (int i = a.length-1;i >=0; --i) {
            b[c[a[i]]-1] = a[i];
            c[a[i]]--;
        }
        System.arraycopy(b, 0, a, 0, a.length);
    }
    public static void main(String[] args) {
        int[] a={3,3,5,6,2,1,5,6};
        System.out.println(Arrays.toString(a));
        countingSort(a,10);
        System.out.println(Arrays.toString(a));
    }
}


