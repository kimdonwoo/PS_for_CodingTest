package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 백준_1715 {
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0 ; i < n ; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }
        int a = 0 , b = 0, res = 0;

        while(pq.size() > 1){
            a = pq.poll();
            b = pq.poll();
            res+=(a+b);
            pq.add(a+b);
        }

        System.out.println(res);


    }
}
