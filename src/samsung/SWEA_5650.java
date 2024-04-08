package samsung;

import java.util.Scanner;
import java.util.*;

public class SWEA_5650 {

    static int[][] dirChange = {{0, 0, 0, 0}, {1, 2, 3, 0}, {1, 3, 0 ,2}, {3, 0, 1, 2}, {2, 0, 3, 1}, {1, 0, 3, 2}};
    static int[] moveR = {0, 0, -1, 1};
    static int[] moveC = {1, -1, 0, 0};

    static int[][] map;
    static ArrayList<Pos>[] wormhole;

    static int N;
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc = 1; tc<= T; tc++) {
            N = sc.nextInt();
            map = new int[N][N];
            wormhole = new ArrayList[5];

            for(int i=0; i<5; i++) {
                wormhole[i] = new ArrayList<>();
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    map[i][j] = sc.nextInt();
                    if(map[i][j] >= 6) {
                        wormhole[map[i][j]-6].add(new Pos(i, j));
                    }
                }
            }


            answer = 0;

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j]!=0) continue;
                    for(int dir=0; dir<4; dir++) {
                        int score = check(i, j, dir);
                        if(score > answer)
                            answer = score;

                    }
                }
            }

            System.out.println("#"+tc+" "+answer);
        }

    }

    static int check(int r, int c, int dir) {
        int score = 0;

        int nowR = r;
        int nowC = c;
        int move = 0;

        while(true) {

            //제자리로
            if(nowR == r && nowC == c && move !=0)
                break;

            //블랙홀
            if(map[nowR][nowC] == -1)
                break;

            nowR = nowR + moveR[dir];
            nowC = nowC + moveC[dir];
            move++;


            //벽에 부딪힘
            if(nowR<0 || nowR>=N || nowC<0 || nowC>=N) {
                nowR = nowR - moveR[dir];
                nowC = nowC - moveC[dir];

                if(dir == 0)
                    dir = 1;
                else if(dir == 1)
                    dir = 0;
                else if(dir == 2)
                    dir = 3;
                else if(dir == 3)
                    dir = 2;

                score++;

            }

            //블록에 부딪힘
            if(map[nowR][nowC] >= 1 && map[nowR][nowC] <=5) {
                int block = map[nowR][nowC];

                dir = dirChange[block][dir];
                score++;
                continue;
            }

            //웜홀
            if(map[nowR][nowC] > 5) {
                int block = map[nowR][nowC];
                int r1 = wormhole[block-6].get(0).r;
                int c1 = wormhole[block-6].get(0).c;

                int r2 = wormhole[block-6].get(1).r;
                int c2 = wormhole[block-6].get(1).c;

                if(nowR == r1 && nowC == c1) {
                    nowR = r2;
                    nowC = c2;
                }
                else {
                    nowR = r1;
                    nowC = c1;
                }
            }


        }

        return score;
    }

    static class Pos {
        int r, c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }

    }

}


