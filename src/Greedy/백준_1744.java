package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class 백준_1744 {
    public static void main(String args[]) throws IOException {

        int res = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minus_zero = new PriorityQueue<>();
        PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0 ; i < n ; i ++){
            int num =Integer.parseInt(br.readLine());
            if(num > 0 ){
                plus.add(num);
            }else{
                minus_zero.add(num);
            }
        }

        while(!plus.isEmpty()){
            if(plus.size() >= 2){
                // 1이면 더하기
                // 2 1 , 1 1
                int tmp = plus.poll();
                int tmp2 = plus.poll();
                if(tmp == 1 || tmp2 == 1){
                    res+=(tmp+tmp2);
                    continue;
                }
                res+= (tmp*tmp2);
            }else if (plus.size() == 1){
                res+=plus.poll();
            }
        }

        while(!minus_zero.isEmpty()){
            if(minus_zero.size() >= 2){
                res+= minus_zero.poll() * minus_zero.poll();
            }else if (minus_zero.size() == 1){
                res+=minus_zero.poll();
            }
        }

        System.out.println(res);

    }
}








