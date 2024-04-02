package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1464 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 50 자리
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();

        boolean dir = true;
        sb.append(str.substring(0,1));

        for(int i = 1 ; i < str.length() ; i++){
            if(dir){
                // 증가방향
                if(sb.charAt(0) >= str.charAt(i)){
                    sb.reverse();
                    dir = !dir;
                    sb.append(str.charAt(i));
                }else{
                    sb.append(str.charAt(i));
                }
            }else{
                if(sb.charAt(sb.length()-1) < str.charAt(i)){
                    sb.reverse();
                    dir = !dir;
                    sb.append(str.charAt(i));
                }else{
                    sb.append(str.charAt(i));
                }
            }
        }


        if(dir){
            System.out.println(sb.toString());
        }else{
            System.out.println(sb.reverse().toString());
        }
    }
}

/*
    [그리디 풀이법]

    새로 들어온 문자가 sb의 마지막 문자보다 크면 sb의 앞에 붙이기
    아니면 sb 뒤에 붙이기
    마지막에 reverse 하기




 */