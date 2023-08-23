package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503 {

    //  북
    // 서 동
    //  남

    // 북 동 남 서 -> 반시계
    static int[] dx ={-1,0,1,0};
    static int[] dy ={0,1,0,-1};
    static int count = 0;
    static int[][] map;
    static boolean[][] clean;
    static int N, M;

    public static void main(String[] args)throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        clean = new boolean[N][M];

        st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());


        for(int i = 0 ; i < N ; i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        DFS(x,y,d);

        System.out.println(count);

    }

    public static void DFS(int x , int y , int dir){

        // 젤 처음 로봇 위치 세팅
        if(!clean[x][y]){
            clean[x][y] = true;
            count++;
        }


        // (dir+3)%4
        for(int i = 0 ; i < 4 ; i++){
            dir = (dir+3)%4;
            int next_x = x + dx[dir];
            int next_y = y + dy[dir];

            if(next_x > 0 && next_x < N-1 && next_y > 0 && next_y < M-1){
                // 방이면서 청소안한곳만 이동
                if(map[next_x][next_y] == 0 && !clean[next_x][next_y]){
                    DFS(next_x,next_y,dir);
                    // 어느곳 가면 종료
                    return;
                }
            }
        }

        if(x+dx[(dir+2)%4] > 0 && x+dx[(dir+2)%4] < N-1 && y+dy[(dir+2)%4] > 0 && y+dy[(dir+2)%4] < M-1){ // 범위 이내
            if(map[x+dx[(dir+2)%4]][y+dy[(dir+2)%4]] == 0 ){ // 후진칸이 길이면 이동가능
                DFS(x+dx[(dir+2)%4],y+dy[(dir+2)%4],dir);
            }
        }
        //System.exit(0);

    }
}
