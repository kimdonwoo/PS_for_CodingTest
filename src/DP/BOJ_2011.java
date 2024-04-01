package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int n = str.length();
        long[] dp = new long[n];

        if(n > 0 && !str.substring(0, 1).equals("0")){
            dp[0]++;
        }

        if(n > 1){
            if(!str.substring(1, 2).equals("0")){
                dp[1]+=dp[0];
            }
            int temp = Integer.parseInt(str.substring(0,2));
            if(temp >= 10 && temp <= 26){
                dp[1]++;
            }
        }

        for(int i = 2 ; i < n ; i++){
            if(!str.substring(i, i + 1).equals("0")){
                dp[i]+=dp[i-1];
            }
            int temp = Integer.parseInt(str.substring(i-1,i+1));
            if(temp >= 10 && temp <= 26){
                dp[i]+=dp[i-2];
            }
            dp[i]%= 1_000_000;
        }

        boolean check = false;

        for(int i = 0 ; i < n ; i++){
            if(dp[i] == 0){
                check = true;
                break;
            }
        }

        if(check){
            System.out.println(0);
        }else{
            System.out.println(dp[n-1] % 1_000_000);
        }

    }
}
