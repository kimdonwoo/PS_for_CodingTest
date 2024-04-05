package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<int[]> airplanes = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            if(start < end){
                airplanes.add(new int[]{start,end,val});
            }
        }

        Collections.sort(airplanes,(o1,o2)->{
            return o1[1]-o2[1];
        });

        // 0이 몇번쨰인지, 1이 지금까지 값
        // 무조건 메모리 초과 뜰듯 ? 흠..
        int[][] dp = new int[M][N+1];
        for(int i = 0 ; i < M ; i++) Arrays.fill(dp[i],-1);
        dp[0][1] = 0;

        for(int i = 0 ; i < airplanes.size() ; i++){
            // 이전꺼가 M넘었으면 넘겨
            int start = airplanes.get(i)[0];
            int end = airplanes.get(i)[1];
            int val = airplanes.get(i)[2];

            for(int j = 1 ; j < M ; j++){
                if(dp[j-1][start] >= 0) {
                    dp[j][end] = Math.max(dp[j][end], dp[j - 1][start] + val);
                }
            }
        }
        int res = 0;

        for(int i = 1 ; i < M ; i++){
            res = Math.max(res,dp[i][N]);
        }
        System.out.println(res);
        //이렇게 하고 dp[M][N]

        /*
        문제 : 그게 아닐수도 있음

        dp : 몇번짼지, (현재까지 최댓값)
        몇번째 비행기인지
        dp[몇번탔는지][도시] = 현재까지 값

        start, end ,val

        dp[i][end] = Math.max(dp[i][end], dp[i-1][start] + val);
        여기서 M보다 더 많이타는건 없애고
        1출발은 어떻게 구현하지..?
            -> dp 다 -1로 채우고 dp[0][1] = 0

         */





        /*
        dp에 몇번짼지랑 값 두개 저장해야겠다

         */

        /*
            N개
                동 1 ~ N 서
                M개의 도시 여행 : 1시작 N도착

                기내식 점수의 총합이 최대가 되도록
                N : 300 , K 100_000
                반대로 돌아가는 경우 x
                흠..

                상황변수 :

                1 2 3
                0 5 10 = 10;

                100_000개
                1 2 5
                1 3 10
                2 3 3
                1 3 4

                잠시만 M개가 중요하네

                무조건 1출발 무조건 N도착(이때 M)



         */
    }
}
