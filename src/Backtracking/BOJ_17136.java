package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17136 {
    static int[][] map;
    static int res = 10000;
    static int[] select;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[10][10];
        select = new int[6];
        Arrays.fill(select,5);

        for(int i = 0 ; i < 10 ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 10 ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        DFS(0,0);

        if(res == 10000) System.out.println(-1);
        else System.out.println(res);

    }

    // 현재 위치랑 색종이 갯수
    private static void DFS(int depth, int count) {

        // 만약 depth 도달하면 갱신
        if(depth == 100){
            res = Math.min(res,count);
            return;
        }

        int nowX = depth / 10;
        int nowY = depth % 10;

        if(map[nowX][nowY] == 1){
            for(int i = 1 ; i <= 5 ; i++){
                if(select[i] > 0 && check(nowX,nowY,i)){
                    // 덮기
                    attach(nowX,nowY,i);
                    select[i]--;

                    DFS(depth+i,count+1);
                    // 빼기
                    detach(nowX,nowY,i);
                    select[i]++;

                }
            }
        }else{
            // 2 or 0
            DFS(depth+1,count);
        }

    }

    private static void detach(int nowX, int nowY, int len) {
        for(int i = nowX ; i < nowX+len ; i++){
            for(int j = nowY ; j < nowY+len ; j++){
                map[i][j] = 1;
            }
        }
    }

    private static void attach(int nowX, int nowY, int len) {
        for(int i = nowX ; i < nowX+len ; i++){
            for(int j = nowY ; j < nowY+len ; j++){
                map[i][j] = 2;
            }
        }
    }

    private static boolean check(int nowX, int nowY, int len) {

        for(int i = nowX ; i < nowX+len ; i++){
            for(int j = nowY ; j < nowY+len ; j++){
                if(i >= 10 || j >= 10) return false;
                if(map[i][j] == 0 || map[i][j] == 2) return false;
            }
        }

        return true;
    }
}
