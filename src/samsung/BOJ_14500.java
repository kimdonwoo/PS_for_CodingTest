package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {
    static int N,M;
    static int[][] map;
    static int res = Integer.MIN_VALUE;
    static int[] dx = {-1,-1,-1,0,1,1,1};
    static int[] dy = {0,1,2,3,2,1,0};
    static int[] ddx = {0,1,2,3,2,1,0};
    static int[] ddy = {-1,-1,-1,0,1,1,1};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                check(i,j);
            }
        }

        System.out.println(res);
    }

    private static void check(int x, int y) {
        int temp = 0;

        // 1, 3, 5 처리
        if(y+2 < M) {
            temp= map[x][y]+map[x][y+1]+map[x][y+2];
            for (int i = 0; i < 7; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                    res = Math.max(res,temp+map[nx][ny]);
                }
            }
        }

        if(x+2 < N) {
            temp= map[x][y]+map[x+1][y]+map[x+2][y];
            for (int i = 0; i < 7; i++) {
                int nx = x + ddx[i];
                int ny = y + ddy[i];
                if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                    res = Math.max(res,temp+map[nx][ny]);
                }
            }
        }

        // 2 처리
        if(x+1 >= 0 && x+1 < N && y+1 >= 0 && y+1 < M){
            res = Math.max(res,map[x][y]+map[x+1][y]+map[x][y+1]+map[x+1][y+1]);
        }

        // 4-1 처리
        if(y+1 < M) {
            temp= map[x][y]+map[x][y+1];
            if(x+1 < N && y-1 >= 0){
                res = Math.max(res,temp+map[x+1][y-1]+map[x+1][y]);
            }
            if(x+1 < N && y+2 < M){
                res = Math.max(res,temp+map[x+1][y+1]+map[x+1][y+2]);
            }
        }

        // 4-2 처리
        if(x+1 < N) {
            temp= map[x][y]+map[x+1][y];
            if(x-1 >= 0 && y+1 < M){
                res = Math.max(res,temp+map[x-1][y+1]+map[x][y+1]);
            }
            if(x+2 < N && y+1 < M){
                res = Math.max(res,temp+map[x+1][y+1]+map[x+2][y+1]);
            }
        }
    }
}
