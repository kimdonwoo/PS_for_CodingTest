package implement;

import java.util.Scanner;

public class BOJ12904 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String S = sc.next();
        String T = sc.next();

        // S를 T로 바꿀 수 있으면 1, 없으면 0
        // S가 1 이고 T가 1000 일때 -> 2^999
        // 즉 완탐 x

        // T에서 S 맞춰가기
        StringBuffer T_sb = new StringBuffer();
        T_sb.append(T);

        while(true){
            if(T_sb.charAt(T_sb.length()-1) == 'A'){
                T_sb.deleteCharAt(T_sb.length()-1);
            }else{
                T_sb.deleteCharAt(T_sb.length()-1);
                T_sb.reverse();
            }

            if(T_sb.length() == S.length()){
                if(S.equals(T_sb.toString())){
                    System.out.println(1);
                }else{
                    System.out.println(0);
                }
                return;
            }

        }

    }
}
