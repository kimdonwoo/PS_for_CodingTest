package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.locks.Condition;

public class BOJ_9084 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int[] moneys;
        long[] dp;

        for(int t = 0 ; t < T ;t++){
            int N = Integer.parseInt(br.readLine());
            moneys = new int[N];
            st = new StringTokenizer(br.readLine());

            for(int i = 0 ; i < N ; i++){
                moneys[i] = Integer.parseInt(st.nextToken());
            }

            int goal = Integer.parseInt(br.readLine());
            dp = new long[goal+1];

            dp[0] = 1;

            for(int i = 0 ; i < N ; i++){
                for(int j = 0; j <= goal - moneys[i] ; j++){
                    if(dp[j] != 0){
                        dp[j + moneys[i]] += dp[j] ;
                    }
                }
            }

            System.out.println(dp[goal]);

        }
    }
}

