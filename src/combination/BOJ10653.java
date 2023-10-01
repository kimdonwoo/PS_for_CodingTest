package combination;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    DP 문제

    memo[n][k]는 n번째 좌표에 도착했을 때,
        그 전까지 k개를 건너뛴 경우에 최소 거리가 저장되는 것

 */

public class BOJ10653 {
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        Pair[] arr = new Pair[N];
        int[][] dist = new int[N][N];

        dp = new int[N][K+1];

        for(int i=0; i<N; i++) {
            for(int j=0; j<=K; j++)
                dp[i][j] = -1;
        }

        for(int i=0; i<N; i++) {
            input = br.readLine().split(" ");
            arr[i] = new Pair(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                dist[i][j] = Math.abs(arr[i].x-arr[j].x) + Math.abs(arr[i].y-arr[j].y);
                dist[j][i] = Math.abs(arr[i].x-arr[j].x) + Math.abs(arr[i].y-arr[j].y);
            }
        }

        System.out.println(dfs(dist, N-1, K));
    }

    public static int dfs(int[][] dist, int idx, int k) {
        if (idx == 0) return 0;
        if (dp[idx][k]!=-1) return dp[idx][k];

        int d = Integer.MAX_VALUE;

        for (int i=0; i<=k; i++) {
            if (idx-i-1 < 0) break;
            d = Math.min(dfs(dist, idx-i-1, k-i) + dist[idx-i-1][idx], d);
        }

        return dp[idx][k] = d;
    }

    public static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}