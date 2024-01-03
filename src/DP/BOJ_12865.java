package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {
    static int N,K;
    static int[][] dp = new int[101][100001];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        /*
           dp[i][j] = 처음부터 i번째까지의 물건을 살펴보고,
                    현재 배낭 용량이 j였을 때 배낭에 들어간 물건의 가치합의 최댓값
                    -> 즉, 현재 상황이 dp[i][j]일때
                            가능한 이전 상황은 dp[i-1][j]랑
                                dp[i-1][j-w]+v이다

        */
        for(int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            for(int j = 1; j <= K ; j++){
                if( j >= W ){ // i번째 물건을 넣을 수 있다면 ?
                    // 넣지 않을때랑 넣을 때랑, 둘 중 더 큰 것으로 초기화 (..?)
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-W]+V);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N][K]);

    }
}
