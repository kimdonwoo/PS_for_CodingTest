package DP;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2748 {
    static long[] dp;
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        dp = new long[N+1];
        Arrays.fill(dp,-1);

        dp[0] = 0;
        dp[1] = 1;

        System.out.println(Fib(N));

//        if(N <= 1){
//            System.out.println(N);
//        }else{
//
//            dp[0] = 0;
//            dp[1] = 1;
//
//            for(int i = 2 ; i <= N ; i++){
//                dp[i] = dp[i-1] + dp[i-2];
//            }
//
//            System.out.println(dp[N]);
//        }
    }

    static long Fib(int N){
        if(dp[N] < 0) dp[N] = Fib(N-1) + Fib(N-2);

        return dp[N];
    }
}

