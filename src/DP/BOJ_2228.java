package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2228 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int [] arr = new int[n+1];
        int [] sum = new int[n+1];
        int [][] dp = new int[n+1][m+1];

        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(br.readLine());
            sum[i] = sum[i-1]+arr[i];
        }

        for(int i=0; i<=n; i++){
            for(int j=1; j<=m; j++){
                dp[i][j] = -32768*101;
            }
        }

        dp[1][1] = arr[1];
        for(int i=2; i<=n; i++){
            for(int j=1; j<=m; j++){
                dp[i][j] = dp[i-1][j];

                int min=0;
                if(j==1){
                    min=-1;
                }

                for(int k=i-2; k>=min; k--){
                    if(k<0){
                        dp[i][j] = Math.max(dp[i][j],sum[i]);
                    }
                    else{
                        dp[i][j] = Math.max(dp[i][j], dp[k][j-1]+sum[i]-sum[k+1]);
                    }
                }
            }
        }

        System.out.println(dp[n][m]);

    }
}


//public class DP.BOJ_2228 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//
//        int[] nums = new int[N];
//        int sum = 0;
//        for(int i = 0 ; i < N ; i++){
//            nums[i] = Integer.parseInt(br.readLine());
//            sum += nums[i];
//        }
//
//        int start = 0;
//        int end = 0;
//        // 결과
//        int res = Integer.MIN_VALUE;
//        int tempSum = nums[0];
//
//        if(M == 1){
//            res = sum;
//        } else if (M == 2) {
//            int t = 0;
//            for(int i = 0 ; i < N-1 ; i++){
//                t+=nums[i];
//                res = Math.max(res,t);
//                res = Math.max(res,sum-t);
//            }
//        } else {
//            while(start < N){
//
//                if(res < tempSum) {
//                    res = tempSum;
//                }
//
//                if(end-start+1 < N-M+1 && end < N-1){
//                    if(nums[start] < 0){
//                        tempSum-=nums[start];
//                        start++;
//                    }else{
//                        end ++;
//                        tempSum+=nums[end];
//                    }
//                }else{
//                    tempSum-=nums[start];
//                    start++;
//                }
//
//            }
//
//        }
//
//        System.out.println(res);
//
//        // 1 2 3 4 5 6 7 8
//        // 다음 end 값이 양수면 넓혀도 됨
//        // 현재 start 값이 음수이면 좁혀야함
//    }
//}
