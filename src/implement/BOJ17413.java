package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ17413 {
    public static void main(String[] args) throws IOException {

        StringBuffer sb = new StringBuffer();
        // 1. 문자열 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // 2. < 입력받으면 > 나올때까지 StringBuffer에 넣고 나오면 출력
        boolean flag = false;
        for(int i = 0 ; i < str.length();i++){
            if(str.charAt(i) == '<'){
                if(sb.length() > 0){
                    System.out.print(sb.reverse());
                    sb.delete(0,sb.length());
                }
                flag = true;
                sb.append(str.charAt(i));
                continue;
            }
            if(str.charAt(i) == '>'){
                flag = false;
                sb.append(str.charAt(i));
                System.out.print(sb);
                sb.delete(0,sb.length());
                continue;
            }

            if(flag){
                sb.append(str.charAt(i));
            }else{ //계속 입력받다가 String의 끝이거나 띄워쓰기거나 <가 나오면  나오면 reverse 출력
                if(str.charAt(i) == ' '){
                    System.out.print(sb.reverse());
                    System.out.print(' ');
                    sb.delete(0,sb.length());
                }else if(i == str.length()-1){
                    sb.append(str.charAt(i));
                    System.out.print(sb.reverse());
                    sb.delete(0,sb.length());
                }else{
                    sb.append(str.charAt(i));
                }
            }
        }
    }
}
