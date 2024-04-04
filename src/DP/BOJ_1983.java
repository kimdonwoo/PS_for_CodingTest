package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1983 {
    static int[][] memo;
    static ArrayList<Integer> up;
    static ArrayList<Integer> down;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        up = new ArrayList<>();
        down = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp != 0) up.add(temp);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp != 0) down.add(temp);
        }

        int upZeroCnt = N - up.size();
        int downZeroCnt = N - down.size();
        memo = new int[upZeroCnt+1][downZeroCnt+1];

        for (int i = 0; i < N; i++) {
            for (int r = upZeroCnt; r >= 0; r--) {
                for (int c = downZeroCnt; c >= 0; c--) {
                    check(i, r, c);
                }
            }
        }

        int maxVal = Integer.MIN_VALUE;
        for (int[] row : memo) {
            for (int val : row) {
                maxVal = Math.max(maxVal, val);
            }
        }
        System.out.println(maxVal);
    }

    // 여기서 i r c 가 뭘까
    // r은 up의 0 사용 갯수
    // c은 down의 0 사용 갯수
    // memo[][]
    static void check(int i, int r, int c) {
        int maxy = 0;

        // r이랑 c가 i보다 클 수 없다
        // i-r이 뭘까? i-c가 뭘까?
        if ( 0<= i-r && i - r < up.size() && 0 <= i-c && i - c < down.size()) {
            // 새로 들어온거 0 사용 x
            maxy = memo[r][c] + up.get(i - r) * down.get(i - c);
            if (0 < r) maxy = Math.max(maxy, memo[r - 1][c]);
            if (0 < c) maxy = Math.max(maxy, memo[r][c - 1]);
            memo[r][c] = maxy;
        }
    }
}
