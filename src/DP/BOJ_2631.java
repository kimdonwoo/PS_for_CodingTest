package DP;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2631 {

    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] input = new int[N];

        for(int i=0;i<N;i++) input[i] = scan.nextInt();

        int[] dp = new int[N];
        Arrays.fill(dp,1);

        int ans = 0;
        for(int i=1;i<N;i++){
            for(int j=0;j<i;j++){
                if(input[i]>input[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(N-ans);

    }
}
//public class BOJ_2631 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int N = Integer.parseInt(br.readLine());
//
//        int[] now = new int[N+1];
//        int[] dp = new int[N+1];
//        // i번째아이의 위치 저장
//        int[] children = new int[N+1];
//        int res = Integer.MIN_VALUE;
//
//
//        for(int i = 1 ; i <= N ; i++){
//            now[i] = Integer.parseInt(br.readLine());
//            children[now[i]] = i;
//        }
//
//        for(int i = N ; i > 0 ; i--){
//            for(int j = children[i]+1 ; j <= N ; j++){
//                if(now[children[i]] < now[j]){
//                    dp[i] = Math.max(dp[i],dp[j]+1);
//                    res = Math.max(res,dp[i]);
//                }
//            }
//        }
//
//        System.out.println(N-1-res);
//    }
//}
