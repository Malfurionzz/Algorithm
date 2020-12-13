package quickSort;

import java.util.Arrays;
import java.util.Random;

/**
 * @auther zsy
 * @date 2020/12/12-20:16
 */
public class quickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 5, 2, 5};
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void tailRecursiveQuickSort(int[] a){
        innerTailRecursiveQuickSort(a,0,a.length-1);
    }

    public static void innerTailRecursiveQuickSort(int[] a,int p,int q){
        int r=partition(a,p,q);
        innerTailRecursiveQuickSort(a,p,r-1);
        p=r+1;
    }

    public static void quickSort(int[] a) {
        innerQuickSort(a, 0, a.length - 1);
    }

    public static void innerQuickSort(int[] a, int p, int q) {
        if (p < q) {
            //int r = partition(a, p, q);
            int r = RandomPartition(a, p, q);
            innerQuickSort(a, p, r - 1);
            innerQuickSort(a, r + 1, q);
        }
    }
    public static int RandomPartition(int[] a, int p, int q) {
        Random rand=new Random();
        int index=rand.nextInt(q-p+1)+p;
        int temp= a[p]; a[p]=a[index]; a[index]=temp;
        return partition(a,p,q);
    }
    public static int partition(int[] a, int p, int q) {
        int pivot = a[p];
        while (p < q) {
            if (p < q && a[q] >= pivot) {
                --q;
            }
            a[p] = a[q];
            if (p < q && a[p] <= pivot) {
                ++p;
            }
            a[q] = a[p];
        }
        a[p] = pivot;
        return p;
    }

}
