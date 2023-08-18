package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12840 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[][] ground = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i + a - 1 < n && j + b + c - 1 < m) {
                    res = Math.min(res, one(ground, i, j, a, b, c, res));
                }
                if (i + a + b - 1 < n && j + c + a - 1 < m) {
                    res = Math.min(res, two(ground, i, j, a, b, c, res));
                }
                if (i + a + c - 1 < n && j + b + a - 1 < m) {
                    res = Math.min(res, three(ground, i, j, a, b, c, res));
                }
            }
        }

        System.out.println(res);
    }

    static int one(int[][] grounds, int x, int y, int a, int b, int c, int res) {
        int sum = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b + c; j++) {
                if (sum > res) return Integer.MAX_VALUE;
                sum += grounds[x + i][y + j];
            }
        }
        return sum;
    }

    static int two(int[][] grounds, int x, int y, int a, int b, int c, int res) {
        int sum = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < c; j++) {
                if (sum > res) return Integer.MAX_VALUE;
                sum += grounds[x + i][y + j];
            }
        }
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                if (sum > res) return Integer.MAX_VALUE;
                sum += grounds[x + a + i][y + c + j];
            }
        }
        return sum;
    }

    static int three(int[][] grounds, int x, int y, int a, int b, int c, int res) {
        int sum = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (sum > res) return Integer.MAX_VALUE;
                sum += grounds[x + i][y + j];
            }
        }
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < a; j++) {
                if (sum > res) return Integer.MAX_VALUE;
                sum += grounds[x + a + i][y + b + j];
            }
        }
        return sum;
    }
}