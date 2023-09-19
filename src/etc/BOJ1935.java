package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1935 {
    static HashMap<Character,Integer> hm;
    static Stack<Double> stack;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String str = br.readLine();
        hm = new HashMap<>();
        stack = new Stack<>();

        for(int i = 0 ; i < N ; i++){
            hm.put((char) ('A'+i),Integer.parseInt(br.readLine()));
        }

        for(int i = 0 ; i < str.length() ; i++){
            char c = str.charAt(i);

            if(c >= 'A' && c <= 'Z'){ // 피연산자면
                stack.add(Double.valueOf(hm.get(c)));
            }else{ //
                double a = stack.pop();
                double b = stack.pop();
                double temp = 0;

                if(c == '*') temp = a*b;
                else if (c == '+') temp = a+b;
                else if (c == '/') temp = b/a;
                else if (c == '-') temp = b-a;
                stack.add(temp);
            }
        }
        double res = stack.pop();
        System.out.printf("%.2f",res);

        /*
            알파벳 갯수가 첨에 나오고 ABCDE 이렇게 나온느건가

            HashMap에 값넣어두고 계싼하면 될듯?
            20억은 뭘까 흠..

            (1(23*)+)(45/)-
               => 7 - 0.8

               stack : 1 2 3
                        *나오면 위에 2개 뽑아서 곱해서 다시 넣기
                       1 6
                        +
                        7 0.8
                        -
         */



    }
}
