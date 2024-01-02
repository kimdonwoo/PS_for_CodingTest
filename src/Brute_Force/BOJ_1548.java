package Brute_Force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1548 {
    static int N;
    static long[] arr;
    static int res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        res = 2;
        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        if( N <= 2 ){
            System.out.println(N);
            return;
        }

        for(int i = 0 ; i < N ; i ++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 정렬
        Arrays.sort(arr);

        checkBFS();

        System.out.println(res);


    }
    /*
       이렇게 하면 N이 50일 때
       0 49
       0 48                 1 49
       0 47     1 48        1 48    2 49       // 겹치는 게 생김.. 흠..
       0 46 1 47            1 47 2 48
                2 48            3 49

        -> 이건 좀 조심해야할 문제같음

     */
    private static void checkBFS(){

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0,arr.length-1});
        int[] temp = new int[2];

        while(!q.isEmpty()){
            int[] now = q.poll();
            if(Arrays.equals(now,temp)) continue;
            temp = now.clone();

            if(now[1] - now[0] < 2) {
                break;
            }

            if(arr[now[0]]+arr[now[0]+1] > arr[now[1]]){
                // 성공
                res = Math.max(res,now[1] - now[0] + 1);
                break;
            }


            q.add(new int[] {now[0],now[1]-1});
            q.add(new int[]{now[0] + 1, now[1]});
        }
    }
}

/*
        for(int first=0; first<N-1; first++) {
			for(int third=first+2; third<N; third++) {
				if(arr[first] + arr[first+1] <= arr[third])break;
				max = Math.max(max, third-first+1);
			}
		}
 */
