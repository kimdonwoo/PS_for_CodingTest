package two_point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_15961 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken()); // ?
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        HashMap<Integer,Integer> hm = new HashMap<>();
        hm.put(C,1);

        int[] nums = new int[N];
        for(int i = 0 ; i < N ; i++){
            nums[i] = Integer.parseInt(br.readLine());
            if(i < K){
                if(!hm.containsKey(nums[i])){
                    hm.put(nums[i],1);
                }else{
                    hm.put(nums[i],hm.get(nums[i])+1);
                }
            }
        }

        int res = hm.size();

        int start = 0;
        int end = K-1;

        while(start < N){

            // 뒤로 한칸씩 밀기
            if(hm.get(nums[start]) == 1){
                hm.remove(nums[start]);
            }else{
                hm.put(nums[start],hm.get(nums[start])-1);
            }
            start++;
            end++;
            if(!hm.containsKey(nums[end%N])){
                hm.put(nums[end%N],1);
            }else{
                hm.put(nums[end%N],hm.get(nums[end%N])+1);
            }

            res = Math.max(res,hm.size());

        }

        System.out.println(res);
        /*
            1. k개의 접시 연속해서 먹으면 할인
            2. ..?

            가능한 한 다양한 종류의 초밥
            회전 초밥 벨트에 놓인 접시의 수 N (3_000_000)
            초밥의 가짓수 d (1~d) (3_000)
            연속해서 먹는 접시의 수 k, (3_000)
            쿠폰 번호 c - 무조건 하나

            k 사이즈의 window
            start, end
            현재 가짓수를 HashMap으로 ?

         */

    }
}
