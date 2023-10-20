package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600 {
    static boolean[][][] visit;
    static int[][] map;
    static int res = -1;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    /*
        2 2 -2 -2 1 -1 1 -1
        1 -1 1 -1 2 2 -2 -2


     */
    static int[] ddx = {2,2,-2,-2,1,-1,1,-1 };
    static int[] ddy = {1,-1,1,-1,2,2,-2,-2};
    static int K , W, H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        visit = new boolean[H][W][K+1];
        map = new int[H][W];

        for(int i = 0 ; i < H ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < W ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();

        if(res == -1){
            System.out.println(-1);
        }else {
            System.out.println(res);
        }


    }

    static void BFS(){

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0,0,K,0});
        visit[0][0][K] = true;

        while(!q.isEmpty()){
            int[] now = q.poll();

            if(now[0] == H-1 && now[1] == W-1 ){
                res = now[3];
                break;
            }

            if(now[2] > 0){
                for(int i = 0 ; i < 8; i++){
                    int nx = now[0]+ddx[i];
                    int ny = now[1]+ddy[i];

                    if(nx >= 0 && ny >= 0 && nx < H && ny < W ){
                        if(!visit[nx][ny][now[2]-1] && map[nx][ny] != 1){
                            visit[nx][ny][now[2]-1] = true;
                            q.add(new int[] {nx,ny,now[2]-1,now[3]+1});
                        }
                    }
                }
            }

            for(int i = 0 ; i < 4; i++){
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];

                if(nx >= 0 && ny >= 0 && nx < H && ny < W ){
                    if(!visit[nx][ny][now[2]] && map[nx][ny] != 1){
                        visit[nx][ny][now[2]] = true;
                        q.add(new int[] {nx,ny,now[2],now[3]+1});
                    }
                }
            }

        }

    }
}
