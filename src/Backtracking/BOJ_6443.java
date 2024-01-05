package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_6443 {
    static HashMap<Character,Integer> hm;
    static ArrayList<String> res;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        hm = new HashMap<>();


        for(int i = 0 ; i < T ; i++){
            // 단어의 길이는 20보다 작거나 같다
            String str = br.readLine();

            for(int j = 0 ; j < str.length() ; j++){
                hm.put(str.charAt(j),hm.getOrDefault(str.charAt(j),0)+1);
            }
            res = new ArrayList<>();
            backtracking(0,str.length(),sb);

            Collections.sort(res);

            for(String s : res){
                System.out.println(s);
            }

            hm.clear();
        }
    }

    private static void backtracking(int i, int length , StringBuilder sb) {
        // 선택완료
        if(i == length){
            res.add(sb.toString());
            return;
        }

        // 선택
        // 여기서 hm 삭제가 되려나? -> 역시 안됨 !
        // 이 상황에 iterator가 필요?? 흠 ..
        for(char c : hm.keySet()){

            int val = hm.get(c);
            if(val != 0) {
                hm.put(c, val - 1);

                backtracking(i + 1, length, sb.append(c));
                sb.deleteCharAt(sb.length() - 1);

                hm.put(c, val);
            }
        }
    }
}
