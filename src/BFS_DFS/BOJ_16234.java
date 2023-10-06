package BFS_DFS;

import java.util.*;
import java.io.*;

public class BOJ_16234 {
    static int n, l, r;
    static int[][] map;
    static ArrayList<int[]> list;
    static boolean[][] visit;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 }; // 동 남 서 북

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());

    }

    static int move() { // 인구이동 발생일 수 구하기
        int result = 0;

        while (true) {
            visit = new boolean[n][n];

            boolean isMove = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (!visit[i][j]) {
                        int sum = bfs(i, j);// 연합찾기
                        // 의문 : 여기서 map 값을 바꾸면 인접값이 바뀌는거 아닌가?
                        // -> 아니네 visit 때문에 안바뀌는구나! (이런 디테일 중요!)

                        if (list.size() > 1) {
                            changePopulation(sum);
                            isMove = true;
                        }
                    }

                }
            }

            if (!isMove)
                return result;
            result++;
        }

    }

    static void changePopulation(int sum) {
        int avg = sum / list.size();
        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i)[0];
            int y = list.get(i)[1];
            map[x][y] = avg;
        }
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        list = new ArrayList<>();

        q.add(new int[] { x, y });
        list.add(new int[] { x, y });
        visit[x][y] = true;
        int sum = map[x][y];

        while (!q.isEmpty()) {
            int[] t = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = t[0] + dx[i];
                int ny = t[1] + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (!visit[nx][ny]) {
                        int tmp = Math.abs(map[t[0]][t[1]] - map[nx][ny]);
                        if (tmp >= l && tmp <= r) {

                            visit[nx][ny] = true;
                            q.add(new int[] { nx, ny });
                            list.add(new int[] { nx, ny });
                            sum += map[nx][ny];
                        }

                    }
                }
            }
        }
        return sum;
    }

}






//public class BOJ_16234 {
//    static int N,L,R;
//    static int[][] map;
//    static boolean[][] visit;
//    static Queue<int[]> queue = new LinkedList<>();
//    static Queue<int[]> queue2 = new LinkedList<>();
//    static int sum;
//
//    static int[] dx = {0,0,1,-1};
//    static int[] dy = {1,-1,0,0};
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        L = Integer.parseInt(st.nextToken());
//        R = Integer.parseInt(st.nextToken());
//
//        map = new int[N][N];
//
//        for(int i = 0 ; i < N ; i++){
//            st = new StringTokenizer(br.readLine());
//            for(int j = 0 ; j < N ; j++){
//                map[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        int res =0;
//
//        while(true){
//
//            res++;
//
//            sum = 0;
//            visit = new boolean[N][N];
//
//            for(int i = 0 ; i < N ; i++){
//                for(int j = 0 ; j < N ; j++){
//                    // 방문 안했으면
//                    if(!visit[i][j]){
//                        sum+=map[i][j];
//                        visit[i][j] = true;
//                        queue.add(new int[]{i,j});
//                        queue2.add(new int[]{i,j});
//                        bfs(i,j);
//                    }
//
//                    // 값 수정?
//                }
//            }
//
//
//        }
//
//        /*
//            N*N 땅
//
//            1. 국경선 공유하는 두 나라의 인구차이가 L 이상 R 이하면 하루 인구이동
//            2. 모두 연다음에 한번에 인구이동 시작(연합) -> BFS인가?
//            3. 연합끼리의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
//
//            -> 인구이동 며칠동안 발생하는지 출력 (최대 2000)
//
//            [구현방법]
//            1. 국경선을 공유하는지 체크 배열
//                - 큐 2개로 해결?
//                - boolean visit도 필요
//                - 50 30 20 40
//         */
//
//
//    }
//
//    static void bfs(int x, int y){
//
//        while(!queue.isEmpty()){
//            int[] now = queue.poll();
//
//            for(int i = 0 ; i < 4 ; i++){
//                int nx = now[0]+dx[i];
//                int ny = now[1]+dy[i];
//                int val = Math.abs(map[nx][ny]-map[now[0]][now[1]]);
//
//                if(nx >= 0 && ny >= 0 && nx < N && nx < N){
//                    // 방문안하고 공유가능
//                    if(!visit[nx][ny] && val >= L && val <= R){
//                        sum+=map[nx][ny];
//                        visit[nx][ny] = true;
//                        queue.add(new int[] {nx,ny});
//                        queue2.add(new int[] {nx,ny});
//                    }
//                }
//            }
//        }
//
//    }
//
//
//}
