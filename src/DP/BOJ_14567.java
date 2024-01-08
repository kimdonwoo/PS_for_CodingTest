package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14567 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] dp = new int[N+1];
        Arrays.fill(dp,1);

        int[][] subj = new int[M][2];

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            subj[i][0] = Integer.parseInt(st.nextToken());
            subj[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(subj,(o1,o2)->{
            if(o1[0] == o2[0]){
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        for(int i = 0 ; i < M ; i++){
            dp[subj[i][1]] = Math.max(dp[subj[i][1]],dp[subj[i][0]]+1);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= N ; i++){
            sb.append(dp[i]+" ");
        }

        System.out.println(sb.toString());
    }
}