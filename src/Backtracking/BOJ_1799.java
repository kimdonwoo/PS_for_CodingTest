package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1799 {
    static int[][] chess;
    static int N;
    static int[] res = new int[2];
    static int resCnt = 0;
    static int[] dx = {-1,-1};
    static int[] dy = {1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        chess = new int[N][N];

        StringTokenizer st;
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                chess[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bishopDFS(0,0);
        resCnt++;
        bishopDFS(1,0);

        System.out.println(res[0]+res[1]);

    }

    private static void bishopDFS(int depth,int cnt) {
        if(depth >= N*N){
            res[resCnt] = Math.max(res[resCnt],cnt);
            return;
        }
        bishopDFS(depth+calIncr(depth),cnt);
        int nx = depth/N;
        int ny = depth%N;

        if(chess[nx][ny] == 1){
            if(checkBishop(nx,ny)){
                chess[nx][ny] = 2;
                bishopDFS(depth+calIncr(depth),cnt+1);
                chess[nx][ny] = 1;
            }
        }

    }

    private static boolean checkBishop(int nx, int ny) {
        for(int i = 0 ; i < 2 ;i++){
            int nextX = nx;
            int nextY = ny;

            while(true){
                nextX += dx[i];
                nextY += dy[i];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N ) break;
                if(chess[nextX][nextY] == 2){
                    return false;
                }
            }
        }

        return true;
    }

    private static int calIncr(int ind) {
        //크기가 홀 수일 경우 -> 2증가
        if (N % 2 == 1) return 2;
        //크기가 짝수일 경우
        if (ind % N == N-1) return 1;
        else if (ind % N == N-2) return 3;
        else return 2;
    }

}

/*
    // 여기서 0 인곳만 둘 수 있음
    private static int plusBishop(int nx, int ny) {

        int stackCnt = 0;
        chess[nx][ny] = 2;
        stack.push(new int[]{nx,ny});
        stackCnt++;

        for(int i = 0 ; i < 4 ;i++){
            int nextX = nx;
            int nextY = ny;

            while(true){
                nextX += dx[i];
                nextY += dy[i];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N ) break;
                if(chess[nextX][nextY] == 1){
                    chess[nextX][nextY] = 2;
                    stack.push(new int[]{nextX,nextY});
                    stackCnt++;
                }
            }
        }

        return stackCnt;
    }

    private static void minusBishop(int stackCnt) {
        while(stackCnt--> 0){
            int[] now = stack.pop();
            chess[now[0]][now[1]] = 1;
        }
    }

 */

/*
    private static boolean checkBishop(int nx, int ny) {

        for(int i = 0 ; i < 2 ;i++){
            int nextX = nx;
            int nextY = ny;

            while(true){
                nextX += dx[i];
                nextY += dy[i];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N ) break;
                if(chess[nextX][nextY] == 2){
                    return false;
                }
            }
        }

        return true;
    }
 */