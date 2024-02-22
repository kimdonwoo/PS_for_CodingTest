package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20181 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long sum = arr[0];
        long[] dp = new long[N + 1]; // dp[i] 는 i까지의 최대 탈피 에너지
        int left = 0, right = 1;

        while (right <= N) {
            if (sum >= K) {
                while (sum >= K) {
                    dp[right] = Math.max(dp[right], dp[left] + sum - K);
                    sum -= arr[left];
                    left++;
                }
            } else {
                dp[right] = Math.max(dp[right], dp[right - 1]);

                if (right == N)
                    break;

                sum += arr[right];
                right++;
            }
        }

        System.out.println(dp[N]);
    }

}

//public class BOJ_20181 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//
//        HashMap<Integer,Long>[] DP = new HashMap[N+1];
//        for(int i = 0 ; i < N+1 ; i++){
//            DP[i] = new HashMap<>();
//        }
//
//        DP[0].put(0,0);
//
//        st = new StringTokenizer(br.readLine());
//        for(int i = 1 ; i <= N ; i++){
//
//            int now = Integer.parseInt(st.nextToken());
//
//            for(int key : DP[i-1].keySet()){
//                if(key > 0){
//                    // 만족도가 0보다 크면 무조건 먹는다
//                    int temp = key + now;
//
//                    if(temp >= K){
//                        if(DP[i].containsKey(0)){
//                            if(DP[i].get(0) < DP[i-1].get(key)+ (temp - K)){
//                                DP[i].put(0,DP[i - 1].get(key) + (temp - K));
//                            }
//                        }else {
//                            DP[i].put(0, DP[i - 1].get(key) + (temp - K));
//                        }
//                    }else{
//                        if(DP[i].containsKey(temp)){
//                            if(DP[i].get(temp) < DP[i-1].get(key)){
//                                DP[i].put(temp,DP[i-1].get(key));
//                            }
//                        }else{
//                            DP[i].put(temp,DP[i-1].get(key));
//                        }
//                    }
//
//                }else{
//                    // 이전 만족도가 0
//                    // 안먹거나
//                    if(DP[i].containsKey(key)){
//                        if(DP[i].get(key) < DP[i-1].get(key)){
//                            DP[i].put(key,DP[i-1].get(key));
//                        }
//                    }else {
//                        DP[i].put(key,DP[i-1].get(key));
//                    }
//
//                    // 먹거나
//                    int temp = key + now;// 새로운 만족도
//
//                    if(temp >= K){
//                        if(DP[i].containsKey(0)){
//                            if(DP[i].get(0) < DP[i-1].get(key)+(temp - K)){
//                                DP[i].put(0,DP[i - 1].get(key) + (temp - K));
//                            }
//                        }else {
//                            DP[i].put(0, DP[i - 1].get(key) + (temp - K));
//                        }
//                    }else{
//                        if(DP[i].containsKey(temp)){
//                            if(DP[i].get(temp) < DP[i-1].get(key)){
//                                DP[i].put(temp,DP[i-1].get(key));
//                            }
//                        }else{
//                            DP[i].put(temp,DP[i-1].get(key));
//                        }
//                    }
//                }
//            }
//
//        }
//
//        if(DP[N].isEmpty()){
//            System.out.println(0);
//        }else{
//            Long maxVal = Long.MIN_VALUE;
//            for(int key : DP[N].keySet()){
//                maxVal = Math.max(maxVal,DP[N].get(key));
//            }
//            System.out.println(maxVal);
//        }
//
//
//    }
//}
