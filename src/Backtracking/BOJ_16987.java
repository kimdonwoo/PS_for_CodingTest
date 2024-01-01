package Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16987 {
    static int N ;
    static int res = 0;
    static int[][] eggs;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        backtracking(0);

        System.out.println(res);
    }

    private static void backtracking(int idx){
        if(idx == N){
            // 만약 모든 계란으로 다 치면 깨진 계란 수 비교

            int cnt = 0;
            for(int[] egg : eggs){
                if(egg[0] <= 0) cnt++;
            }

            res = Math.max(res,cnt);

            return;
        }

        // 손으로 든게 깨졌을 때
        if(eggs[idx][0] <= 0){
            backtracking(idx+1);
            return;
        }

        boolean check = false;

        for(int i = 0 ; i < N ; i++){
            if(i == idx) continue;
            if(eggs[i][0] > 0){
                // 안깨진 놈
                eggs[i][0] -= eggs[idx][1];
                eggs[idx][0] -= eggs[i][1];

                backtracking(idx+1);

                eggs[i][0] += eggs[idx][1];
                eggs[idx][0] += eggs[i][1];
                check = true;
            }

        }

        // 전부다 깨진 상황일때 !
        if(!check) backtracking(idx+1);
    }
}
