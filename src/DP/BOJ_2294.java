package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2294 {
    static int N,K;
    static int[] moneys;
    static int[] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        moneys = new int[N];
        dp = new int[K+1];
        Arrays.fill(dp,Integer.MAX_VALUE);

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            moneys[i] = Integer.parseInt(st.nextToken());
            if(moneys[i] <= K) dp[moneys[i]] = 1;
        }

        // dp[i] 의미 : 현재가치가 i일때, 최소로 사용한 동전 갯수
        for(int i = 0 ; i <= K ; i++){
            for(int j = 0 ; j < N ; j++){
                if(i >= moneys[j] && dp[i-moneys[j]] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i],dp[i-moneys[j]]+1);
                }
            }
        }

        if(dp[K] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(dp[K]);


        /*
            n가지 종류의 동전이 있다
            가치의 합이 k원이 되고 싶다

            동전의 갯수는 최소

            n : 100 / k : 10_000

            현재가치가 k일때, 최소로 사용한 동전 갯수

            dp[k] = (최소 동전 갯수) <- 모두 최댓값 넣기

            모든 동전의 갯수 (100개 돌면서) -> j
            dp[i] = Math.min(dp[i],dp[i-j]+1)

            dp[1] = 1
            dp[5] = 1
            dp[12] = 1






         */

    }
}
