package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234_ {
    static int n, l, r;
    static int[][] map;
    static ArrayList<int[]> list;
    static boolean[][] visit;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 }; // 동 남 서 북

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
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
                        int sum = bfs(i, j);

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


    //    static int N, L, R;
//    static int[][] map;
//    static int[][] check;
//    static ArrayList<int[]> list;
//
//    static int[] dx ={0,1};
//    static int[] dy ={1,0};
//    static int sum , num ;
//
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        R = Integer.parseInt(st.nextToken());
//        L = Integer.parseInt(st.nextToken());
//
//        map = new int[N][N];
//
//        // 맵 초기화
//        for(int i = 0 ; i < N ; i++){
//            st = new StringTokenizer(br.readLine());
//            for(int j = 0 ;j < N ; j++){
//                map[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        int count = 0;
//
//        while(true){
//
//            // 1. map을 돌면서 오른쪽,아래만 체크하면서 국경선이 인접한지 체크한다
//            // 2. 만약 인접하다면 check[][]
//            // 흠.. 즉 하나의 덩어리를 만들어야함
//            // 만약 본인 값이 0이 아니면 오,아래 체크할 떄, 그 값을 넣어줌
//            // 만약 본인 값이 0인데 오른쪽이랑 아래가 변수하나 ++ 해서 값에 넣어주면됨
//            check = new int[N][N];
//            check();
//
//            // 3. map에다가 인구 이동 계산
//
//            // 4. 인구 이동이 있었으면 count++
//            //      (check 전부다 0이면)없었으면 break
//
//            boolean res = false;
//
//            for(int i = 0 ; i < N ; i++){
//                for(int j = 0 ; j < N ; j++){
//                    if(check[i][j] != 0) {
//                        res = true;
//                        break;
//                    }
//                }
//            }
//
//            if(res) break;
//
//            move();
//
//            count++;
//
//        }
//
//        System.out.println(count);
//
//    }
//
//    public static void check(){
//
//        int temp = 1;
//
//        for(int i = 0 ; i < N ; i++){
//            for(int j = 0 ;j < N ; j++){
//                //map[i][j];
//                for(int k = 0 ;k < 2 ; k++){
//                    int next_x = i+dx[k];
//                    int next_y = j+dy[k];
//
//                    if(next_x >= N || next_y >= N) break;
//                    int minus = map[i][j]-map[next_x][next_y] >= 0 ? map[i][j]-map[next_x][next_y] : map[next_x][next_y] - map[i][j];
//
//                    if(minus >= L && minus <= R){ // 인접하다면
//                        if(check[i][j] == 0){ // 0 이면
//                            check[i][j] = temp;
//                            check[next_x][next_y] = temp;
//                            temp++;
//
//                        }else{ // 0 아닐때
//                            check[next_x][next_y]  = check[i][j];
//
//                        }
//                    }
//
//                }
//            }
//        }
//
//    }
//
//    public static void move() {
//
//        // 인구 이동 (map 값 변경)
//        for(int i = 0 ; i < N ; i++){
//            for(int j = 0 ; j < N ; j++){
//                if(check[i][j] != 0){
//                    list = new ArrayList<>();
//                    sum = map[i][j];
//
//                    DFS(i,j);
//                    list.add(new int[] {i,j});
//                    check[i][j]= 0;
//
//                    change(sum/list.size());
//                }
//            }
//        }
//    }
//
//    public static void change(int nn){
//
//        for(int[] arr : list){
//            map[arr[0]][arr[1]] = nn;
//        }
//
//    }
//
//    public static void DFS(int x, int y){
//
//        for(int k = 0 ; k < 2 ; k++){
//            int next_x = x+dx[k];
//            int next_y = y+dy[k];
//
//            if(next_x >= N || next_y >= N) break;
//            if(check[next_x][next_y] == check[x][y]) {
//                num++;
//                sum+=map[next_x][next_y];
//                DFS(next_x, next_y);
//                list.add(new int[] {next_x,next_y});
//                check[next_x][next_y] = 0;
//            }
//        }
//
//    }
//
//
//}
