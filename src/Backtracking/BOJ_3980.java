package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3980 {
    static int[][] map;
    static int[] select;
    static int res;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        map = new int[11][11];
        select = new int[11];

        for(int t = 0 ; t < T ; t++){
            res = Integer.MIN_VALUE;

            for(int i = 0 ; i < 11 ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < 11 ; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            backtracking(0,0);

            System.out.println(res);
        }

    }

    private static void backtracking(int idx , int sum){
        // 선택 완료
        if(idx == 11){
            res = Math.max(res,sum);
            return;
        }

        // 선택
        for(int i = 0 ; i < 11 ; i++){
            if(map[idx][i] != 0 && select[i] == 0){
                select[i] = map[idx][i];
                backtracking(idx+1,sum+map[idx][i]);
                select[i] = 0;
            }
        }
    }
}
