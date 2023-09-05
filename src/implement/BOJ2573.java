package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2573 {
    static int N,M;
    static int[][] map;
    static int[][] sea;
    static boolean[][] visit;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

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

        int time = 0;

        while(true){

            time++;

            sea = new int[N][M];

            // sea check
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(map[i][j] != 0){
                        int seaCount = 0;
                        for(int k = 0 ; k < 4 ; k++){
                            int next_x = i + dx[k];
                            int next_y = j + dy[k];

                            if(next_x >= 0 && next_x < N && next_y >= 0 && next_y < M){
                                if(map[next_x][next_y] == 0) seaCount++;
                            }
                        }
                        sea[i][j] = seaCount;
                    }
                }
            }

            // 여기서 녹이기
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(sea[i][j] > 0){
                        map[i][j] = (map[i][j] < sea[i][j]) ? 0 : map[i][j] - sea[i][j];
                    }
                }
            }

            // 두 덩어리로 분리 시 탈출
            // 또는 끝까지 녹으면 탈출
            int count = 0;
            visit = new boolean[N][M];

            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(map[i][j] > 0 && !visit[i][j]){
                        visit[i][j] = true;
                        DFS(i,j);
                        count++;
                    }
                }
            }

            if(count == 0){ // 다 녹음
                time = 0;
                break;
            }else if(count > 1){ // 두 덩어리
                break;
            }


        }

        System.out.println(time);

    }

    public static void DFS(int x, int y){

        for(int i = 0 ; i < 4 ; i++){
            int next_x = x + dx[i];
            int next_y = y + dy[i];

            if(next_x >= 0 && next_x < N && next_y >= 0 && next_y < M) {
                if(!visit[next_x][next_y] && map[next_x][next_y] > 0){
                    visit[next_x][next_y] = true;
                    DFS(next_x,next_y);
                }
            }
        }

    }
}
