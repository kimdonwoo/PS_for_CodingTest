package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2624 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] dp = new int[T+1];
        dp[0] = 1;

        for(int i = 0 ; i < k ; i++){
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            for(int j = T ; j >= 0 ; j--){
                if(dp[j] > 0){
                    for(int l = 1 ; l <= count ; l++){
                        if(j+price*l > T) break;
                        dp[j+price*l]+=dp[j];
                    }
                }
            }
        }

        System.out.println(dp[T]);

    }
}

/*
    목표 20




 */