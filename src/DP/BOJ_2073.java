package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[] dp = new int[D+1];
        dp[0]=Integer.MAX_VALUE;

        for(int i = 0 ; i < P ; i++){
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            for(int j = D ; j>0 ; j--){
                if(j>=L){
                    if(dp[j-L] > 0){
                        int temp = Math.min(dp[j-L],C);
                        if(dp[j] > 0) temp = Math.max(temp,dp[j]);
                        dp[j] = temp;
                    }
                }

            }
        }

        System.out.println(dp[D]);

    }
}
