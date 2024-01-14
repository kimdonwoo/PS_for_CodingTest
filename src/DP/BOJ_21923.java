package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21923 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        long[][] UpDp = new long[N][M];
        long[][] DownDp = new long[N][M];

        for(int i = N-1 ; i >= 0 ; i --){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(i == 0) UpDp[i][j] = map[i][j];
            }
        }

        // 상승 dp 생성
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(i == 0 && j == 0) continue;
                if(j==0){
                    UpDp[i][j] = UpDp[i-1][j]+map[i][j];
                }else if(i==0){
                    UpDp[i][j] = UpDp[i][j-1]+map[i][j];
                }else{
                    UpDp[i][j] = Math.max(UpDp[i-1][j],UpDp[i][j-1])+map[i][j];
                }
            }
        }

        for(int i = N-1 ; i >= 0 ; i --){
            for(int j = 0 ; j < M ; j ++){
                if(j==0 && i ==N-1){
                    DownDp[i][j] = UpDp[i][j]+map[i][j];
                    continue;
                }
                if(j==0){
                    DownDp[i][j] = Math.max(UpDp[i][j],DownDp[i+1][j])+map[i][j];
                }else if(i == N-1){
                    DownDp[i][j] = Math.max(DownDp[i][j-1],UpDp[i][j])+map[i][j];
                }
                else{
                    DownDp[i][j] = Math.max(DownDp[i+1][j],DownDp[i][j-1]);
                    DownDp[i][j] = Math.max(DownDp[i][j],UpDp[i][j])+map[i][j];
                }
            }
        }

        System.out.println(DownDp[0][M-1]);

    }
}
