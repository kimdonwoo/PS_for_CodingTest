import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_2661 {
    static int[] select;
    static int N;
    public static void main(String[] args) throws IOException {

        Scanner sc= new Scanner(System.in);
        N = sc.nextInt();
        select = new int[N];

        backtracking(0);


    }

    public static void backtracking(int count){
        // 선택 완료
        if(count == N){
            for(int s : select) System.out.print(s);
            System.exit(0);
            return;
        }

        //선택
        for(int i = 1 ; i <= 3 ; i ++){
            select[count] = i;
            if(!checkbad(count)){
                backtracking(count+1);
            }
        }
    }

    public static boolean checkbad(int count){
        /*
            여기서 select의 0부터 count-1 상황에서
            count에 num을 넣었을 때 나쁜 수열이 되는지 판별해야함
         */

        for(int i = 0 ; i < count ; i++){
            if(count-i % 2 == 0) continue;

            boolean check = false;

            for(int j = i ; j < i+(count-i+1)/2; j++){
                if(select[j] != select[j+(count-i+1)/2]){
                    check = true;
                    break;
                }
            }

            // check가 true이면
            // 하나는 다른 상황 -> 즉, 통과인 상황
            // check가 false이면 다 같은 상황 -> 나쁜 배열

            if(!check) return true;

        }

        return false;


    }
}
