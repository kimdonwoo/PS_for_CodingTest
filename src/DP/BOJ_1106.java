package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1106 {
    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // 각 도시에서 비용으로 얻을 수 있는 고객수는 100명 이하
        // 적어도 C명을 늘여야하므로 그보다 더 큰 고객을 들였을 때의 비용이 더 작을 수 있음
        int dp[] = new int[c+100];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            for(int j=customer; j<c+100; j++) {
                // 돈에 정수배 만큼 투자할 수 있으므로
                // cost(현재 비용) + dp[j-customer] 로 j명의 고객을 늘린다.
                if (dp[j-customer] != Integer.MAX_VALUE) // 무한이라면 아직 갱신되지 않은 값이므로 고객을 확보할 수 없다
                    dp[j] = Math.min(dp[j], cost+dp[j-customer]);
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i=c; i<c+100; i++) {// 최소 c명을 확보해야 하므로 dp[c]부터 탐색
            answer = Math.min(answer, dp[i]);
        }
        System.out.println(answer);

    }
}
    //    static int C,N;
//    static int res = Integer.MAX_VALUE;
//    static int[][] cities;
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        C = Integer.parseInt(st.nextToken());
//        N = Integer.parseInt(st.nextToken());
//        cities = new int[N][2];
//
//        for(int i = 0 ; i < N ; i ++){
//            st = new StringTokenizer(br.readLine());
//            cities[i][0] = Integer.parseInt(st.nextToken());
//            cities[i][1] = Integer.parseInt(st.nextToken());
//        }
//
//        Arrays.sort(cities,(o1,o2)->{
//            /*
//                정렬 방식 -> 투자해야하는 돈의 최솟값
//                1. 고객 한명당 홍보비용이 더 싼 애들
//                2. 같으면 전체 비용 더 싼것 부터 or 고객 많은 것
//                // [0] : 비용 , [1] : 고객 수
//             */
//            double a = (double)o1[0]/(double)o1[1];
//            double b = (double)o2[0]/(double)o2[1];
//
//            //System.out.println(a + " : " + b);
//
//            if(a == b){
//                return o2[1]-o1[1];
//                //return o1[0]-o2[0];
//            }else if(a < b){
//                return -1; // a-b
//            }else{
//                return 1;
//            }
//        });
//
//        //for (int[] c : cities) System.out.println("-> " + c[0] + " : " + c[1]);
//
//        int now = 0; // 현재 선택한 인원수
//        int money = 0; // 현재 사용한 돈
//
//        for(int i = 0 ; i < cities.length ; i++){
//            // cities[][0] : 비용 , [1] : 고객 수
//            // C - now : 남은 고객 수
//            if ((C - now) / cities[i][1] > 0){
//                money += ((C - now) / cities[i][1]) * cities[i][0];
//                now += ((C - now) / cities[i][1]) * cities[i][1];
//            }
//
//            if(now == C){
//                res = Math.min(res,money);
//                break;
//            }else if(now < C){
//                res = Math.min(res,money+cities[i][0]);
//            }
//        }
//
//        System.out.println(res);
//    }
//}
