package two_point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int start = 0;
        int end = N-1;

        int[] res = new int[2];
        int now = Integer.MAX_VALUE;

        while(start<end){

            int temp = nums[start]+nums[end];

            if(Math.abs(temp) <= now){
                res[0] = nums[start];
                res[1] = nums[end];
                now = Math.abs(temp);
                if(temp >= 0){
                    end--;
                }else{
                    start++;
                }
            }else{
                if(temp >= 0){
                    end--;
                }else{
                    start++;
                }
            }
        }

        System.out.println(res[0] +" " + res[1]);


    }
}
