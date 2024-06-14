package Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4659 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){

            String str = br.readLine();
            boolean check = true;

            if(str.equals("end")) break;

            char[] arrs = str.toCharArray();

            for(int i = 0 ; i < arrs.length ; i++){
                // 하나도 포함안되었으면 실패
                if(arrs[i] == 'a' || arrs[i] == 'e' || arrs[i] == 'i' || arrs[i] == 'o' || arrs[i] == 'u' ){
                    check = true;
                    break;
                }
                check = false;
            }

            int seq = 1;
            boolean state = false; // 모음
            if(arrs[0] == 'a' || arrs[0] == 'e' || arrs[0] == 'i' || arrs[0] == 'o' || arrs[0] == 'u' ){
                state = false;
            }else{
                state = true;
            }

            for(int i = 1 ; i < arrs.length ; i++) {
                if (!state && (arrs[i] == 'a' || arrs[i] == 'e' || arrs[i] == 'i' || arrs[i] == 'o' || arrs[i] == 'u')) {
                    // 연속 모음이면
                    seq++;
                } else if (state && (arrs[i] == 'a' || arrs[i] == 'e' || arrs[i] == 'i' || arrs[i] == 'o' || arrs[i] == 'u')) {
                    // 자음 -> 모음
                    seq = 1;
                    state = false;
                } else if (state && !(arrs[i] == 'a' || arrs[i] == 'e' || arrs[i] == 'i' || arrs[i] == 'o' || arrs[i] == 'u')) {
                    // 연속 자음
                    seq++;
                } else {
                    seq = 1;
                    state = true;
                }

                if (seq == 3) {
                    check = false;
                    break;
                }
            }

            if(arrs.length > 1){
                for(int i = 0 ; i < arrs.length-1 ; i++) {
                    if(arrs[i] == arrs[i+1]){
                        if(!(arrs[i] == 'e' || arrs[i] == 'o')){
                            check = false;
                            break;
                        }
                    }
                }
            }

            if(!check)
                sb.append("<"+str+">"+" is not acceptable.\n");
            else
                sb.append("<"+str+">"+" is acceptable.\n");

        }

        System.out.println(sb);
    }
}
