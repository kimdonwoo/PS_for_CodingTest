package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_16234 {
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

                    if (!visit[i][j]) { // 이미 방문했던곳은 연합 체크한 곳이라 방문할 필요 x
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