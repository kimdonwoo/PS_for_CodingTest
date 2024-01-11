package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int n, m;
        long result=0;
        long [][] dp = new long [11][4001];

        for(int j=1; j<=4000; j++){
            dp[1][j] = 1;
        }

        for(int j=2; j<=10; j++){
            for(int k=1; k<=4000;k++){
                if(dp[j-1][k]!=0){
                    for(int q =k*2; q<=4000; q++){
                        dp[j][q] += dp[j-1][k];
                    }
                }
            }
        }

        for(int i=0; i<t; i++){
            String[] s = br.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            m = Integer.parseInt(s[1]);
            result=0;
            for(int k=1; k<=m; k++){
                result+=dp[n][k];
            }
            System.out.println(result);
        }
    }
}

/*
    dp[i][j] = val에서

    i는 n이고
    j는 m이다
    즉, dp[i][j] = val 의미는
        현재 i번째 숫자에 j를 골랐을때 나올 수 있는 경우의 수

    dp[i-1][j:0~m]을 돌다가 0이 아니라면
        dp[i]의 값들중 2*j부터 m까지 dp[i-1][j]을 더해줍니다

    dp[i][j] = dp[i-1][0~j/2]

 */



