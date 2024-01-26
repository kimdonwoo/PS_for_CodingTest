package two_point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22862 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i  < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int oddCnt = 0;
        if(nums[0]%2 == 1) oddCnt++;
        int res = Integer.MIN_VALUE;

        while(start < N){

            if(end < N-1){
                if(oddCnt <= K){
                    res = Math.max(res,end-start+1-oddCnt);
                    end++;
                    if(nums[end]%2 == 1) oddCnt++;
                }else{
                    if(nums[start]%2 == 1) oddCnt--;
                    start++;
                }
            }else{
                // 무조건 start++
                if(oddCnt <= K){
                    res = Math.max(res,end-start+1-oddCnt);
                }
                if(nums[start]%2 == 1) oddCnt--;
                start++;
            }

        }

        System.out.println(res);

        /*
            가장 긴 짝수 연속한 부분 수열

            수열 S에서 원하는 위치에 있는 수를 골라 최대 K번 삭제 가능

            짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이

            N : 1_000_000
            K : 100_000

            흠..

            1 2 4 5 6 7 8
            1 1 1 2 2 3 3

            K = 2 -> 5/7 삭제
            DP / 이분 탐색 / 투포인터

            start <= ~ <=end
            1 2 3 4 5 6 7 8
            s
              e

            k = 2
            만약 S~E안에
                홀 수 갯수가 k보다 많으면 start ++
                홀 수 갯수가 k보다 작으면 end ++
                홀 수 갯수가 k랑 같으면 길이 비교 갱신후, start++
         */



    }
}
