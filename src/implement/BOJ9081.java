package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9081 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            System.out.println(solution(br.readLine()));
        }
    }

    static String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();

        char[] ary = s.toCharArray();
        int idx1 = -1, idx2 = 0;

        for (int i = n - 1; i > 0; i--) {
            if (ary[i - 1] < ary[i]) {
                idx1 = i - 1;
                break;
            }
        }

        if (idx1 == -1) return s;

        for (int i = n - 1; i >= 0; i--) {
            if (ary[idx1] < ary[i]) {
                idx2 = i;
                break;
            }
        }

        char tmp = ary[idx1];
        ary[idx1] = ary[idx2];
        ary[idx2] = tmp;

        Arrays.sort(ary, idx1 + 1, n);
        for (char c : ary) sb.append(c);

        return sb.toString();
    }
}