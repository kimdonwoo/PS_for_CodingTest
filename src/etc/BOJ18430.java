package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18430 {

    static int N,M;
    static int[][] map;
    static boolean[][] visit;
    // 동 남 서 북
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    static int res = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0,0);

        System.out.println(res);
    }

    private static void DFS(int depth, int temp){
        if(depth ==N*M){
            res = Math.max(res, temp);
            return;
        }

        int y = depth/M;
        int x = depth%M;

        if(!visit[y][x]){
            if(y+1<N && x-1>=0 && !visit[y+1][x] && !visit[y][x-1]){
                visit[y][x]=true;
                visit[y+1][x]=true;
                visit[y][x-1]=true;
                DFS(depth+1, temp+map[y+1][x]+map[y][x-1]+(map[y][x]*2));
                visit[y][x]=false;
                visit[y+1][x]=false;
                visit[y][x-1]=false;
            }
            if(y+1<N && x+1<M && !visit[y+1][x] && !visit[y][x+1]){
                visit[y][x]=true;
                visit[y+1][x]=true;
                visit[y][x+1]=true;
                DFS(depth+1, temp+map[y+1][x]+map[y][x+1]+(map[y][x]*2));
                visit[y][x]=false;
                visit[y+1][x]=false;
                visit[y][x+1]=false;
            }
            if(y-1>=0 && x-1>=0 && !visit[y-1][x] && !visit[y][x-1]){
                visit[y][x]=true;
                visit[y-1][x]=true;
                visit[y][x-1]=true;
                DFS(depth+1, temp+map[y-1][x]+map[y][x-1]+(map[y][x]*2));
                visit[y][x]=false;
                visit[y-1][x]=false;
                visit[y][x-1]=false;
            }
            if(y-1>=0 && x+1<M && !visit[y-1][x] && !visit[y][x+1]){
                visit[y][x]=true;
                visit[y-1][x]=true;
                visit[y][x+1]=true;
                DFS(depth+1, temp+map[y-1][x]+map[y][x+1]+(map[y][x]*2));
                visit[y][x]=false;
                visit[y-1][x]=false;
                visit[y][x+1]=false;
            }
        }
        DFS(depth+1, temp);
    }


}
