package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20058 {
    static int N, Q, M;
    static int map[][];
    static int L[];
    static boolean visited[][];
    static int iceCnt; // 덩어리가 차지하는 칸 개수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken()); // 파이어스톰 시전 회수
        M = (int) Math.pow(2, N); // M=2^N

        map = new int[M][M];
        visited = new boolean[M][M];
        L = new int[Q];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++)
            L[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < Q; i++) { // Q번의 파이어스톰 실행
            FireStorm(L[i]);
        }

        // 1.남아있는 얼음 양
        int sum = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                sum += map[i][j];
            }
        }

        // 2. 가장 큰 덩어리가 차지하는 개수
        int res = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    iceCnt = 1;
                    dfs(i, j);
                    res = Math.max(res, iceCnt);
                }
            }
        }
        System.out.println(sum);
        System.out.println(res);
    }

    private static void dfs(int x, int y) { // 얼음 덩어리 개수
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= M || ny < 0 || ny >= M)
                continue;
            if (!visited[nx][ny] && map[nx][ny] > 0) {
                dfs(nx, ny);
                iceCnt++;

            }
        }
    }

    private static void FireStorm(int l) { // 파이어 스톰

        int K = (int) Math.pow(2, l); // K = 2^l

        int[][] copy = copy(map);
        for (int i = 0; i < M; i = i + K) { // KxK 크기의 격자 90도 회전
            for (int j = 0; j < M; j = j + K) {
                rotate(i, j, K, copy);
            }
        }
        decreaseIce(); // 회전 끝나면 얼음 줄이기 시작
    }

    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };

    private static void decreaseIce() { // 얼음 줄이기
        List<int[]> list = new ArrayList<>();

        for (int x = 0; x < M; x++) {
            for (int y = 0; y < M; y++) {
                int cnt = 0; // 얼음이 있는 칸 수
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= M || ny < 0 || ny >= M)
                        continue;
                    if (map[nx][ny] > 0)
                        cnt++;
                }
                if (cnt < 3) // 얼음있는 칸이 3칸 미만이면
                    list.add(new int[] { x, y });
            }

        }
        for (int i = 0; i < list.size(); i++) {
            int[] cur = list.get(i);
            if (map[cur[0]][cur[1]] > 0) {
                map[cur[0]][cur[1]] -= 1;
            }
        }
    }

    private static void rotate(int x, int y, int k, int[][] copy) { // 시계방향 90도 회전

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                map[x + j][y + k - 1 - i] = copy[x + i][y + j];
            }
        }
    }

    private static int[][] copy(int[][] map) {
        int data[][] = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++)
                data[i][j] = map[i][j];
        }
        return data;
    }
}

//public class BOJ_20058 {
//    static int N,Q,NN;
//    static int[][] map;
//    static boolean[][] visit;
//    static int[] dx= {0,0,1,-1};
//    static int[] dy= {1,-1,0,0};
//
//    static int[] movedx = {0,1,1,0};
//    static int[] movedy = {0,0,1,1};
//    static int ice_sum = 0 , ice_max = Integer.MIN_VALUE , ice_com;
//
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        Q = Integer.parseInt(st.nextToken());
//        NN = (int)Math.pow(2,N);
//        map = new int[NN][NN];
//        visit = new boolean[NN][NN];
//
//        for(int i = 0 ; i < NN ; i ++){
//            st = new StringTokenizer(br.readLine());
//            for(int j = 0 ; j < NN ; j ++){
//                map[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        st = new StringTokenizer(br.readLine());
//
//        for(int i = 0 ; i < Q ; i ++) {
//            int L = Integer.parseInt(st.nextToken());
//
//            // 1. 격자 내부 회전
//            rotate(L);
//
//            // 2. 얼음 녹이기
//            melting();
//        }
//
//        // 3. 남은 얼음 합
//        for(int i = 0 ; i < NN ; i++){
//            for(int j = 0 ; j < NN ; j++){
//                if(!visit[i][j] && map[i][j] > 0){
//                    ice_com = 0;
//                    dfs(i,j);
//                    ice_max = Math.max(ice_max,ice_com);
//                }
//            }
//        }
//
//        for(int i = 0 ; i < NN ; i ++){
//            for(int j = 0 ; j < NN ; j ++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
//
//        // 4. 출력
//        System.out.println(ice_sum);
//        System.out.println(ice_max);
//
//    }
//
//    private static void dfs(int x, int y) {
//        visit[x][y] = true;
//        ice_sum += map[x][y];
//        ice_com++;
//
//        for(int i = 0 ; i < 4; i++){
//            int nx = x+dx[i];
//            int ny = y+dy[i];
//
//            if(nx < 0 || nx >= NN || ny < 0 || ny >= NN) continue;
//            if(!visit[nx][ny] && map[nx][ny] > 0){
//                dfs(nx,ny);
//            }
//
//        }
//    }
//
//    private static void melting() {
//        int[][] temp = new int[NN][NN];
//
//        for(int i = 0 ; i < NN ; i ++){
//            for(int j = 0 ; j < NN ; j ++){
//                if(map[i][j] > 0){
//                    for(int k = 0 ; k < 4 ; k++){
//                        int nx = i+dx[k];
//                        int ny = j+dy[k];
//
//                        if(nx < 0 || nx >= NN || ny < 0 || ny >= NN) continue;
//                        temp[nx][ny]++;
//                    }
//                }
//            }
//        }
//
//
//        for(int i = 0 ; i < NN ; i ++){
//            for(int j = 0 ; j < NN ; j ++){
//                if(temp[i][j] < 3 && map[i][j] > 0) map[i][j]--;
//            }
//        }
//    }
//
//    private static void rotate(int l) {
//        // 회전 시키기
//        // 만약 2면
//        // Math.pow(2,l) Math.pow(2,N-l)
//        // Math.pow(2,1) == 2 -> 이거를 Math.pow(2,2) 번씩
//        // Math.pow(2,2) == 4 ->
//        /*
//            l == 1
//            -> 4번씩
//            i = 0,1,2,3
//            x = 0,2,4,6
//
//            l == 2
//            -> 2번씩
//            i = 0,1
//            x = 0,4
//
//            ==> (int) Math.pow(2,l)
//         */
//        int nowX = 0, nowY = 0;
//        for(int i = 0 ; i < (int)Math.pow(2,N-l) ; i++){
//            for(int j = 0 ; j < (int)Math.pow(2,N-l) ; j++){
//                move((int) Math.pow(2,l)*i,(int) Math.pow(2,l)*j,(int)Math.pow(2,l));
//            }
//        }
//    }
//
//    private static void move(int x, int y , int r) {
//
//        int temp[][] = new int[r/2][r/2];
//
//        for(int i = 0 ; i < r/2 ; i++){
//            for(int j = 0 ; j < r/2 ; j++){
//                temp[i][j] = map[x+i][y+j];
//            }
//        }
//
//        for(int k = 0 ; k < 3; k++){
//            for(int i = 0 ; i < r/2 ; i++){
//                for(int j = 0 ; j < r/2 ; j++){
//                    //movedx[k]; movedy[k];
//                    map[x+i+(movedx[k]*(r/2))][y+j+(movedy[k]*(r/2))] =
//                            map[x+i+(movedx[k+1]*(r/2))][y+j+(movedy[k+1]*(r/2))];
//                }
//            }
//        }
//
//        for(int i = 0 ; i < r/2 ; i++){
//            for(int j = 0 ; j < r/2 ; j++){
//                map[x+i][y+(r/2)+j] = temp[i][j];
//            }
//        }
//
//    }
//}
