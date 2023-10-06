package BFS_DFS;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 동시 다발적으로 발생하는 경우 큐를 사용한 BFS로 해결!

public class BOJ_7576 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int n, m;
    static int[][] map;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    // 초기 큐 세팅
                    q.add(new int[]{i, j});
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        while (!q.isEmpty()) {
            // 익은 토마토를 하나씩 꺼낸다.
            int[] t = q.poll();
            int x = t[0];
            int y = t[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                // 주변에 익지 않은게 있으면
                if (map[nx][ny] == 0) {
                    // *** 토마토에 익은 날짜 값을 넣어주는 것!!!
                    map[nx][ny] = map[x][y] + 1;
                    // 새로 익은 토마토 큐에 넣는다 (그 이전 토마토들은 넣을 필요가 없음)
                    q.add(new int[]{nx, ny});
                }
            }
        }

        // 큐 탈출 후
        int max = Integer.MIN_VALUE;

        // 안익은게 존재하는지 check
        if (checkZero()) {
            return -1;
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 여기서
                    if (max < map[i][j]) {
                        max = map[i][j];
                    }
                }
            }

            return max - 1;
        }


    }

    private static boolean checkZero() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0)
                    return true;
            }
        }
        return false;
    }
}

//public class BOJ_7576 {
//
//    static int res = 0;
//    static int[][] tomato;
//    static boolean[][] change;
//    static int M,N;
//    static int[] dx ={1,-1,0,0};
//    static int[] dy ={0,0,1,-1};
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//
//        tomato = new int[M][N];
//
//
//        for(int i = 0 ; i < M ; i++){
//            st = new StringTokenizer(br.readLine());
//            for(int j = 0 ; j < N ; j++){
//                tomato[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        while(true){
//            // 탈출조건 : tomato가 모두 1 or -1인지 확인
//            boolean flag = false;
//
//            for(int i = 0 ; i < M ; i++){
//                for(int j = 0 ; j < N ; j++){
//                    if(tomato[i][j] == 0){
//                        flag = true;
//                        break;
//                    }
//                }
//                if(flag) break;
//            }
//            if(!flag) break;
//
//            // res 증가
//            res++;
//
//            // 익은거 체크
//            change = new boolean[M][N];
//
//            for(int i = 0 ; i < M ; i++){
//                for(int j = 0 ; j < N ; j++){
//                    if(tomato[i][j] == 1){
//                        ripe(i,j);
//                    }
//                }
//            }
//
//            // change 부분중에 tomato 0인 부분이 없으면 -1 출력
//            // tomato 익은 부분 변화 시키기
//
//            boolean flag2 = false;
//
//            for(int i = 0 ; i < M ; i++){
//                for(int j = 0 ; j < N ; j++){
//                    // true면 익자나
//                    if(change[i][j] && tomato[i][j] == 0){
//                        tomato[i][j] = 1;
//                        flag2 = true;
//                    }
//                }
//            }
//
//            if(!flag2){
//                res = -1;
//                break;
//            }
//
//        }
//
//        System.out.println(res);
//    }
//
//    static void ripe(int x, int y){
//        for(int i = 0 ; i < 4; i++){
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//
//            if(nx >= 0 && nx < M&& ny >= 0 && ny <N){
//                change[nx][ny] = true;
//            }
//        }
//    }
//
//}
