import java.io.*;
import java.util.*;

public class prac {
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<int[]> goal;
    static int count = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int n, m;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visited = new boolean[n][n];
        goal = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            goal.add(new int[]{x - 1, y - 1});
            map[x - 1][y - 1] = 2;
        }

        int[] start = goal.get(0);
        int[] end = goal.get(m - 1);
        visited[start[0]][start[1]] = true;
        DFS(1, start[0], start[1], end[0], end[1]);

        System.out.println(count);
    }

    static void DFS(int depth, int now_x, int now_y, int goal_x, int goal_y) {

        if (now_x == goal_x && now_y == goal_y) {
            if (depth == m) { // 찾았으면
                count++;
                return;
            }
        }

        for (int i = 0; i < 4; i++) {
            int next_x = now_x + dx[i];
            int next_y = now_y + dy[i];

            if (next_x >= 0 && next_x < n && next_y >= 0 && next_y < n) {
                if (!visited[next_x][next_y] && map[next_x][next_y] == 0) {
                    visited[next_x][next_y] = true;
                    DFS(depth + 1, next_x, next_y, goal_x, goal_y);
                    visited[next_x][next_y] = false;
                }
            }
        }
    }
}
