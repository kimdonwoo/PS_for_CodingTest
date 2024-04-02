package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9082 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            int N = Integer.parseInt(br.readLine());
            int cnt = 0;

            String temp = br.readLine();
            String bomb = br.readLine();

            int[] nums = new int[N];
            for(int i = 0 ; i < N ; i++){
                nums[i] += Integer.parseInt(temp.substring(i,i+1));
                if(bomb.substring(i,i+1).equals("*")){
                    cnt++;
                    if( i != 0) nums[i-1]--;
                    nums[i]--;
                    if( i != N-1) nums[i+1]--;
                }
            }

            // 0이랑 N-1일때
            if(bomb.substring(0,1).equals("#") && nums[0] > 0 && nums[1] > 0){
                nums[0]--;
                nums[1]--;
                cnt++;
            }

            if(bomb.substring(N-1,N).equals("#") && nums[N-2] > 0 && nums[N-1] > 0){
                nums[N-2]--;
                nums[N-1]--;
                cnt++;
            }

            // 만약 0이 *이면
            for(int i = 1 ; i < N-1 ; i++){
                if(bomb.substring(i,i+1).equals("#")){
                    if(nums[i-1] > 0 && nums[i] > 0 && nums[i+1] > 0){
                        cnt++;
                        nums[i-1]--;
                        nums[i]--;
                        nums[i+1]--;
                    }
                }
            }

            System.out.println(cnt);

        }
    }
}

/*
 지뢰의 수 최댓값 ?




 */