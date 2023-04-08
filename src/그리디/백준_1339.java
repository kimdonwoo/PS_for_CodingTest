package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class 백준_1339 {
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n =  Integer.parseInt(br.readLine());

        HashMap<Character,Integer> map = new HashMap<>();

        for(int i = 0 ; i < n ; i++){
            String s = br.readLine();
            int pos = 0;
            for(int j = s.length()-1 ; j >= 0 ; j--){
                if(map.containsKey(s.charAt(j))){
                    map.put(s.charAt(j),map.get(s.charAt(j))+(int)Math.pow(10,pos));
                }else{
                    map.put(s.charAt(j),(int)Math.pow(10,pos));
                }
                pos++;
            }
        }

        // 내림차순 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(Character c : map.keySet()){
            pq.add(map.get(c));
        }

        int res = 0;

        int num = 9;
        while(!pq.isEmpty()){
            res += num * pq.poll();
            num--;
        }

        System.out.println(res);



    }

}
