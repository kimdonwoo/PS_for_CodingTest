package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499 {
    static int N,M,K;
    static int[] now;
    static int[][] map;
    static int[] dice;
    // 동 서 남 북
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        now = new int[2];

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        now[0] = Integer.parseInt(st.nextToken());
        now[1] = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dice = new int[6];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < K ; i++){
           int dir = Integer.parseInt(st.nextToken());

           int nx = now[0]+dx[dir-1];
           int ny = now[1]+dy[dir-1];

           if(nx >= 0 && nx < N && ny >= 0 && ny < M){
               // 갈 수 잇는 곳이라면
               now[0] = nx;
               now[1] = ny;

               int temp;

               // 1. 방향에 따라 val 회전 시키고
               if(dir == 1){ // 동
                   temp = dice[0];
                   dice[0] = dice[2];
                   dice[2] = dice[5];
                   dice[5] = dice[3];
                   dice[3] = temp;
               }else if(dir == 2){ // 서
                   temp = dice[0];
                   dice[0] = dice[3];
                   dice[3] = dice[5];
                   dice[5] = dice[2];
                   dice[2] = temp;
               }else if(dir == 3){ // 남
                   temp = dice[0];
                   dice[0] = dice[4];
                   dice[4] = dice[5];
                   dice[5] = dice[1];
                   dice[1] = temp;
               }else if(dir == 4){ // 북
                   temp = dice[0];
                   dice[0] = dice[1];
                   dice[1] = dice[5];
                   dice[5] = dice[4];
                   dice[4] = temp;
               }

               // 2. dice[0]이랑 map 값이랑 비교
               if(map[now[0]][now[1]] == 0){
                   map[now[0]][now[1]] = dice[0];
               }else{
                   dice[0] = map[now[0]][now[1]];
                   map[now[0]][now[1]] = 0;
               }

               // 3. dice[5] 출력
               System.out.println(dice[5]);
           }
        }
    }
}
