package Brute_Force;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_12919 {
    static String S;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        String T = br.readLine();

        StringBuilder sb = new StringBuilder();
        sb.append(T);

        brute(sb);

        System.out.println(0);

    }

    private static void brute(StringBuilder str){
        if(str.length() == S.length()){

            if(str.toString().equals(S)){
                System.out.println(1);
                System.exit(0);
            }

            return;
        }

        /*
            만약
            ABB -> ABBA : 만약 맨뒤가 A이면 적용 가능
                   BBBA : 젤앞이 B이면 적용가능
         */

        if(str.charAt(0) == 'B'){
            StringBuilder temp = new StringBuilder();
            temp.append(str);
            temp.reverse();
            temp.deleteCharAt(temp.length()-1);
            brute(temp);
        }

        if(str.charAt(str.length()-1) == 'A'){
            StringBuilder temp = new StringBuilder();
            temp.append(str);
            temp.deleteCharAt(temp.length()-1);
            brute(temp);
        }
    }
}


    /*
        [푸는 방법]
        1. brute - 실패
        2. 반대로 해야 더 빠를 듯 ?

        // Stringbuilder 시간복잡도 공부 필요할듯

     */