package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class BOJ_20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-->0){

            char temp[] = br.readLine().toCharArray();
            int K = Integer.parseInt(br.readLine());
            HashMap<Character, ArrayList<Integer>> hm = new HashMap<>();
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            for(int i = 0 ; i < temp.length ; i++){
                if(hm.containsKey(temp[i])){
                    hm.get(temp[i]).add(i);
                }else{
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(i);
                    hm.put(temp[i],arrayList);
                }
            }

            for(char c : hm.keySet()){
                if(hm.get(c).size() >= K){
                    ArrayList<Integer> arr = hm.get(c);
                    for(int i = 0 ; i < arr.size()-K+1 ; i++){
                        max = Math.max(max,arr.get(i+K-1) - arr.get(i));
                        min = Math.min(min,arr.get(i+K-1) - arr.get(i));
                    }
                }
            }

            if(max == Integer.MIN_VALUE){
                System.out.println(-1);
            }else {
                System.out.println(min + 1);
                System.out.println(max + 1);
            }
        }

        /*
            K를 포함하는 가장 짧은 연속 문자열
            - 어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
            - 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.

            문자열 길이 10_000

            26개 돌면서 idx 넣고 Max 찾기


         */

    }
}
