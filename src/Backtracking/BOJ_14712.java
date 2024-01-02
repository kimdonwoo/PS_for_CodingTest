package Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14712 {

    static boolean[][] map;
    static int N,M;
    static int res = 0;
    static int[] dx = {-1,0,-1};
    static int[] dy = {-1,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        backtracking(0);

        System.out.println(res);

    }

    static private void backtracking(int count){
        // 선택 완료
        if(count == N*M){
            res++;
            return;
        }

        backtracking(count+1); // 네모 x

        // 선택 - 사각형 안만들어지는 경우만 backtracking 들어가기
        int x = count / M;
        int y = count % M;

        // 네모 생성되는지 체크하고 생성안되면 체크 후 backtracking 들어가기
        // 3개중에 하나라도 boolean or 범위 밖이면 들어갈 수 있다.
        for(int i = 0 ; i < 3; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if((nx < 0 || ny < 0 || nx >= N || ny >= M) || (!map[nx][ny])){
                map[x][y] = true;
                backtracking(count+1);
                map[x][y] = false;
                break;
            }
        }


    }
}

