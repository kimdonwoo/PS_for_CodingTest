package DP;

import java.util.Scanner;

public class BOJ11057 {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        // 수의 길이에 따른 오르막 수를 구해라

        int[][] dp = new int[10][N+1];
        for(int i = 0 ; i < 10 ;i ++){
            dp[i][1] = 1;
        }


        for(int i = 2 ;i < N+1 ; i++){
            for(int j = 0 ;j < 10; j++){
                for(int k = 0 ; k <= j ; k++) {
                    dp[j][i] += (dp[k][i - 1]%10007);
                }
            }
        }

        int sum = 0;
        for(int i = 0 ; i < 10 ; i++){
            sum+=dp[i][N];
        }

        System.out.println(sum % 10007);
    }
}
