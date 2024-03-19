package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13975 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T --> 0){

            int K = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < K ; i++){
                pq.add(Long.parseLong(st.nextToken()));
            }

            Long res = 0L;

            while(pq.size() > 1){
                Long temp = pq.poll() + pq.poll();

                pq.add(temp);
                res+=temp;
            }

            System.out.println(res);

        }

    }
}
