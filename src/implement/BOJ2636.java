package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ2636 {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visit;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static HashMap<int[],Boolean> melt;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];


        // 초기화
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                if(Integer.parseInt(st.nextToken())==1) map[i][j] = 1;
            }
        }

        int time = 0;

        while(true){
            // 1. 치즈 남아 있는지 체크 (1이 하나도 없으면 while 탈출)
            if(!checkCheese()) break;

            time++;

            // 2. 남아 있으면 DFS 돌기
            melt = new HashMap<>();
            visit = new boolean[N][M];
            count = 0;
            DFS(0,0);

            // 3. 치즈 녹이기
            for(int[] m : melt.keySet()){
                map[m[0]][m[1]] = 0;
            }

        }

        System.out.println(time);
        System.out.println(count);


    }

    public static boolean checkCheese(){
        // map에 1이 하나라도 있으면 true
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] == 1) return true;
            }
        }
        // 전부다 0이면 false
        return false;
    }

    public static void DFS(int x, int y){

        for(int i = 0 ; i < 4; i++){
            int next_x = x+dx[i];
            int next_y = y+dy[i];

            if(next_x >= 0 && next_x < N && next_y >= 0 && next_y < M){ // 범위 이내
                if(!visit[next_x][next_y]){// 방문 안하고 0인곳이면
                    if(map[next_x][next_y] == 0) {
                        visit[next_x][next_y] = true;
                        DFS(next_x, next_y);
                    }else{
                        // 방문 안하고 치즈 있는 곳
                        visit[next_x][next_y] = true;
                        count++;
                        melt.put(new int[] {next_x,next_y} , true);
                    }
                }
            }

        }


    }
}
