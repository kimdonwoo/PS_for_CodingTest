package two_point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

public class BOJ_1644 {
    public static void main (String[] args) throws IOException{

        /*
            연속된 소수들의 합

            N이

            1. 먼저 연속된 소수를 찾는다.
            2. 투포인터 or 누적합으로 가능한 경우 찾기
                -> 누적합 후 노가다

         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] sosoo = new boolean[N+1];
        for(int i = 2 ; i*i <= N ; i++){
            if(!sosoo[i]){
                for(int j = i*i ; j <= N ; j+=i){
                    sosoo[j] = true;
                }
            }
        }

        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 2 ; i <= N ; i++){
            if(!sosoo[i]){
                nums.add(i);
            }
        }


        int left = 0;
        int right = 0;
        int res = 0;
        int now = 2;

        while(left < nums.size() && right < nums.size()){
            if(now == N){
                res++;
                now -= nums.get(left);
                left++;
            }else if(now > N){
                now -= nums.get(left);
                left++;
            }else{
                right++;
                if(right >= nums.size()) break;
                now += nums.get(right);
            }
        }

        System.out.println(res);

    }
}
