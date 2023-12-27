package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20061 {
    static boolean[][] blue = new boolean[6][4];
    static boolean[][] green = new boolean[6][4];

    static int res = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // green 이동
            move(t,x,y,green);
            // blue 이동
            if(t == 1){
                y = 3-x;
            }else if(t == 2){
                t = 3;
                y = 3-x;
            }else if(t == 3){
                t = 2;
                y = 2-x;
            }
            move(t,x,y,blue);

            // 꽉찬 열 or 행 있는지 확인하고 점수
            checkScore(green);
            checkScore(blue);

            // 0,1 체크
            shiftMap(green);
            shiftMap(blue);
        }

        int count = 0;

        for(int i = 2 ; i < 6 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                if(green[i][j]) count++;
                if(blue[i][j]) count++;
            }
        }

        System.out.println(res);
        System.out.println(count);


    }

    private static void shiftMap(boolean[][] map) {
        // 1을 2번 체크한다.
        for(int k = 0 ; k < 2; k++){
            for(int i = 0 ; i < 4; i++){
                // 1에 true가 하나라도 있으면 이동
                if(map[1][i]){
                    for(int j = 4 ; j >= 0; j--){
                        map[j+1] = map[j];
                    }
                    break;
                }
            }
        }

        // 위에 2줄 비우기
        for(int i = 0 ; i < 2 ; i++){
            map[i] = new boolean[4];
        }
    }

    private static void checkScore(boolean[][] map) {
        // 둘다 2,3,4,5 체크

        for(int i = 2 ; i <= 5 ; i++){
            boolean check = false;
            for(int j = 0 ; j < 4 ; j++){
                if(!map[i][j]) check = true;
            }
            if(check) continue;
            // i층이 모두 true인 상황
            // 줄 없애고 점수 +1
            for(int j = 0 ; j < 4 ; j++){
                map[i][j] = false;
            }
            res++;
            // 윗라인( 0 ~ i-1) 땡기기
            for(int j = i ; j > 0 ; j--){
                map[j] = map[j-1];
            }
            map[0] = new boolean[4];

        }
    }

    private static void move(int t, int x, int y, boolean[][] map) {

        if(t == 1){
            int nx = 1;
            map[nx][y] = true;

            while(true){
                nx++;
                if(nx >= 6) break;
                if(map[nx][y]) break;
                map[nx-1][y] = false;
                map[nx][y] = true;
            }

        }else if(t == 2){
            // t = 2 y = 1
            // t = 2 y = 0
            int nx = 1;
            map[nx][y] = true;
            map[nx][y+1] = true;

            while(true){
                nx++;
                if(nx >= 6) break;
                if(map[nx][y] || map[nx][y+1]) break;
                map[nx-1][y] = false;
                map[nx-1][y+1] = false;

                map[nx][y] = true;
                map[nx][y+1] = true;
            }
        }else{
            int nx = 1;
            map[nx-1][y] = true;
            map[nx][y] = true;

            while(true){
                nx++;
                if(nx >= 6) break;
                if(map[nx][y]) break;
                map[nx-2][y] = false;
                map[nx][y] = true;
            }
        }

    }

    /*
            내려가기

                t = 1 : (x,y)
                    -> blue : (1,3-x)
                    -> green : (1,y)

                t = 2 : (x,y) (x,y+1)
                    -> blue : (0,3-x)
                              (1,3-x)
                    -> green : (1,y) (1,y+1)

                t = 3 : (x,y)
                        (x+1,y)
                    -> blue : (1,3-x)(1,2-x)
                    -> green : (1,y) (0,y)
             */
}

