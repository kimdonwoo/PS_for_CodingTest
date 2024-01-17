package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2629 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int chuCnt = Integer.parseInt(br.readLine());
        boolean[] dp = new boolean[15001];
        dp[0] = true;

        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < chuCnt ; i++){
            int chu = Integer.parseInt(st.nextToken());
            boolean[] temp = new boolean[15001];
            for(int j = 0 ; j < 15001;j++){
                if(dp[j]){
                    // true이면
                    if(j-chu >= 0) temp[j-chu] = true;
                    else temp[chu-j] = true;
                    if(j+chu < 15001) temp[j+chu] = true;
                }
            }
            for(int j = 0 ; j < 15001;j++){
                if(temp[j]){
                    dp[j] = temp[j];
                }
            }
        }

        int ballCnt = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < ballCnt ; i++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp > 15000){
                sb.append("N ");
                continue;
            }
            if(dp[temp]) sb.append("Y ");
            else sb.append("N ");
        }

        System.out.println(sb.toString());

    }
}
