package Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2212 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        if (K >= N) {
            System.out.println(0);
            return;
        }

        int[] censor = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            censor[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(censor);

        /*
            집중국을 어디 설치해야할까?
                센서들 사이에 거리가 가장 먼곳을 경계로해야
                최소의 길이가 나온다
            인접센서들과의 거리를 측정하고 최대 거리인 K개를 제외한
                나머지 N-K개의 인접 거리의 합이 답이다.

         */

        int[] dif = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            dif[i] = censor[i + 1] - censor[i];
        }
        Arrays.sort(dif);

        int ans = 0;
        for (int i = 0; i < N - K; i++) {
            ans += dif[i];
        }

        System.out.println(ans);
    }

}




/*
    dfs 풀이 - 시간초과

 */

//public class Greedy.BOJ_2212 {
//    static int[] nums;
//    static int res = Integer.MAX_VALUE;
//    static int N,K;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        N = Integer.parseInt(br.readLine());
//        K = Integer.parseInt(br.readLine());
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        nums = new int[N];
//
//        for(int i = 0 ; i < N ; i++){
//            nums[i] = Integer.parseInt(st.nextToken());
//        }
//
//        Arrays.sort(nums);
//
//        dfs(0,0,0);
//
//        System.out.println(res);
//    }
//
//    // 현재 집중국 몇개 설치했는지, 현재까지 합이 몇인지, 현재 어디 센서인지
//    //
//    private static void dfs(int now, int sum, int cnt){
//        // 집중국 K개 넘으면 탈출
//        if(cnt > K) return;
//        // 현재 합이 res보다 크면 탈출
//        if(sum >= res) return;
//        // 현재 마지막 센서면 갱신 (애매)
//        if(now == N){
//            res = Math.min(res,sum);
//            return;
//        }
//
//        for(int i = now; i < N ; i++){
//            dfs(i+1,sum + nums[i]-nums[now],cnt+1);
//        }
//    }
//}
