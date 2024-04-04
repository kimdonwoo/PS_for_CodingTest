package DP;

import java.util.*;

public class BOJ_5582 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String str1 = scan.nextLine();
        String str2 = scan.nextLine();
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        int max = 0;
        for(int i = 1; i <= str1.length(); i++) {
            for(int j = 1; j <= str2.length(); j++) {
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        System.out.println(max);
    }
}

/*
    이전의 모든 상황을 고려하는게 아니라
    새로 들어온 게 같을때만 이전보다 +1 하면 됨


 */
