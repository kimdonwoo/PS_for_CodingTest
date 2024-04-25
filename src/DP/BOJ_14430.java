package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14430 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[N][M];
        int[][] dp = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                if(st.nextToken().equals("1")) {
                    map[i][j] = true;
                }
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j]) dp[i][j]++;
                if(i == 0 && j == 0){
                    continue;
                }else if(i == 0){
                    dp[i][j] += dp[i][j-1];
                }else if(j == 0){
                    dp[i][j] += dp[i-1][j];
                }else{
                    dp[i][j] += Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[N-1][M-1]);

    }
}
