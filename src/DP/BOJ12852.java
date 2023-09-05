package DP;


import java.util.Scanner;

public class BOJ12852 {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int dp[] = new int[N + 1];
        int before[] = new int[N + 1];

        StringBuffer sb = new StringBuffer();

        /*
            dp 배열 bottom up 으로 저장

            먼저 i-1 적용하고 3으로 나누어 떨어지면서 dp[i / 3] + 1랑 dp[i] 비교


         */

        dp[1] = 0;

        for(int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            before[i] = i - 1;

            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                before[i] = i / 3;
            }
            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                before[i] = i / 2;
            }
        }
        System.out.println(dp[N]);

        while(N > 0){
            sb.append(N+" ");
            N = before[N];
        }

        System.out.print(sb.toString());
    }
}
