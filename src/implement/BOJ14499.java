package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14499 {
    static int[] dice = new int[7];
    static int n,m,x,y;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++) {
            int d = Integer.parseInt(st.nextToken());
            move(d);
        }

    }

    static void move(int d) {
        int nx = x + dx[d-1];
        int ny = y + dy[d-1];
        if(nx <0 || ny < 0 || nx > m-1 || ny > n-1) return;
        roll(d, nx, ny);
        x = nx; y = ny;

    }

    // 1 2 3 4 (동 서 남 북)
    static void roll(int d, int x, int y) {
        int tmp = dice[3];
        switch(d) {
            case 1:
                dice[3] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[2];
                dice[2] = tmp;
                break;
            case 2:
                dice[3] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[4];
                dice[4] = tmp;
                break;
            case 3:
                dice[3] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[1];
                dice[1] = tmp;
                break;
            case 4:
                dice[3] = dice[1];
                dice[1] = dice[6];
                dice[6] = dice[5];
                dice[5] = tmp;
                break;
        }
        if(map[y][x] == 0) {
            map[y][x] = dice[6];
        } else {
            dice[6] = map[y][x];
            map[y][x] =0;
        }
        System.out.println(dice[3]);

    }
}


//    static int[][] dice = {
//            {},
//            {0,3,4,2,5},
//            {0,3,4,6,1},
//            {0,6,1,2,5},
//            {0,1,6,2,5},
//            {0,3,4,1,6},
//            {0,3,4,5,2}
//    };
//    // 동서남북 순
//    static int[] dx = {0,0,-1,1};
//    static int[] dy = {1,-1,0,0};
//
//    static int[] dice_val = {0,0,0,0,0,0}; // 1,2,3,4,5,6
//    static int now = 1; // 현재 바닥에 놓인 주사위 숫자 - 1
//
//    static int N,M;
//
//    static int[][] map;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//
//        // 초기 주사위 위치
//        int x0 = Integer.parseInt(st.nextToken());
//        int y0 = Integer.parseInt(st.nextToken());
//
//        int K = Integer.parseInt(st.nextToken());
//
//        map = new int[N][M];
//
//        for(int i = 0 ; i < N ; i ++){
//            st = new StringTokenizer(br.readLine());
//            for(int j = 0 ; j < M ; j ++){
//                map[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//
//        // 명령 : 동 1 서 2 북 3 남 4
//        st = new StringTokenizer(br.readLine());
//        for(int i = 0 ; i < K ; i++){
//            int num = Integer.parseInt(st.nextToken());
//
//            // 다음칸이 범위 내인지 붵 체크
//            int next_x = x0+ dx[num-1];
//            int next_y = y0+ dy[num-1];
//            if(next_x < 0 || next_x >=N || next_y <0 || next_y >=M) continue;
//
//            // 범위 안이니 이제 이동시켜줌
//            x0 = next_x;
//            y0 = next_y;
//            now = dice[now][num];
//
//            if(map[next_x][next_y] != 0){
//                // 다이스 값 넣고 다이스 0으로
//                dice_val[now-1] = map[next_x][next_y];
//                map[next_x][next_y] = 0;
//            }else{
//                map[next_x][next_y] = dice_val[now-1];
//                dice_val[now-1] = 0;
//            }
//
//            // 출력 : now가 2면 5의 val를 보여줘야함
//            System.out.println(dice_val[6 -now]);
//            // 6 - now
//
//        }
//
//    }
//
//}
