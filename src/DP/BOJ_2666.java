package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class BOJ_2666 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] start = new int[2];
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());

        if(start[0] > start[1]){
            int temp = start[0];
            start[0] = start[1];
            start[1] = temp;
        }
        int M = Integer.parseInt(br.readLine());

        int[][][] dp = new int[M+1][N+1][N+1];
        for(int i = 0 ; i < M+1 ; i++){
            for(int j = 0 ; j < N+1 ; j++){
                Arrays.fill(dp[i][j],Integer.MAX_VALUE);
            }
        }
        dp[0][start[0]][start[1]] = 0;

        for(int i = 0 ; i < M ; i++){
            int now = Integer.parseInt(br.readLine());
            // i가 순서라고 보면됨
            for(int j = 1 ; j < N+1 ; j++){
                for(int k = j+1 ; k < N+1 ; k++){
                    if(dp[i][j][k] != Integer.MAX_VALUE){

                        // now랑 j
                        if(now < j){
                            // j,k -> now,j
                            dp[i+1][now][j] = Math.min(dp[i+1][now][j],dp[i][j][k]+(j-now)+(k-j));
                        }else if(now > j){
                            // j,k -> j,now
                            dp[i+1][j][now] = Math.min(dp[i+1][j][now],dp[i][j][k]+Math.abs(now-k));
                        }else{
                            // 만약 now == j이면 ?
                            // j,k -> j,k;
                            dp[i+1][j][k] = Math.min(dp[i+1][j][k],dp[i][j][k]);
                        }

                        // now랑 k
                        if(now > k){
                            // j k now
                            // j,k -> k,now
                            dp[i+1][now][j] = Math.min(dp[i+1][now][j],dp[i][j][k]+(now-k)+(k-j));
                        }else if(now < k){
                            // j,k -> now,k
                            dp[i+1][now][k] = Math.min(dp[i+1][now][k],dp[i][j][k]+Math.abs(now-j));
                        }else{
                            // 만약 now == k이면 ?
                            // j,k -> j,k;
                            dp[i+1][j][k] = Math.min(dp[i+1][j][k],dp[i][j][k]);
                        }

                    }
                }
            }
        }

        // 그리고 dp[M][i~][j~] 에서 최댓값 구하기

        int res = Integer.MAX_VALUE;

        for(int i = 0 ; i < N+1 ; i++){
            for(int j = 0 ; j < N+1 ; j++){
                res = Math.min(res,dp[M][i][j]);
            }
        }

        System.out.println(res);

    }
}
