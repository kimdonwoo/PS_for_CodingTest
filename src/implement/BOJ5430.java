package implement;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ5430 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            String p = sc.next();
            int n = sc.nextInt();

            String arrStr = sc.next();
            Deque<Integer> deque = new LinkedList<>();

            for (String s : arrStr.substring(1, arrStr.length() - 1).split(","))
                if (!s.equals(""))
                    deque.add(Integer.valueOf(s));


            System.out.println(ac(deque, p));
        }
    }

    static String ac(Deque<Integer> deque, String commands) {
        boolean reverse = false;

        for (char command : commands.toCharArray()) {
            if (command == 'R')
                reverse = !reverse;
            else {
                if (deque.size() == 0)
                    return "error";

                if (reverse)
                    deque.removeLast();
                else
                    deque.removeFirst();
            }
        }

        StringBuilder sb = new StringBuilder("[");
        while (!deque.isEmpty()) {
            sb.append(reverse ? deque.removeLast() : deque.removeFirst());
            if (deque.size() != 0)
                sb.append(',');
        }
        sb.append(']');

        return sb.toString();
    }
}



    //    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int N =Integer.parseInt(br.readLine());
//
//        for(int i = 0 ; i < N ; i ++){
//            String func = br.readLine();
//            int M =Integer.parseInt(br.readLine());
//            String nums = br.readLine(); // [42]
//
//
//            boolean dir = true; // true가 정방향
//            int start_idx = 1;
//            int end_idx = nums.length()-2; //
//            boolean isError = false;
//
//            // 2. func의 길이만큼 for문 돌고 각 함수 적용
//            for(int j = 0 ; j < func.length() ; j++){
//                if(func.charAt(j) == 'R'){
//                    // 여기 R 함수 정의
//                    dir = !dir;
//                }
//                else{
//                    // 여기 D 함수 정의
//                    if(dir){ // 정방향이면
//                        if(start_idx > end_idx){
//                            isError = true;
//                            break;
//                        }
//                        start_idx +=2;
//                    }else{
//                        if(start_idx > end_idx){
//                            isError = true;
//                            break;
//                        }
//                        end_idx -=2;
//                    }
//
//                }
//            }
//
//            // 여기서  출력
//            if(isError){
//                System.out.println("error");
//            }else{
//                System.out.print("[");
//                if(dir){ // 정방향 일때
//                    System.out.print(nums.substring(start_idx+1,end_idx+1));
//                }else{
//                    StringBuffer sb = new StringBuffer();
//                    sb.append(nums);
//                    System.out.print(sb.reverse().toString().substring(nums.length()-end_idx-1,nums.length()-start_idx-1));
//
//                }
//                System.out.println("]");
//
//
//            }
//
//        }
//
//    }
//}
