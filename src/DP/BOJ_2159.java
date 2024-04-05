package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2159 {
    static int[] dx = {0,1,-1,0,0};
    static int[] dy = {0,0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // i는 몇번짼지,
        long[][] dp = new long[N][5];
        for(int i = 0 ; i < N ; i++) Arrays.fill(dp[i],Long.MAX_VALUE);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] before = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};

        st = new StringTokenizer(br.readLine());
        int[] now = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};

        for(int i = 0 ; i < 5 ; i++){
            int nx = now[0]+dx[i];
            int ny = now[1]+dy[i];

            dp[0][i] = Math.abs(before[0]-nx) + Math.abs(before[1]-ny) ;
        }

        before = now;

        for(int i = 1 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            now = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};

            for(int j = 0 ; j < 5 ; j++){
                int nx = now[0]+dx[j];
                int ny = now[1]+dy[j];
                for(int k = 0 ; k < 5 ; k++){
                    int bx = before[0]+dx[k];
                    int by = before[1]+dy[k];

                    dp[i][j] = Math.min(dp[i][j] , dp[i-1][k] + Math.abs(bx-nx) + Math.abs(by-ny));
                }
            }

            before = now;
        }

        long res = Long.MAX_VALUE;
        for(int i = 0 ; i < 5 ; i++) res = Math.min(res,dp[N-1][i]);
        System.out.println(res);
    }
}
