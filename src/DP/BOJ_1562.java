package DP;

import java.io.*;

public class BOJ_1562 {

    static int N;
    static long[][][] dp;
    static final int MOD = 1000000000;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        // dp[n][k][visit]
        // 현재 n번째 자리에 k가 있고, 비트마스킹 visit으로 현재까지 들린 위치 표시
        // dp[n][k][visit | (1<<k)] = dp[n-1][k-1][visit] + dp[n-1][k+1][visit]

        dp = new long[N + 1][10][1 << 10];

        for (int i = 1; i <= 9; i++)
            dp[1][i][1 << i] = 1;

        for (int n = 2; n <= N; n++) {
            for (int k = 0; k <= 9; k++) {
                // 현재 n 자리에 올 k를 고르는 상황
                for (int visit = 0; visit < (1 << 10); visit++) {
                    // n 자리에 k를 둘때 visit
                    int newVisit = visit | (1 << k);

                    if (k == 0)
                        dp[n][k][newVisit] += dp[n - 1][k + 1][visit] % MOD;
                    else if (k == 9)
                        dp[n][k][newVisit] += dp[n - 1][k - 1][visit] % MOD;
                    else
                        dp[n][k][newVisit] += dp[n - 1][k + 1][visit] % MOD + dp[n - 1][k - 1][visit] % MOD;

                    dp[n][k][newVisit] %= MOD;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i][(1 << 10) - 1] % MOD;
            sum %= MOD;
        }

        bw.write(sum + "\n");
        bw.flush();

    }

}