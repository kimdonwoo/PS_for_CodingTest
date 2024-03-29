package prac_zone;

import java.io.*;

public class DFS_combi_2 {


    static int[][] map;
    static int[][] trip;
    static boolean[][] visit;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int N,K;

    public static void main(String[] args) throws IOException{

        N = 5;
        K = 3;
        map = new int[N][N];
        trip = new int[N][N];
        visit = new boolean[N][N];

        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                map[i][j] = 1;
            }
        }

        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                visit[i][j] = true;
                combi(i,j,i,j,0,map[i][j]);
                visit[i][j] = false;
            }
        }

        for(int i = 0 ; i < N ; i ++){
            System.out.println();
            for(int j = 0 ; j < N ; j ++){
                System.out.print(map[i][j]+" ");
            }
        }
        System.out.println();

        for(int i = 0 ; i < N ; i ++){
            System.out.println();
            for(int j = 0 ; j < N ; j ++){
                System.out.print(trip[i][j]+" ");
            }
        }
    }

    // + 여기서 중복 없애기

    private static void combi(int startI, int startJ, int nowI, int nowJ, int now,int cnt) {
        if(now == K){
            trip[startI][startJ] = Math.max(trip[startI][startJ],cnt);
            return;
        }

        for(int i = 0 ; i < 4 ; i++ ){
            int nx = nowI + dx[i];
            int ny = nowJ + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if(visit[nx][ny]) continue;
            visit[nx][ny] = true;
            combi(startI,startJ,nx,ny,now+1,cnt+map[nx][ny]);
            visit[nx][ny] = false;
        }

    }

}