//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class SWEA_5650 {
//
//    static int[] dx ={0,1,0,-1};
//    static int[] dy ={1,0,-1,0};
//    static int[][] map;
//    static HashMap<String,Boolean> blackhole;
//    static int res,N;
//    public static void main(String args[]) throws Exception{
//        //System.setIn(new FileInputStream("res/input.txt"));
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        int T = Integer.parseInt(br.readLine().trim());
//
//        for(int test_case = 1; test_case <= T; test_case++) {
//            N = Integer.parseInt(br.readLine().trim());
//            res = 0;
//            map = new int[N+2][N+2];
//            blackhole = new HashMap<>();
//            ArrayList<int[]>[] worm = new ArrayList[5];
//            for(int i = 0 ; i < 5; i++) worm[i] = new ArrayList<>();
//
//            for(int i = 1 ; i < N+1 ; i++){
//                st = new StringTokenizer(br.readLine().trim());
//                for(int j = 1 ; j < N+1 ; j++){
//                    map[i][j] = Integer.parseInt(st.nextToken());
//                    if(map[i][j] == -1){
//                        blackhole.put(i+","+j,true);
//                    }else if(6 <= map[i][j] && map[i][j] <= 10){
//                        worm[map[i][j]-6].add(new int[] {i,j});
//                    }
//                }
//            }
//
//            // 1. 먼저 0이면 dfs 돌기
//            for(int i = 1 ; i < N+1 ; i++){
//                for(int j = 1 ; j < N+1 ; j++){
//                    if(map[i][j] == 0) {
//                        for(int k = 0 ; k <= 3 ; k++){
//                            go(i,j,i,j,k,worm);
//                        }
//                    }
//                }
//            }
//
//            System.out.println("#"+test_case+" "+res);
//
//        }
//    }
//
//    private static void go(int startI, int startJ,int i, int j, int dir, ArrayList<int[]>[] worm) {
//
//        int score = 0;
//        int nx = i;
//        int ny = j;
//
//        while(true){
//
//            nx += dx[dir];
//            ny += dy[dir];
//
//            if((startI == nx && startJ == ny) || blackhole.containsKey(nx+","+ny)){
//                res = Math.max(res, score);
//                break;
//            }
//
//            if((nx <= 0 || nx > N || ny <= 0 || ny > N ) || map[nx][ny] == 5){
//                dir = changeDir2(dir,map[nx][ny]);
//                score++;
//                //res = Math.max(res,score*2+1);
//                //break;
//            }else if(map[nx][ny] >= 6 && map[nx][ny] <= 10 ){
//                int[] a = worm[map[nx][ny]-6].get(0);
//                int[] b = worm[map[nx][ny]-6].get(1);
//
//                if(nx == a[0] && ny == a[1]){
//                    nx = b[0];
//                    ny = b[1];
//                }else{
//                    nx = a[0];
//                    ny = a[1];
//                }
//            }else if(map[nx][ny] == 0){
//                continue;
//            }else{
//                dir = changeDir2(dir,map[nx][ny]);
//                score++;
//            }
//        }
//    }
//
//
//    private static void dfs(int startI, int startJ,int i, int j, int dir, int score, ArrayList<int[]>[] worm) {
//
//        int d=0;
//        if(dir == 1) d = 0;
//        else if(dir == 2) d = 1;
//        else if(dir == 4) d = 2;
//        else if(dir == 8) d = 3;
//
//        int nx = i+dx[d];
//        int ny = j+dy[d];
//
//        if((startI == nx && startJ == ny) || blackhole.containsKey(nx+","+ny)){
//            res = Math.max(res, score);
//            return;
//        }
//
//
//        if((nx <= 0 || nx > N || ny <= 0 || ny > N ) || map[nx][ny] == 5){
//            //dir = changeDir(dir,5);
//            //dfs(startI,startJ,nx,ny,dir,score+1);
//            res = Math.max(res,score*2+1);
//            return;
//        }else if(map[nx][ny] >= 6 && map[nx][ny] <= 10 ){
//            int[] a = worm[map[nx][ny]-6].get(0);
//            int[] b = worm[map[nx][ny]-6].get(1);
//
//            if(nx == a[0] && ny == a[1]){
//                dfs(startI,startJ,b[0],b[1],dir,score,worm);
//            }else{
//                dfs(startI,startJ,a[0],a[1],dir,score,worm);
//            }
//        }else if(map[nx][ny] == 0){
//            dfs(startI,startJ,nx,ny,dir,score,worm);
//        }else{
//            dir = changeDir(dir,map[nx][ny]);
//            dfs(startI,startJ,nx,ny,dir,score+1,worm);
//        }
//
//    }
//
//    private static int changeDir2(int dir, int block) {
//        // 1 2 4 8
//        if(block == 5){
//            if(dir == 0) return 2;
//            if(dir == 1) return 3;
//            if(dir == 2) return 0;
//            if(dir == 3) return 1;
//        }else if(block == 1){
//            if(dir == 0) return 2;
//            if(dir == 1) return 0; // 여기 위치
//            if(dir == 2) return 3;
//            if(dir == 3) return 1;
//        }else if(block == 2){
//            if(dir == 0) return 2;
//            if(dir == 1) return 3; // 여기 위치
//            if(dir == 2) return 1;
//            if(dir == 3) return 0;
//        }else if(block == 3){
//            if(dir == 0) return 1;
//            if(dir == 1) return 3; // 여기 위치
//            if(dir == 2) return 0;
//            if(dir == 3) return 2;
//        }else if(block == 4){
//            if(dir == 0) return 3;
//            if(dir == 1) return 2; // 여기 위치
//            if(dir == 2) return 0;
//            if(dir == 3) return 1;
//        }
//
//        return -1;
//    }
//
//    private static int changeDir(int dir, int block) {
//
//        if(block == 5){
//            if(dir == 1) return 4;
//            if(dir == 2) return 8;
//            if(dir == 4) return 1;
//            if(dir == 8) return 2;
//        }else if(block == 1){
//            if(dir == 1) return 4;
//            if(dir == 2) return 1; // 여기 위치
//            if(dir == 4) return 8;
//            if(dir == 8) return 2;
//        }else if(block == 2){
//            if(dir == 1) return 4;
//            if(dir == 2) return 8; // 여기 위치
//            if(dir == 4) return 2;
//            if(dir == 8) return 1;
//        }else if(block == 3){
//            if(dir == 1) return 2;
//            if(dir == 2) return 8; // 여기 위치
//            if(dir == 4) return 1;
//            if(dir == 8) return 4;
//        }else if(block == 4){
//            if(dir == 1) return 8;
//            if(dir == 2) return 4; // 여기 위치
//            if(dir == 4) return 1;
//            if(dir == 8) return 2;
//        }
//
//        return -1;
//    }
//}
//
