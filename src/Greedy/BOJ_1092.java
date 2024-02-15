package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1092 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] crane = new int[N];
        int maxCrane = Integer.MIN_VALUE;
        for(int i = 0 ; i < N ; i++){
            crane[i] = Integer.parseInt(st.nextToken());
            maxCrane = Math.max(maxCrane,crane[i]);
        }

        int maxBox = Integer.MIN_VALUE;
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> boxes = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++){
            boxes.add(Integer.parseInt(st.nextToken()));
            maxBox = Math.max(maxBox,boxes.get(i));
        }

        if(maxBox > maxCrane){
            System.out.println(-1);
            return;
        }

        Arrays.sort(crane);
        Collections.sort(boxes);

        int cnt = 0;

        while(!boxes.isEmpty()){
            cnt ++;
            for(int i = N-1 ; i >= 0 ; i--){
                // 이분탐색으로 temp 보다 작은 값중 최대값 찾기
                int idx = searchIdx(crane[i], boxes);

                // idx 삭제
                if(idx >= 0){
                    boxes.remove(idx);
                }

            }
        }

        System.out.println(cnt);
    }

    private static int searchIdx(int goal, ArrayList<Integer> boxes) {
        // 이분탐색으로 TTTFF
        // goal보다 작은 값들 중 최댓값 찾기
        int left = -1;
        int right = boxes.size();

        while(left + 1 < right){
            int mid = (left+right)/2;

            if(boxes.get(mid) <= goal){
                left = mid;
            }else{
                right = mid;
            }
        }

        return left;
    }
}
