package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17144 {
    static int R,C,T;
    static int[][] map;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static ArrayList<int[]> air = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map= new int[R][C];

        for(int i = 0 ; i < R ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < C ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    air.add(new int[] {i,j});
                }
            }
        }

        for(int i = 0 ; i < T ; i++){
            spread();
            act();
        }

        int res = 0;

        for(int i = 0 ; i < R ; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] != -1) res+=map[i][j];
            }
        }

        System.out.println(res);

    }

    private static void act() {

        int top = air.get(0)[0]; // 공기청정기 윗 부분좌표며,  반시계 방향으로 진행

        for (int x = top - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }

        for (int y = 0; y < C - 1; y++) {
            map[0][y] = map[0][y + 1];
        }

        for (int x = 0; x < top; x++) {
            map[x][C - 1] = map[x + 1][C - 1];
        }

        for (int y = C - 1; y > 1; y--) {
            map[top][y] = map[top][y - 1];
        }

        map[top][1] = 0; // 공기청정기로 나가는 곳이므로 먼지는 0이다.

        int bottom = air.get(1)[0]; // 공기청정기 밑 부분좌표며, 시계방향으로 진행

        for (int x = bottom + 1; x < R - 1; x++) {
            map[x][0] = map[x + 1][0];
        }

        for (int y = 0; y < C - 1; y++) {
            map[R - 1][y] = map[R - 1][y + 1];
        }

        for (int x = R - 1; x > bottom; x--) {
            map[x][C - 1] = map[x - 1][C - 1];
        }

        for (int y = C - 1; y > 1; y--) {
            map[bottom][y] = map[bottom][y - 1];
        }

        map[bottom][1] = 0; // 공기청정기로 나가는 곳이므로 먼지는 0이다.

    }

    // 동시에 퍼트리기
    private static void spread() {

        int[][] temp = new int[R][C];

        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){

                if(map[i][j] > 0){
                    for(int k = 0 ; k < 4 ; k++){
                        int nx = i+dx[k];
                        int ny = j+dy[k];

                        if(nx >= 0 && nx < R && ny >= 0 && ny < C){
                            if(map[nx][ny] != -1){
                                temp[nx][ny]+=map[i][j]/5;
                                temp[i][j]-=map[i][j]/5;
                            }
                        }
                    }
                }

            }
        }

        // 여기서 temp 값 map 에 합치기
        for(int i = 0 ; i < R ; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] != -1) map[i][j] += temp[i][j];
            }
        }

    }
}
