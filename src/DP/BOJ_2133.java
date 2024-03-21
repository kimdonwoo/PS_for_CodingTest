package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2133 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if(N%2 == 1){
            System.out.println(0);
            return;
        }

        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[2] = 3;

        for(int i = 4 ; i <= N ; i+=2){
            // 현재 i
            dp[i] = dp[i-2]*3;
            // 여기서 i 0까지 드가기
            for(int j = i-4; j >=0 ; j-=2 ){
                dp[i] += dp[j]*2;
            }
        }

        System.out.println(dp[N]);

        /*
        3*N 벽을 2*1과 1*2로 채우자

        이런 문제는 무조건 dp긴해

        DP[0] = 1
        DP[2] = 3
        N이 4일떄 :
        DP[4] = DP[2]*3 + DP[0]*2 = 11

        N이 6일때
        DP[6] = DP[4]*3 + DP[2]*2


        타일은 2칸짜리임
        만약 N이 홀수인 것은 채울 수 없음

        4칸


        맞네



         */




    }
}
