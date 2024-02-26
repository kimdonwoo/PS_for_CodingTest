import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2473 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] nums = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            nums[i] = Integer.parseInt(st. nextToken());
        }

        Arrays.sort(nums);

        long[] select = new long[3];
        long res = Long.MAX_VALUE;

        // i는 무조건 선택한 상황
        for(int i = 0 ; i < N-2 ; i++){

            int left = i+1;
            int right = N-1;

            while(left < right){

                long temp = nums[i]+nums[left]+nums[right];

                if(res > Math.abs(temp)){
                    select[0] = nums[i];
                    select[1] = nums[left];
                    select[2] = nums[right];
                    res = Math.abs(temp);

                }

                if(temp > 0) right--;
                else left++;

            }
        }

        System.out.println(select[0]+" "+select[1]+" "+select[2]);

    }
}
