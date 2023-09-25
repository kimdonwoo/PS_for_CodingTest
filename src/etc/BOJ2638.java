package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

        /*
            N*M map 존재
            치즈입장에서 4방향중 2방향이 외부면 녹음

            모두 녹아 없어질때까지 걸리는 시간 출력

            (N : 100 / M : 100)
            치즈 있는부분이 1

            시간 : 100*100 map : 4방향
            4 00 00 * 50 = 2 000 000 ㄱㄴ
         */

public class BOJ2638 {
    static int N, M, res=0;
    static boolean[][] map;
    static boolean[][] temp;
    static boolean[][] air;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        // 1. 제일 처음 치즈 있는 부분에 대해서 map에 입력을 받음
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    // 치즈있으면
                    map[i][j] = true;
                }
            }
        }

        /*
            map의 (0,0)에서 DFS 돌리기
            그리고 방문한 곳만 표시

            체크시에 map을 확인하는게 아니고 dfs로 외부 공기만 확인해야함

            map ()
            temp ()

         */

        while (true) {
            temp = new boolean[N][M];
            air = new boolean[N][M];
            boolean isEnd = false;

            DFS(0,0);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 2. map돌면서 true 나오면 4 방향 확인하기 -> 없어질 치즈 temp에 체크
                    if (map[i][j]) {
                        // 만약 녹는애면
                        if (meltCheck(i, j)) {
                            isEnd = true;
                            temp[i][j] = true;
                        }
                     }
               }
            }
             // 3. temp에 값존재하면 map에 치즈 없애줌
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(temp[i][j]) map[i][j] = false;
                }
            }

            if(!isEnd) break;
            res++;
        }

        System.out.println(res);

    }

    // 해당 치즈 녹으면 true
    static boolean meltCheck(int x, int y){

        int count = 0;

        for(int i = 0 ; i < 4 ; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                // 공기 false 면 카운터
                if(air[nx][ny]) count++;
            }

        }

        if(count >= 2) return true;
        return false;

    }

    static void DFS(int x, int y){

        for(int i = 0 ; i < 4 ; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                // air true는 이미 방문한 곳이니깐
                if(!air[nx][ny] && !map[nx][ny]){
                    air[nx][ny] = true;
                    DFS(nx,ny);
                }
            }

        }

    }

}
