package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11054 {
    static int[] nums;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int res = 0;

        for(int goal = 0 ; goal < N ; goal++){

            ArrayList<Integer> dp = new ArrayList<>();

            // 이분탐색으로 최장증가수열 구현해야 이문제는 풀수있다
            for(int i = 0 ; i <= goal ; i++){
                // 비어있거나 아니면 더 크면
                if(dp.isEmpty() || dp.get(dp.size()-1) < nums[i]){
                    dp.add(nums[i]);
                }else{
                    // 그런게 아니면 이분탐색
                    // dp에서 이분탐색으로 nums[i] 값보다 크거나 같은 값중 최솟값 찾기
                    // FFFTTT
                    int idx = binarySearch(-1, dp.size(), nums[i],dp);
                    dp.set(idx,nums[i]);
                }
            }

            ArrayList<Integer> dp2 = new ArrayList<>();
            for(int i = goal ; i < N ; i++){
                if(dp2.isEmpty() || dp2.get(dp2.size()-1) > nums[i]){
                    dp2.add(nums[i]);
                }else{
                    // dp에서 이분탐색으로 nums[i] 값보다 작거나 같은 값중 최댓값 찾기
                    // FFFTTT
                    int idx = binarySearch2(-1, dp2.size(), nums[i],dp2);
                    dp2.set(idx,nums[i]);
                }
            }


            if(res < dp.size()+dp2.size()-1){
                res = dp.size()+dp2.size()-1;
            }

        }

        System.out.println(res);

    }

    static int binarySearch(int left, int right, int key,ArrayList<Integer> dp) {

        while(left+1 < right) {
            int mid = (left+right)/2;

            if(dp.get(mid) >= key) {
                // FFFTTT 조건 만족해서
                // mid가 최선의 답임
                right = mid;
            }else {
                left = mid;
            }
        }
        return right;
    }

    static int binarySearch2(int left, int right, int key,ArrayList<Integer> dp) {

        while(left+1 < right) {
            int mid = (left+right)/2;

            if(dp.get(mid) <= key) {
                // 만약 key보다 크면 값을 줄여야함
                right = mid;
            }else {
                // 조건 만족
                left = mid;
            }
        }
        return right;
    }
}
