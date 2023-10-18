package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {

    /*
        미로안에 불, 지훈 (둘다벽 통과 x)
        -> 탈출 여부, 시간
        불은 상하좌우로 번짐

        가장자리에 접한 공간에서 탈출 가능

        R*C map
        #: 벽
        .: 지나갈 수 있는 공간
        J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간) - 하나만 주어짐
        F: 불이 난 공간

        -> BFS

     */
    static int R,C;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static ArrayList<int[]> jihoon = new ArrayList<>();
    //static ArrayList<int[]> fire = new ArrayList<>();
    static Queue<int []> fires = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visit = new boolean[R][C];

        for(int i = 0 ; i < R; i++){
            String temp = br.readLine();
            for(int j = 0 ; j < C; j++){
                map[i][j] = temp.charAt(j);
                if(map[i][j] == 'J'){
                    map[i][j] = '.';
                    if(i == 0 || i == R-1 || j == 0 || j == C-1){
                        System.out.println(1);
                        return;
                    }
                    jihoon.add(new int[] {i,j});
                }
                if(map[i][j] == 'F'){
                    fires.add(new int[] {i,j});
                }
            }
        }

        int res = bfs();
        if(res == 0){
            System.out.println("IMPOSSIBLE");
        }else{
            System.out.println(res+1);
        }

    }

    static int bfs(){

        Queue<int []> q = new LinkedList<>();

        q.add(new int[] {jihoon.get(0)[0] , jihoon.get(0)[1] , 0 });
        visit[jihoon.get(0)[0]][jihoon.get(0)[1]] = true;
        // 여기다 불 조절 큐를 추가해야함
        int time = 0;
        while(!q.isEmpty()){

            int now[] = q.poll();

            if(time != now[2]) {
                time++;
                int fs = fires.size();
                for (int i = 0; i < fs; i++) {
                    int[] fire = fires.poll();

                    for (int j = 0; j < 4; j++) {
                        int firenx = fire[0] + dx[j];
                        int fireny = fire[1] + dy[j];

                        if (firenx >= 0 && fireny >= 0 && firenx < R && fireny < C) {
                            if (map[firenx][fireny] == '.') {
                                map[firenx][fireny] = 'F';
                                fires.add(new int[]{firenx, fireny});
                            }
                        }
                    }
                }
            }

            if(map[now[0]][now[1]] == 'F') continue;

            if(now[0] == 0 || now[0] == R-1 || now[1] == 0 || now[1] == C-1){
                return now[2];
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];

                if(nx >= 0 && ny >= 0 && nx < R && ny < C){
                    if(!visit[nx][ny] && map[nx][ny]=='.'){
                        visit[nx][ny] = true;
                        q.add(new int[] {nx,ny,now[2]+1});
                    }
                }
            }

        }


        return 0;
    }

}
