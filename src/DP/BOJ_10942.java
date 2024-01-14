package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // [dp 계산]
        boolean[][] dp = new boolean[N][N];

        // dp 초기화
        for(int i = 0 ; i < N ; i++){
            dp[i][i] = true;
            if(i != N-1 && arr[i] == arr[i+1]) dp[i][i+1] = true;
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = i ; j < i+2 ; j++){
                if(i == N-1 && j == N) continue;
                if(dp[i][j]){
                    int d = 0;
                    while(true){
                        d++;
                        if(i-d < 0 || j+d >= N) break;
                        if(arr[i-d] != arr[j+d]) break;
                        dp[i-d][j+d] = true;
                    }
                }
            }
        }

        //출력
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < T ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            if(dp[a][b]) sb.append(1+"\n");
            else sb.append(0+"\n");
        }

        System.out.println(sb.toString());

        /*
            0.5초

            N : 2_000
            M : 1_000_000
                -> 그냥 O(n)으로 해야함
                -> 즉 미리 배열에 저장해야할듯 ..?

            N개의 자연수
            M번의 질문
                - S번째부터 E까지 팰린드롬 여부
                - E-S+1 개

            M일때마다 이분접근은 ?
                -> 2 000 000 000 -> 초과

            dp 적용 어떻게 ?

            1 2 1 3 1 2 1
            -     -
            dp[i][j] : i시작 j 도착 ?

            N : 2000

              1 2 3 4 5 6 7
            1 o x o x x x o
            2 - o
            3     o
            4
            5
            6
            7

             -> 흠... 1
                어떤게 팰린드롬이면
                dp[i][j]가 팰린드롬이면
                dp[i-1][j+1]은 arr[i-1]이랑 arr[j+1]만 확인해보면 됨





         */
    }
}
