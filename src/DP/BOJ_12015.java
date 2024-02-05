package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_12015 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> dp = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            int temp = Integer.parseInt(st.nextToken());

            if(dp.isEmpty()){
                dp.add(temp);
            }else{
                if(dp.get(dp.size()-1) < temp){
                    dp.add(temp);
                }else{
                    // 여기서 이분탐색
                    // dp 돌면서 temp 값보다 큰 값들 중에 젤 작은 값 ->lower bound?
                    int start = -1;
                    int end = dp.size();
                    while(start + 1 < end){
                        int mid = (start+end)/2;
                        if(dp.get(mid) >= temp){
                            end = mid;
                        }else{
                            start = mid;
                        }
                    }
                    // end 값을 바꾸기
                    dp.set(end,temp);
                }
            }
        }

        System.out.println(dp.size());

    }
}


