package shortest_distance;

import java.io.*;


public class BOJ_2224 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[][] arr = new boolean[52][52];

        while (n-->0) {
            String cur = br.readLine();
            arr[atoi(cur.charAt(0))][atoi(cur.charAt(5))] = true;
        }

        for (int k = 0; k < 52; k++) {
            for (int i = 0; i < 52; i++) {
                for (int j = 0; j < 52; j++) {
                    if (i==j || i==k || k==j || arr[i][j]) continue;
                    if (arr[i][k] && arr[k][j]) arr[i][j] = true;
                }
            }
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                if (i == j || !arr[i][j]) continue;
                cnt++;
                sb.append(itoa(i)).append(' ').append('=').append('>').append(' ').append(itoa(j)).append('\n');
            }
        }

        System.out.println(cnt);
        System.out.println(sb.toString());
    }

    private static int atoi(char c) {
        if (c>='a') return c-'a'+26;
        return c-'A';
    }
    private static char itoa(int idx) {
        if (idx<26) return (char)('A'+idx);
        return (char)('a'+(idx-26));
    }
}
