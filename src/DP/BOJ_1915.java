package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N][M];
        int res = Integer.MIN_VALUE;

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = str.charAt(j)-'0';
                    res = Math.max(res,dp[i][j]);
                    continue;
                }
                if(str.charAt(j)-'0' == 1){
                    if(dp[i-1][j-1] != 0 && dp[i][j-1] != 0 && dp[i-1][j] != 0 ){
                        int temp = dp[i-1][j-1];
                        temp = Math.min(temp,dp[i-1][j]);
                        temp = Math.min(temp,dp[i][j-1]);

                        dp[i][j] = (int) Math.pow((int)(Math.sqrt(temp))+1,2);
                        res = Math.max(res,dp[i][j]);
                    }else{
                        res = Math.max(res,dp[i][j]);
                        dp[i][j] = 1;
                    }
                }
            }
        }
        System.out.println(res);
    }
}
