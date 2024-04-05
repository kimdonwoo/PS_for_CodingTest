package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());
        int len = N - K;

        Stack<Character> stack = new Stack<>();

        String str = br.readLine();

        for(int i = 0; i < str.length() ; i++){
            if(!stack.empty()){
                while(!stack.empty() && K > 0 && stack.peek() < str.charAt(i)){
                    stack.pop();
                    K--;
                }
            }

            stack.push(str.charAt(i));
        }

        while(true){
            if(stack.size() == len)
                break;

            stack.pop();
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb.reverse().toString());

    }
}
//public class BOJ_2812 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//
//        StringBuilder sb = new StringBuilder();
//        String nums = br.readLine();
//
//        int idx = 0;
//        for(int i = K ; i < N ; i++){
//            int maxVal = -1;
//            int maxIdx = -1;
//            for(int j = idx ; j <= i ; j++){
//                // 여기서 최댓값찾기
//                if(maxVal < nums.charAt(j)-'0'){
//                    maxVal = nums.charAt(j)-'0';
//                    maxIdx = j;
//                }
//            }
//            sb.append(maxVal);
//            idx = maxIdx+1;
//        }
//
//        System.out.println(sb.toString());
//        /*
//        0~
//
//         */
//
//    }
//}

/*


6 3
7767
1245

// 매번 다시 계산?

7 7 6 7
1 2 3 4

8 9 9 11

8 9 6 10 12 7

[1 9 2] 4]

// N-K자리를 만들어야함


 */