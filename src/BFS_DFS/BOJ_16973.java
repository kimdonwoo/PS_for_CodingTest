package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16973 {
    static int N,M;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};
    static int res = 0;
    static int H,W;
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
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int Sr = Integer.parseInt(st.nextToken())-1;
        int Sc = Integer.parseInt(st.nextToken())-1;
        int Fr = Integer.parseInt(st.nextToken())-1;
        int Fc = Integer.parseInt(st.nextToken())-1;


        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {Sr,Sc,0});
        visit[Sr][Sc] = true;

        while (!q.isEmpty()){

            int now[] = q.poll();

            // 도착 조건
            if(now[0] == Fr && now[1] == Fc){
                res = now[2];
                break;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(inRange(nx,ny)){ //여기서 범위 체크
                    if(!visit[nx][ny] && check(nx,ny)){ // visit 체크 벽체크
                        visit[nx][ny] = true;
                        q.add(new int[] {nx,ny,now[2]+1});
                    }
                }
            }

        }

        if(res == 0){
            System.out.println(-1);
        }else{
            System.out.println(res);
        }


    }

    public static boolean inRange(int x , int y){
        if(x >= 0 && x+H-1 < N && y >= 0 && y+W-1 < M ){
            return true;
        }
        return false;
    }


    public static boolean check(int x , int y){

        for(int i = x ; i < x+H ; i++){
            for(int j = y ; j < y+W ; j++){
                if(map[i][j] == 1) return false;
            }
        }
        return true;
    }
}
