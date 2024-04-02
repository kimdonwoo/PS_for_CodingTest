package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1461 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> rightPQ = new PriorityQueue<>((o1,o2) -> o2-o1);
        PriorityQueue<Integer> leftPQ = new PriorityQueue<>((o1,o2) -> o2-o1);

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int now = Integer.parseInt(st.nextToken());
            if( now > 0){
                rightPQ.add(now);
            }else{
                leftPQ.add(Math.abs(now));
            }
        }

        int footCnt = 0;
        int maxDist = 0;
        if(rightPQ.size() > 0) maxDist = Math.max(maxDist,rightPQ.peek());
        if(leftPQ.size() > 0) maxDist = Math.max(maxDist,leftPQ.peek());

        while(!rightPQ.isEmpty()){
            int now = rightPQ.poll();

            footCnt+=now;

            if(rightPQ.size() > M-1){
                for(int i = 0 ; i < M-1 ; i++) rightPQ.poll();
            }else{
                break;
            }

        }

        while(!leftPQ.isEmpty()){
            int now = leftPQ.poll();

            footCnt+=now;

            if(leftPQ.size() > M-1){
                for(int i = 0 ; i < M-1 ; i++) leftPQ.poll();
            }else{
                break;
            }
        }

        System.out.println(footCnt*2 - maxDist);

    }
}

/*




 */