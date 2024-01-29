package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_14698 {
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Long n = Long.parseLong(st.nextToken());
        Long div = 1000000007L;

        for(Long i = 0L ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            Long num = Long.parseLong(st.nextToken());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            Long res = 1L;

            for(Long j = 0L ; j < num ; j++){
                pq.add(Long.parseLong(st.nextToken()));
            }

            while(!pq.isEmpty()){
                if(pq.size() <= 1){
                    break;
                }

                Long a = pq.poll();
                Long b = pq.poll();

                Long mul = a*b;
                pq.add(mul);

                res = res*(mul%div)%div;


            }

            System.out.println(res%div);

        }

    }

}
