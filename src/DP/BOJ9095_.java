package DP;

import java.util.Scanner;

public class BOJ9095_ {

    static int[] dp;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        dp = new int[11];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i = 0 ; i < T ; i++){
            int num = sc.nextInt();
            System.out.println(recur_dp(num));
        }
    }

    // 여기 안에
    static int recur_dp(int n){
        if(dp[n] > 0) return dp[n];
        return recur_dp(n-1)+ recur_dp(n-2) + recur_dp(n-3);

    }
}


/*

public class BOJ9095 {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] dp = new int[11];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i = 4 ; i < 11 ; i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        for(int i = 0 ; i < T ; i++){
            int num = sc.nextInt();
            System.out.println(dp[num]);
        }
    }
}

 */
