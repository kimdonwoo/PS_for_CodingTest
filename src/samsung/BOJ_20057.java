package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20057 {

    static int N, amountOfOutSand = 0;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] numOfMove = {1, 1, 2, 2};
    static int[][] dxOfSand = {{-2, -1, -1, -1, 0, 1, 1, 1, 2}, {0, 1, 0, -1, 2, 1, 0, - 1, 0},
                                {2, 1, 1, 1, 0, -1, -1, -1, -2}, {0, -1, 0, 1, -2, -1, 0, 1, 0}};
    static int[][] dyOfSand = {{0, -1, 0, 1, -2, -1, 0, 1, 0}, {-2, -1, -1, -1, 0, 1, 1, 1, 2},
                                {0, 1, 0, -1, 2, 1, 0, -1, 0}, {2, 1, 1, 1, 0, -1, -1, -1, -2}};
    static int[] ratioOfSand = {2, 10, 7, 1, 5, 10, 7, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tornado(N / 2, N / 2);

        System.out.println(amountOfOutSand);
    }

    private static void tornado(int x, int y) {

        int curX = x;
        int curY = y;

        while (true) {
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < numOfMove[d]; i++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];

                    if (nx < 0 || ny < 0 || N <= nx || N <= ny) return;

                    int sand = map[nx][ny];
                    map[nx][ny] = 0;
                    int spreadTotal = 0;

                    for (int s = 0; s < 9; s++) {
                        int sandX = nx + dxOfSand[d][s];
                        int sandY = ny + dyOfSand[d][s];
                        int spreadAmount = (sand * ratioOfSand[s]) / 100;

                        if (sandX < 0 || sandY < 0 || N <= sandX || N <= sandY) amountOfOutSand += spreadAmount;
                        else map[sandX][sandY] += spreadAmount;

                        spreadTotal += spreadAmount;
                    }

                    int alphaX = nx + dx[d];
                    int alphaY = ny + dy[d];
                    int amountOfAlpha = sand - spreadTotal;

                    if (alphaX < 0 || alphaY < 0 || N <= alphaX || N <= alphaY) amountOfOutSand += amountOfAlpha;
                    else map[alphaX][alphaY] += amountOfAlpha;

                    curX = nx;
                    curY = ny;

                }
            }

            for (int i = 0; i < 4; i++) {
                numOfMove[i] += 2;
            }
        }
    }
}
