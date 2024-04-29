package two_point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N-1;

        int now = Integer.MAX_VALUE;
        int[] res = new int[2];

        while(left < right){

            int temp = nums[left] + nums[right];

            if(now > Math.abs(temp)){
                now = Math.abs(temp);
                res[0] = nums[left];
                res[1] = nums[right];
            }

            if(temp == 0){
                break;
            }else if(temp > 0){
                right--;
            }else{
                left++;
            }
        }

        for(int r : res){
            System.out.print(r+" ");
        }
    }
}
