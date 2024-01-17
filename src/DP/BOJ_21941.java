package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_21941 {
    public static class Node{
        public String str;
        public int score;

        public Node(String str, int score){
            this.str = str;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int res = Integer.MIN_VALUE;

        // TODO : String vs CharArray
        String S = br.readLine();
        int M = Integer.parseInt(br.readLine());
        ArrayList<Node> nodes = new ArrayList<>();
        int[] dp = new int[S.length()+1];

        for(int i = 0 ; i < M ;i ++){
            st = new StringTokenizer(br.readLine());
            nodes.add(new Node(st.nextToken(),Integer.parseInt(st.nextToken())));
        }


        for(int i = 0 ; i < S.length() ;i ++){

            for(Node n : nodes){
                if((i+1)-n.str.length() < 0){
                    // 범위 밖임
                    continue;
                }
                // 같으면 dp 값 더하기
                if(S.substring((i+1)-n.str.length(),i+1).equals(n.str)){
                    dp[i+1] = Math.max(dp[i+1],dp[(i+1) - n.str.length()]+n.score);
                }
            }

            dp[i+1] = Math.max(dp[i+1],dp[i]+1);
            res = Math.max(res,dp[i+1]);
        }

        System.out.println(res);

        /*
            문자열 S 삭제
                1. 문자열 A 존재하면 지우고 점수 겟 (여러개면 다 지움)
                2. S에서 문자 하나 지우고 점수 1점
                -> 최대 점수!

            만약 현재 문자열이 포함한다면 ? DFS
            흠...

            아니면 흠 .. DP

            M : 100흠..

            abc xyzx abc
            10  5  1 10

            dp : 최대점수저장?
            a b  c  x y z x a b c
          0 1 2 10 11


         */

    }
}
