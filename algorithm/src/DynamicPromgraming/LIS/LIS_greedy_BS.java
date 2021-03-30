package DynamicPromgraming.LIS;

/**
 * 辅助数组b[i]，b[i]代表递增长度为i的递增子序列的末尾元素的最小值，由于b[i]必然是是递增的，可以用二分查找进行优化。O(nlg n)
 * 每一个数都会在dp数列中出现过，我们将a[i]位置信息记录在了pos[i]中，然后逆序遍历a,如果当前的l==pos[i],a[i]说明LIS中，l--，寻找下一个LIS中的元素。
 * 但这是贪心+二分，不是DP
 */
public class LIS_greedy_BS {
    public static void LIS(int [] a){
        int []b=new int[a.length+1];
        int []pos=new int[a.length+1];
        int []answer=new int[a.length+1];
        b[1]=a[0];
        pos[0]=1;
        int l=1;
        for (int i=1;i<a.length;++i){
            if (a[i]>b[l]){
                l++;
                b[l]=a[i];
                pos[i]=l;
            }else{
                int index=binarySearch(b,a[i],1,l);
                b[index]=a[i];
                pos[i]=index;
            }
        }
        System.out.print(l);
        int index=l-1;
        int max=Integer.MAX_VALUE;
        for (int i=a.length-1;i>=0;--i){
            if (l==0)
                break;
            if (pos[i]==l) {
                answer[l]=i;
                l--;
            }
        }
        System.out.print("1");
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
