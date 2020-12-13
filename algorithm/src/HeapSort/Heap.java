package HeapSort;

import java.util.Arrays;

/**
 * @auther zsy
 * @date 2020/12/12-15:29
 */
public class Heap {
    public static void main(String[] args)
    {
        int[] a=new int[10];
        for (int i=0;i<10;++i)
        {
            a[i]=10-i;
        }
        a[0]=-1;
        System.out.println(Arrays.toString(a));
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }

    public  static void heapSort(int[] a) {
        buildMaxHeap(a,a.length);
        for (int i=1;i<a.length;++i){
            int temp=a[0];
            a[0]=a[a.length-i];
            a[a.length-i]=temp;
            maxHeapify(a,0,a.length-i);
        }

    }


    public  static void buildMaxHeap(int[] a,int heapsize) {
        for (int i=(int)Math.ceil(a.length/2);i>=0;--i)
        {
            maxHeapify(a,i,heapsize);
        }
    }

    public static void maxHeapify(int [] a,int i,int heapsize){
        int l=LEFT(i);
        int r=RIGHT(i);
        int max=i;
        if(l<heapsize && a[l]>a[i]){
            max=l;
        }
        if(r<heapsize && a[r]>a[max]){
            max=r;
        }
        if(i!=max)
        {
            int temp=a[i];
            a[i]=a[max];
            a[max]=temp;
            maxHeapify(a,max,heapsize);
        }
    }


    public static int LEFT(int i){
        return 2*(i+1)-1;
    }

    public static int RIGHT(int i){
        return 2*(i+1);
    }
}
