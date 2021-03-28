package DynamicPromgraming;

public class LongestIncreasingSubsequence {
    public static void LIS(int [] a){
        int []b=new int[a.length];
        b[1]=a[0];
        int l=1;
        for (int i=1;i<a.length;++i){
            if (a[i]>b[l]){
                l++;
                b[l]=a[i];
            }else{
                insertOrderly(b,a[i],1,l);
            }
        }
        System.out.print(l);
    }
    private static void insertOrderly(int[]b,int x,int p,int q){
        b[binarySearch(b,x,1,q)]=x;
    }

    private static int binarySearch(int[] b,int x,int p,int q){
        if (p==q){
            return p;
        }
        int mid=(p+q)/2;
        if (x<=b[mid])
           return binarySearch(b,x,p,mid);
        else
           return binarySearch(b,x,mid+1,q);
    }

    public static void main(String[] args) {
        int []a =new int[]{4,2,4,5,3,7};
        LIS(a);
    }
}
