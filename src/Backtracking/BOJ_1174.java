package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1174 {
    static int N;
    static ArrayList<Long> nums = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());


        Queue<Long> q = new LinkedList<>();

        for(int i = 0 ; i <= 9 ; i++){
            q.add((long)i);
        }

        while(!q.isEmpty()){
            Long now = q.poll();
            nums.add(now);

            long prefix = (long) Math.pow(10, now.toString().length() - 1);

            // TODO : 특정 숫자의 제일 앞 숫자를 알 수 있는 최적화된 알고리즘이 뭘까?
            long temp = (now / prefix);

            for(long i = temp+1 ; i <= 9 ; i++){
                q.add(i * prefix * 10 + now);
            }
        }

        if(N > nums.size()) System.out.println(-1);
        else{
            Collections.sort(nums);
            System.out.println(nums.get(N-1));
        }
    }
}

/*
    -



 */