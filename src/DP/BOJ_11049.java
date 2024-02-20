package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11049 {
    static int[][] ops;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ops = new int[N+1][2];
        dp = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            ops[i][0] = Integer.parseInt(st.nextToken());
            ops[i][1] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i],-1);
        }

        System.out.println(execute(1,N));
    }

    private static int execute(int s, int e) {
        int result = Integer.MAX_VALUE;

        // 이미 계산된 애는 계산하지말자
        // -> 메모리제이션
        if(dp[s][e] != -1) return dp[s][e];

        // 최소 초기화 과정
        // -> 행렬 1개 or 행렬 2개
        if(s == e) return 0;
        if(s+1 == e) return ops[s][0] * ops[s][1] * ops[e][1];

        // main
        for(int i = s; i < e; i++){
            int a = ops[s][0] * ops[i][1] * ops[e][1];
            // 이게 dp[s][e] = dp[s][i]+dp[i][e]+a
            result = Math.min(result,execute(s,i)+execute(i+1,e)+a);
        }

        return dp[s][e] = result;
    }
}

