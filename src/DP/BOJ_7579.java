package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7579 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int ans = Integer.MAX_VALUE;

        int[] memoryArr = new int[n+1];
        int[] costArr = new int[n+1];

        // dp[i][j] = val
        // i : 몇번째인지
        // j : 비용
        // val : 확보한 메모리 값
        int[][] dp = new int[n+1][100001];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        // 비용과 메모리 초기화부분
        for(int i = 1 ; i <= n; i++){
            memoryArr[i] = Integer.parseInt(st1.nextToken());
            costArr[i] = Integer.parseInt(st2.nextToken());
        }


        // dp[i][j] = max(dp[i - 1][j - cost] + memory, dp[i - 1][j])
        for(int i = 1 ; i < n+1; i++){
            // 현재 cost와 memory
            int cost = costArr[i];
            int memory = memoryArr[i];

            for(int j = 0; j <= 10000; j++){

                if (j >= cost) dp[i][j] = Math.max(dp[i - 1][j - cost] + memory, dp[i - 1][j]);
                else dp[i][j] = dp[i - 1][j];

                // 문제에서 주어진 필요한 메모리보다 확보가능한 메모리가 클 경우 정답으로 저장
                if(dp[i][j] >= m) ans = Math.min(ans, j);
            }
        }
        System.out.println(ans);
    }
}

