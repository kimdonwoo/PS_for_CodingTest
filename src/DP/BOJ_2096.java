package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] maxDP = new int[N+1][3];
        int[][] minDP = new int[N+1][3];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 3 ;i++){
            minDP[0][i] = maxDP[0][i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1 ; i < N ;i++){
            st = new StringTokenizer(br.readLine());

            // 1
            int now = Integer.parseInt(st.nextToken());
            minDP[i][0] = Math.min(minDP[i-1][0],minDP[i-1][1]) + now;
            maxDP[i][0] = Math.max(maxDP[i-1][0],maxDP[i-1][1]) + now;

            // 2
            now = Integer.parseInt(st.nextToken());
            minDP[i][1] = Math.min(minDP[i-1][0],minDP[i-1][1]);
            minDP[i][1] = Math.min(minDP[i][1],minDP[i-1][2]);
            minDP[i][1]+=now;

            maxDP[i][1] = Math.max(maxDP[i-1][0],maxDP[i-1][1]);
            maxDP[i][1] = Math.max(maxDP[i][1],maxDP[i-1][2]);
            maxDP[i][1]+=now;

            // 3
            now = Integer.parseInt(st.nextToken());
            minDP[i][2] = Math.min(minDP[i-1][2],minDP[i-1][1]) + now;
            maxDP[i][2] = Math.max(maxDP[i-1][2],maxDP[i-1][1]) + now;

        }

        minDP[N][1] = Math.min(minDP[N-1][0],minDP[N-1][1]);
        minDP[N][1] = Math.min(minDP[N][1],minDP[N-1][2]);
        maxDP[N][1] = Math.max(maxDP[N-1][0],maxDP[N-1][1]);
        maxDP[N][1] = Math.max(maxDP[N][1],maxDP[N-1][2]);

        System.out.println(maxDP[N][1]+" "+minDP[N][1]);

    }
}
