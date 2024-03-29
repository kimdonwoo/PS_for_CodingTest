package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long[] house = new long[N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            house[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(house);

        long low = 1;
        long high = house[N-1]-house[0];
        long ans = Long.MIN_VALUE;

        while(low <= high){
            long mid = (low+high)/2;

            long now = house[0]+mid;
            int cnt = 1;

            for(int i = 1 ; i < N ; i++){
                // 공유기 설치
                if(house[i] >= now){
                    now = house[i]+mid;
                    cnt++;
                }

                // 더 많은 공유기가 필요
                // 즉 현재 mid거리로 필요한 cnt가 넘 많음
                // -> mid를 늘려야한다 -> left++
                if(C < cnt){
                    break;
                }
            }

            if(cnt < C){
                high = mid-1;
            }else{
                low = mid+1;
                ans = Math.max(ans,mid);
            }
        }

        System.out.println(ans);

    }
}
