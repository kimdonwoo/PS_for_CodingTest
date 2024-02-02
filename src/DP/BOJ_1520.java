package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1520 {
    static class node{
        int high;
        int x;
        int y;

        public node(int high,int x, int y){
            this.high = high;
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dp = new int[N][M];
        dp[0][0] = 1;
        ArrayList<node> nodes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                nodes.add(new node(map[i][j], i, j));
            }
        }

        Collections.sort(nodes, (o1, o2) -> {
            if (o1.high == o2.high) {
                if (o1.x == o2.x) {
                    return o1.y - o2.y;
                }
                return o1.x - o2.x;
            }
            return o2.high - o1.high;
        });

        // nodes 돌면서 주변에 자기보다 낮은애한테 자기만큼 더해주기
        for (node n : nodes) {
            if(n.x == N-1 && n.y == M-1) break;
            if (dp[n.x][n.y] == 0) continue;
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (map[nx][ny] < map[n.x][n.y]) {
                        dp[nx][ny]+=dp[n.x][n.y];
                    }
                }
            }
        }

        System.out.println(dp[N-1][M-1]);
    }
}
