package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17836 {
    /*
        N*M 맵 (1,1)
        용사는 (1,1)
        공주는 (N,M)
        T시간 내에 찾아야함

        무기 획득하면 끝

        (3 ≤ N, M ≤ 100, 1 ≤ T ≤ 10000)

        0 : 빈공간
        1 : 마법의 벽
        2 : 그람
     */
    static int N,M,T;
    static int[] dx ={0,0,1,-1};
    static int[] dy ={1,-1,0,0};
    static int[][] map;
    static int res = Integer.MAX_VALUE;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new LinkedList<>();
        visit[0][0] = true;
        q.add(new int[] {0,0,0});

        while(!q.isEmpty()){
            int[] now = q.poll();

            if(now[2] >= res) continue;

            if(now[0] == N-1 && now[1] == M-1){
                res = Math.min(res,now[2]);
                continue;
            }

            if(map[now[0]][now[1]] == 2){
                res = Math.min(res,now[2] +(N-1-now[0]+M-1-now[1]));
                continue;
            }

            for(int i = 0 ; i < 4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M ){
                    if(!visit[nx][ny] && map[nx][ny] != 1){
                        visit[nx][ny] = true;
                        q.add(new int[] {nx,ny,now[2]+1});
                    }
                }
            }
        }
        if(res > T){
            System.out.println("Fail");
        }else{
            System.out.println(res);
        }
    }
}


/*
    static void DFS(int x, int y, int time){
        if(time >= res) return;

        if(x == N-1 && y == M-1){
            res = Math.min(res,time);
            return;
        } else if (map[x][y] == 2) {
            res = Math.min(res,time +(N-1-x+M-1-y));
            return;
        }

        for(int i = 0 ; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M ){
                if(!visit[nx][ny] && map[nx][ny] != 1){
                    visit[nx][ny] = true;
                    DFS(nx,ny,time+1);
                    visit[nx][ny] = false;
                }
            }
        }
    }

 */