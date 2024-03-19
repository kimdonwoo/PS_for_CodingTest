package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2285 {
    static long[] citys;
    static long[] person;
    static int N;
    static boolean res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 마을 위치, 사람 수
        citys = new long[N];
        person = new long[N];

        StringTokenizer st;

        long left = Long.MAX_VALUE;
        long right = Long.MIN_VALUE;
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());

            long c = Long.parseLong(st.nextToken());
            long p = Long.parseLong(st.nextToken());

            citys[i] = c;
            person[i] = p;

            left = Math.min(left,c);
            right = Math.max(right,c);

        }

        left--;
        right++;

        // 조건 만족하는 최소 mid를 찾아야함
        while(left+1 < right){
            long mid = (left+right)/2;

            if(check(mid)){
                // 성공이면
                right = mid;
                if(res) break;
            }else{
                left = mid;
            }
        }

        if(!res){
            // right이랑 right-- 비교하기
            if(Math.abs(calculate(right-1)) >= Math.abs(calculate(right))){
                System.out.println(right-1);
            }else{
                System.out.println(right);
            }

        }else{
            System.out.println(right);

        }
    }

    private static boolean check(long now) {

        long cnt = calculate(now);
        if(cnt == 0) res = true;
        if(cnt >= 0) return true;
        return false;
    }

    private static long calculate(long now){
        long cnt = 0;

        for(int i = 0 ; i < N ; i++){
            cnt+=(now-citys[i])*person[i];
        }

        return cnt;
    }
}
