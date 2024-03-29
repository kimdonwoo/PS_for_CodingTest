package DP;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class BOJ_14700 {
    static final int MOD = 1000000007;
    static int[][] dp;
    static int N, M;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        M = scanner.nextInt();

        // 무조건 N >= M 이 되도록 바꾼다
        if (N < M) {
            int temp = N;
            N = M;
            M = temp;
        }


        dp = new int[301][1 << 18];

        for (int i = 0; i < 301; i++) {
            Arrays.fill(dp[i],-1);
        }

        System.out.println(nemo(0, 0));
        scanner.close();
    }

    static int nemo(int x, int chk) {
        if (x == N * M) return 1;

        // 메모
        if (dp[x][chk] != -1) return dp[x][chk];

        dp[x][chk] = nemo(x + 1, chk >> 1);

        if (x % M == 0 || (chk & (1 << 0)) == 0 || (chk & (1 << 1)) == 0 || (chk & (1 << M)) == 0) {
            dp[x][chk] = (dp[x][chk] + nemo(x + 1, (chk >> 1) | (1 << M))) % MOD;
        }

        return dp[x][chk];
    }
}