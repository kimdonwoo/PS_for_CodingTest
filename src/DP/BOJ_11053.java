package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11053 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int maxVal = 1;

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        int[] dp = new int[N];
        Arrays.fill(dp,1);
        for(int i = 0 ; i < N ; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1 ; i < N ; i++){
            for(int j = 0 ; j < i ; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    maxVal = Math.max(maxVal,dp[i]);
                }
            }
        }
        System.out.println(maxVal);
    }
}
