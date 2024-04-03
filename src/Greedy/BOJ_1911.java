package Greedy;

import java.util.*;
import java.io.*;

public class BOJ_1911 {

    public static class Water{
        int in;
        int out;
        Water(int in, int out){
            this.in = in;
            this.out = out;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        PriorityQueue<Water> waters = new PriorityQueue<>((o1,o2)->{
            if(o1.in == o2.in) return o1.out-o2.out;
            return o1.in-o2.in;
        });

        for(int i = 0 ; i < N ;i++){
            st = new StringTokenizer(br.readLine());
            int in = Integer.parseInt(st.nextToken());
            int out = Integer.parseInt(st.nextToken());

            waters.add(new Water(in,out));
        }

        int now = 0;
        int cnt = 0;

        while(!waters.isEmpty()){
            Water w = waters.poll();

            if(w.out < now) continue;

            int in = w.in;
            int out = w.out;
            if(in >= now){
                now = in+((out-in)/L)*L;
                cnt += (out-in)/L;
                if((out-in)%L != 0){
                    now += L;
                    cnt++;
                }
            }else{
                // 덮고 있는 상황
                int temp = out-now;
                now += (temp/L)*L;
                cnt += temp/L;
                if(temp%L != 0){
                    now += L;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);

    }
}
