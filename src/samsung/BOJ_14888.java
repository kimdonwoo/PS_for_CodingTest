package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888 {
    static int N;
    static int[] arr;

    static int minValue = Integer.MAX_VALUE;
    static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] op = new int[4];
        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < 4 ;i++){
            op[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(1,op , arr[0]);

        System.out.println(maxValue);
        System.out.println(minValue);

    }

    // op : + - * /
    /*
        현재 값 기억하기 * or 연산자 순서 기억하기

     */

    private static void backtracking(int idx, int[] op, int now) {
        // 정지 조건
        if(idx == N){
            maxValue = Math.max(maxValue,now);
            minValue = Math.min(minValue,now);
            return;
        }

        // 찾기
        for(int i = 0 ; i < 4; i++){
            if(op[i] > 0){
                op[i]--;
                if(i == 0){
                    backtracking(idx+1,op,now+arr[idx]);
                }else if(i == 1){
                    backtracking(idx+1,op,now-arr[idx]);
                }else if(i == 2){
                    backtracking(idx+1,op,now*arr[idx]);
                }else if(i == 3){
                    backtracking(idx+1,op,now/arr[idx]);
                }
                op[i]++;
            }
        }

    }
}
