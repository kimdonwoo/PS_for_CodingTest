package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1726 {
    static class robot{
        int x;
        int y;
        int dir;
        int cnt;

        public robot(int x,int y, int dir, int cnt){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    static int res = 0,N,M;
    // 동서남북
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] map;
    static int[][] visit;
    static int[][] changeDir = {{2,3},{2,3},{1,0},{1,0}};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        visit = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                if(st.nextToken().equals("0")){
                    map[i][j] = true;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] start = new int[3];
        for(int i = 0 ; i < 3 ; i++){
            start[i] = Integer.parseInt(st.nextToken())-1;
        }

        st = new StringTokenizer(br.readLine());
        int[] end = new int[3];
        for(int i = 0 ; i < 3 ; i++){
            end[i] = Integer.parseInt(st.nextToken())-1;
        }

        Queue<robot> q = new LinkedList<>();
        q.add(new robot(start[0],start[1],start[2],0));
        visit[start[0]][start[1]] += 1 << start[2];

        while(!q.isEmpty()){

            robot now = q.poll();

            // 도착
            if(now.x == end[0] && now.y == end[1]  && now.dir == end[2]){
                res = now.cnt;
                break;
            }

            // 현재 위치에서 방향으로 3번 이동하기
            for(int i = 1 ; i <= 3; i++){
                int nx = now.x+i*dx[now.dir];
                int ny = now.y+i*dy[now.dir];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) break;
                if(!map[nx][ny]) break;
                // 방문안했으면
                if((visit[nx][ny] & (1 << now.dir)) == 0){
                    visit[nx][ny] += (1 << now.dir);
                    q.add(new robot(nx,ny,now.dir,now.cnt+1));
                }
            }

            // 회전 2번 하기
            for(int i = 0 ; i < 2 ; i++){
                int dir = changeDir[now.dir][i];

                if((visit[now.x][now.y] & (1 << dir)) == 0){
                    visit[now.x][now.y] += (1 << dir);
                    q.add(new robot(now.x,now.y,dir,now.cnt+1));
                }
            }

        }

        System.out.println(res);

    }
}
