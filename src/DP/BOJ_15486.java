package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15486 {
    static int N;
    static int[] time;
    static int[] pay;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        time = new int[N];
        pay = new int[N];
        dp = new int[N+1];
        Arrays.fill(dp,0);

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }

        int nowMax = 0;

        for(int i = 0 ; i < N+1 ; i++) {
            nowMax = Math.max(nowMax,dp[i]);
            if(i == N) break;
            if(i + time[i] < N+1){
                if(dp[i + time[i]] < nowMax + pay[i]){
                    dp[i + time[i]] = nowMax+pay[i];
                }
            }
        }
        System.out.println(nowMax);

    }
}

/*
    이 방식으로 하면 O(N^2)라서 시간초과 남 흠...

    현재 방식은 i 일일때 미래의 날에 값을 넣고있음
        -> i 일일때 과거의 날 + dp값을 사용해서 i일을 예측해보자

        dp[0] = 0;
        dp[1] = 0;


//        for(int i = 0 ; i < N ; i++) {
//
//            if(i + time[i] < N+1){
//                // j를 없애는 방법
//                for(int j = i + time[i] ; j < N+1 ; j++){
//                    if(dp[j] < dp[i] + pay[i]){
//                        dp[j] = dp[i]+pay[i];
//                    }
//                }
//            }
//        }



 */