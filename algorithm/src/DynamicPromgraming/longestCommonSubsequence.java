package DynamicPromgraming;

public class longestCommonSubsequence {
    public static void LCS(int []p,int []q){
        int[][] m=new int[p.length+1][q.length+1];
        int[][] s=new int[p.length+1][q.length+1];
        for (int i=0;i<=p.length;++i){
            for (int j=0;j<=q.length;++j){
                m[i][j]=-1;
            }
        }
        innerLCS(p,q,m,s);
        System.out.println(m[p.length][q.length]);
        print(p,q,s,p.length,q.length);
    }
    private static void innerLCS(int []p,int []q,int[][]m,int[][]s){
        for (int i=0;i<=p.length;++i)
            m[i][0]=0;
        for (int i=0;i<=q.length;++i)
            m[0][i]=0;
        for (int i=1;i<=p.length;++i){
            for (int j=1;j<=q.length;++j){
                if (p[i-1]==q[j-1]){
                    m[i][j]=m[i-1][j-1]+1;
                    s[i][j]=2;
                } else if (m[i-1][j]<m[i][j-1]){
                    m[i][j]=m[i][j-1];
                    s[i][j]=1;
                }else {
                    m[i][j]=m[i-1][j];
                    s[i][j]=3;
                }
            }
        }
    }
    private static void print(int[]p,int[]q,int[][]s,int i,int j){
        if (i==0 || j==0)
            return;
        if (s[i][j]==2){
            print(p,q,s,i-1,j-1);
            System.out.print(p[i-1]);
        } else if (s[i][j]==1)
            print(p,q,s,i,j-1);
        else
            print(p,q,s,i-1,j);
    }

    public static void main(String[] args) {
        int[] p=new int[]{1,9,3,4,5,8};
        int[] q=new int[]{1,9,8};
        LCS(p,q);
    }
}
