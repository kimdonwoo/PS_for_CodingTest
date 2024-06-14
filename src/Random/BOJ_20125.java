package Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_20125 {
    static int[] dx = {0,0,1};
    static int[] dy = {-1,1,0};
    static boolean[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> res = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        map = new boolean[N][N];

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < N ; j++){
                if(str.charAt(j) == '*') map[i][j] = true;
            }
        }

        boolean check = false;
        int[] heart = new int[2];

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(map[i][j]){
                    heart[0] = i+1;
                    heart[1] = j;
                    check = true;
                    break;
                }
            }
            if(check) break;
        }

        // heart 중심으로 (왼,오,허리)
        int nowX = 0 ,nowY=0;
        for(int i = 0 ; i < 3; i++){
            nowX = heart[0]+dx[i];
            nowY = heart[1]+dy[i];
            int cnt = 0;

            while((nowX >= 0 && nowX < N && nowY >= 0 && nowY < N) && map[nowX][nowY]){
                nowX +=dx[i];
                nowY +=dy[i];
                cnt++;
            }
            res.add(cnt);
        }

        cntLen(res,nowX,nowY-1,N);
        cntLen(res,nowX,nowY+1,N);

        System.out.println((heart[0]+1)+" "+(heart[1]+1));
        for(int i : res){
            System.out.print(i+" ");
        }

    }

    private static void cntLen(ArrayList<Integer> res, int x, int y, int N) {

        int cnt = 0;

        while((x >= 0 && x < N && y >= 0 && y < N) && map[x][y]){
            x +=dx[2];
            y +=dy[2];
            cnt++;
        }
        res.add(cnt);
    }
}
