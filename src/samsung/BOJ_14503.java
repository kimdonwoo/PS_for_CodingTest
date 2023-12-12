package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {
    static int[][] map;
    static int N,M;
    static int[] robot = new int[3];
    static int res = 0;
    /*
        0 북
        1 동
        2 남
        3 서
     */
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 3 ; i++){
            robot[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            boolean check = false;

            // 1. 현재 칸 청소 안되었으면 청소
            if(map[robot[0]][robot[1]] == 0){
                map[robot[0]][robot[1]] = 2;
                res++;
            }

            // 2. 4방향 확인
            for(int i = 1 ; i < 5; i++){
                // 반시계 돌리기
                int d = (robot[2]+4-i)%4;
                int nx = robot[0] + dx[d];
                int ny = robot[1] + dy[d];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M ) {
                    if (map[nx][ny] == 0) { // 청소안되어있으면 이동
                        robot[0] = nx;
                        robot[1] = ny;
                        robot[2] = d;
                        check = true;
                        break;
                    }
                }
            }

            // 3. 후진
            if(!check){
                int d = (robot[2]+2)%4;
                int nx = robot[0] + dx[d];
                int ny = robot[1] + dy[d];

                if(map[nx][ny] == 1){
                    System.out.println(res);
                    System.exit(0);
                }else{
                    robot[0] = nx;
                    robot[1] = ny;
                }
            }

        }

    }


}
