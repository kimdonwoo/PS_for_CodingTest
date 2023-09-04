package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ17144 {
    static int R,C,T;
    static int[][] map;
    static boolean[][] visit;
    static ArrayList<int[]> air;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visit = new boolean[R][C];
        air = new ArrayList<>();

        for(int i = 0 ; i < R ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < C ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                // 여기서 청정기 위치 저장해두기 !!
                if(map[i][j] == -1){
                    air.add(new int[] {i,j});
                }
            }
        }

        // T초 반복
        for(int i = 0 ; i < T ; i ++) {
            // 1. 미세먼지 확산 (모든 칸에서 동시에 일어남! -> 조심)
            dust();
            // 2. 공기 청전기 작동
            airOn();
        }

        // 남은 미세먼지의 양 출력
        int res = 0;
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                if(map[i][j] > 0){
                    res += map[i][j];
                }
            }
        }

        System.out.println(res);
    }

    public static void dust(){
        // 즉 map에 있는 값들을 기준으로 더하기 전용 map에다가 다 기록해두기
        int[][] temp = new int[R][C];

        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                if(map[i][j] > 0){ // 미세먼지 존재하면
                    int count = 0;
                    for(int k = 0 ; k < 4; k++){
                        // 4번 이동해야 함!
                        int next_x = i + dx[k];
                        int next_y = j + dy[k];
                        if(next_x >= 0 && next_x < R && next_y >= 0 && next_y < C ){
                            if(map[next_x][next_y] >= 0) {
                                // 방문한 곳이면 또 방문 x
                                temp[next_x][next_y] += map[i][j] / 5;
                                count++;
                            }
                        }
                    }
                    map[i][j] = map[i][j] - (map[i][j]/5)*count;
                }
            }
        }

        // 만든 temp를 다 더해줘야함
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                if(temp[i][j] != 0) map[i][j] += temp[i][j];
            }
        }


    }

    public static void airOn(){
        // 순환 구현
        int[] up = air.get(0); // (up)
        int[] down = air.get(1);

        /*
            7*8 일때 (R : 7 / C : 8)
                up : (2,0)
                down : (3,0)
         */

        // 윗 순환 구현 up : (2,0) -> 아래,왼,위,오른 순으로 구현해야함 !
        // 1. 아래
        for(int i = up[0]-2 ; i >= 0  ; i--){
            map[i+1][0] = map[i][0];
        }

        // 2. 왼
        for(int i = 0 ; i < C-1 ; i++){
            map[0][i] = map[0][i+1];
        }

        // 3. 위
        for(int i = 0 ; i < up[0] ; i++){
            // 0 7 , 1 7 , 2 7
            map[i][C-1] = map[i+1][C-1];
        }

        // 4. 오른
        for(int i = C-1 ; i > 1 ; i--){
            map[up[0]][i] = map[up[0]][i-1];
        }
        map[up[0]][1] = 0;

        // 아랫 순환 구현 (위 , 왼 , 아래 , 오른순)

        // 1. 위 down[0]가 3임 , R은 7
        for(int i = down[0]+1 ; i < R-1 ; i++){
            map[i][0] = map[i+1][0];
        }

        // 2. 왼
        for(int i = 0 ; i < C-1 ; i++){
            map[R-1][i] = map[R-1][i+1];
        }

        // 3. 아래 6 5 4
        for(int i = R-1 ; i > down[0]  ; i--){
            map[i][C-1] = map[i-1][C-1];
        }

        // 4. 오른
        for(int i = C-1 ; i > 1 ; i--){
            map[down[0]][i] = map[down[0]][i-1];
        }
        map[down[0]][1] = 0;

    }
}
