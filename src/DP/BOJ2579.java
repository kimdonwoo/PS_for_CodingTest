package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    DFS -> 시간초과
    DP로 해보자

    흠 전체합에서 뭘 뺄건지 찾기?
 */


public class BOJ2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] stairs = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        // dp[i]는 i번째 계단에서 최댓값
        int[] dp = new int[n + 1];
        dp[1] = stairs[1];

        for (int i = 2; i <= n; i++) {
            if(i==2){
                dp[2] = stairs[1] + stairs[2];
            }else if(i==3){
                dp[3] = Math.max(stairs[1], stairs[2]) + stairs[3];
            }else{
                dp[i] = Math.max(dp[i-3] + stairs[i-1], dp[i-2]) + stairs[i];
            }
        }

        System.out.println(dp[n]);
    }
}

// DFS -> 시간 초과 남
//public class BOJ2579 {
//    static int stair[];
//    static int T;
//    static int res = Integer.MIN_VALUE;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        T = Integer.parseInt(br.readLine());
//        stair = new int[T+1];
//
//        // 계단 만들기
//        for(int i = 0 ; i < T ; i++){
//            stair[i+1] = Integer.parseInt(br.readLine());
//        }
//
//        DFS(1,0,stair[1]);
//
//        System.out.println(res);
//
//    }
//
//    static void DFS(int now, int onecount, int sum){
//        if(onecount >= 3)
//            return;
//
//        if(now == T){
//            res = Math.max(res,sum);
//            return;
//        }
//
//        // 1계단
//        DFS(now+1, onecount+1 , sum+stair[now]);
//        // 2계단
//        if(now+2 <= T) {
//            DFS(now + 2, 0, sum + stair[now]);
//        }
//
//    }
//}
