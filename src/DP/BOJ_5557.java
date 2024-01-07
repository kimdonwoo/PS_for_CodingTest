package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5557 {
    static int N;
    static long[][] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new long[N-1][21];

        st = new StringTokenizer(br.readLine());
        dp[0][Integer.parseInt(st.nextToken())] = 1;


        for(int i = 0 ; i < N-2 ; i++){
            int temp = Integer.parseInt(st.nextToken());

            for(int j = 0 ; j <= 20 ; j++){
                if(dp[i][j] != 0){
                    if(j + temp <= 20 ){
                        dp[i+1][j+temp] += dp[i][j];
                    }
                    if(j - temp >= 0){
                        dp[i+1][j-temp] += dp[i][j];
                    }
                }
            }
        }

        System.out.println(dp[N-2][Integer.parseInt(st.nextToken())]);
    }
}

/*
    즉,


 */



/*

    // 백트래킹으로 풀면 시간초과남 -> DP

    public static void backtracking(int count, int sum){
        // sum 짜르기
        if(sum < 0 || sum > 20) return;

        // 선택 완료
        if(count == N-1){
            if(sum == nums[N-1]) res++;
            return;
        }

        // 선택
        backtracking(count+1,sum+nums[count]);
        backtracking(count+1,sum-nums[count]);
    }



 */