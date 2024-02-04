package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20542 {
    static int[][] dp;
    static int n;
    static int m;
    static String nStr;
    static String mStr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nStr = br.readLine();
        mStr = br.readLine();

        dp = new int[n + 1][m + 1];

        for (int i=1;i<=n;i++) dp[i][0] = i;
        for (int i=1;i<=m;i++) dp[0][i] = i;

        for (int i=1;i <= n; i++) {
            for (int j=1;j <= m;j++) {

                char ch = nStr.charAt(i - 1);
                char ch1 = mStr.charAt(j - 1);

                boolean check = false;
                if ((ch == 'i') && ( ch1 == 'j' || ch1 == 'l')) check = true;
                if ((ch == 'v') && ( ch1 == 'w' )) check = true;

                if (ch == ch1 || check == true) dp[i][j] = dp[i - 1][j - 1];
                else{
                    int min = Math.min(dp[i - 1][j - 1],dp[i - 1][j]);
                    min = Math.min(min,dp[i][j-1]);
                    dp[i][j] = min + 1;
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}