package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2056 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] dp = new int[N+1];
        int res = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        dp[1] = Integer.parseInt(st.nextToken());

        for(int i = 2 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int endTime = 0;
            for(int j = 0 ; j < M ; j++){
                endTime = Math.max(endTime,dp[Integer.parseInt(st.nextToken())]);
            }
            dp[i] = endTime+time;
            res = Math.max(res,dp[i]);
        }

        System.out.println(res);

    }
}
