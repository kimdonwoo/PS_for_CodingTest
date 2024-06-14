package Random;

import java.util.*;
import java.io.*;

public class BOJ_5073 {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true){

            st = new StringTokenizer(br.readLine());

            int[] nums = new int[3];

            nums[0] = Integer.parseInt(st.nextToken());
            nums[1] = Integer.parseInt(st.nextToken());
            nums[2] = Integer.parseInt(st.nextToken());
            if(nums[0] == 0) break;

            // 오름차순
            Arrays.sort(nums);

            // Equilateral, Isosceles, Scalene, Invalid
            if(nums[2] >= nums[0] + nums[1]){
                sb.append("Invalid").append("\n");
            }else if(nums[0] == nums[1] && nums[1] == nums[2]){
                sb.append("Equilateral").append("\n");
            }else if(nums[0]!=nums[1] && nums[1]!=nums[2] && nums[0]!=nums[2]){
                sb.append("Scalene").append("\n");
            }else{
                sb.append("Isosceles").append("\n");
            }

        }

        System.out.println(sb);

    }
}
