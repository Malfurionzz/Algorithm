package DynamicPromgraming;
/**
 * 矩阵链乘法，m[][]记录子问题的代价，s[][]子问题的最优解。
 * 思路：以矩阵链的长度为出发点求解子问题。
 */

public class matrixChainMultipication {
    public static void matrixChainOrder(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];
        innerMatrixChainOrder(p, n, m, s);
        printResult(s, 1, n);
    }

    private static void innerMatrixChainOrder(int[] p, int n, int[][] m, int[][] s) {
        for (int i = 1; i <= n; ++i)
            m[i][i] = 0;
        for (int l = 2; l <= n; ++l) {
            for (int i=1;i<=n+1-l;++i){
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    private static void printResult(int[][] s, int i, int j) {
        if (i == j)
            System.out.print("A"+i);
        else {
            System.out.print("(");
            printResult(s, i, s[i][j]);
            printResult(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        int[] p = new int[]{30, 35, 15, 5, 10, 20, 25};
        matrixChainOrder(p);
    }
}
