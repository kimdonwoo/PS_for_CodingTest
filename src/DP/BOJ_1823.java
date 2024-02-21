package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1823 {
    static int[] v;
    static int[][] DP;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        v = new int[N+2];
        DP = new int[N+2][N+2];
        Arrays.fill(DP[0],-1);
        Arrays.fill(DP[N+1],-1);

        for(int i = 1 ; i <= N ; i++){
            v[i] = Integer.parseInt(br.readLine());
            Arrays.fill(DP[i],-1);
        }

        int result = Integer.MIN_VALUE;

        for(int i = 1 ; i <= N ; i++){
            result = Math.max(result,execute(i,i)+N*v[i]);
        }

        System.out.println(result);

    }

    private static int execute(int s, int e) {

        // 메모리제이션
        if(DP[s][e] != -1){
            return DP[s][e];
        }

        // 초기화
        if(e-s == N-1) return 0;

        // main
        int k = N-(e-s)-1;

        int temp = -1;
        if(e <= N){
            temp = Math.max(temp,execute(s,e+1)+k*v[e+1]);
        }
        if(s >= 1){
            temp = Math.max(temp,execute(s-1,e)+k*v[s-1]);
        }

        return DP[s][e] = temp;

    }
}
