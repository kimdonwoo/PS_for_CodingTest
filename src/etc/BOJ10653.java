package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    조합 => DP 문제

    memo[n][k]는 n번째 좌표에 도착했을 때,
        그 전까지 k개를 건너뛴 경우에 최소 거리가 저장되는 것

 */

public class BOJ10653 {
    static int arr[][],N,K, memo[][];

    public static void main(String[] args) throws IOException {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int [][]a = new int[N+1][2];

        for(int i =1; i<= N; i++) {
            st = new StringTokenizer(br.readLine());
            a[i][0]= Integer.parseInt(st.nextToken());
            a[i][1]= Integer.parseInt(st.nextToken());
        }

        arr = new int[N+1][N+1];

        // 거리 저장
        for(int i =1; i< N;i++) {
            for(int j =i+1; j<= N; j++) {
                arr[i][j] = Math.abs(a[i][0]-a[j][0])+Math.abs(a[i][1]-a[j][1]);
            }
        }

        memo = new int[N+1][K+1];
        System.out.println(check(N,K));
    }

    // n개중에 k 뛰어넘기
    private static int check(int n,int k) {
        // 이미 방문한 경우 다시 계산하지말고 dp 배열값 바로 리턴하기
        if(memo[n][k]!=0)
            return memo[n][k];

        // n이 1일 때는 무조건 0이니깐
        if(n==1) return 0;

        // 최대값 넣어주고
        memo[n][k] = Integer.MAX_VALUE;

        for(int i = 0; i<= k; i++) {
            if(0<n-i-1){
                // check(n-i-1,k-i)+arr[n-i-1][n]는
                memo[n][k] = Math.min(check(n-i-1,k-i)+arr[n-i-1][n],memo[n][k] );
            }
        }
        return memo[n][k];
    }
}