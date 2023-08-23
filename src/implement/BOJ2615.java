package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2615 {

    static int[][] grounds;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        grounds=new int[19][19];

        for(int i = 0 ; i < 19 ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 19 ; j++){
                grounds[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < 19 ; i++){
            for(int j = 0 ; j < 19 ; j++){
                if(grounds[i][j] != 0){

                    // 해당 지점 기준으로 오른쪽으로 체크
                    if(check_right(i,j,grounds[i][j])){
                        System.out.println(grounds[i][j]);
                        System.out.println((i+1) + " " + (j+1));
                        return;
                    }

                    // 오른쪽 아래 대각선 체크
                    if(check_down_diag(i,j,grounds[i][j])){
                        System.out.println(grounds[i][j]);
                        System.out.println((i+1) + " " + (j+1));
                        return;
                    }

                    // 오른쪽 위로 대각선 체크
                    if(check_up_diag(i,j,grounds[i][j])){
                        System.out.println(grounds[i][j]);
                        System.out.println((i+1) + " " + (j+1));
                        return;
                    }

                    // 아래로 체크
                    if(check_down(i,j,grounds[i][j])){
                        System.out.println(grounds[i][j]);
                        System.out.println((i+1) + " " + (j+1));
                        return;
                    }


                }
            }
        }

        // 다 돌았는데 없으면 0 출력
        System.out.println(0);
    }

    public static boolean check_right(int i, int j , int num){
        if(j+4 >= 19) return false; // 마지막 칸이 범위 벗어나면 false

        //1. 해당 칸 왼쪽 칸이 범위 안에 있을때, num 있으면 걍 통과시킴
        if(j-1 >= 0) {
            if (grounds[i][j - 1] == num) return false;
        }

        //2. 오른쪽으로 5칸이 같은 num인지 확인
        for(int k = 1 ; k < 5; k++){ // j j+1 j+2 j+3 j+4
            if(grounds[i][j+k] != num) return false;
        }

        //3. 그 다음 칸이 num인지 아닌지 체크
        if(j+5 < 19) {
            if (grounds[i][j + 5] == num) return false;
        }
        //4. 다 통과한 조건이면
        return true;
    }

    public static boolean check_down_diag(int i, int j , int num){
        if(i+4 >= 19 || j+4 >= 19) return false;

        //1. 해당 칸 왼쪽 대각선 위 칸이 범위 안에 있을때, num이면 걍 통과시킴
        if(j-1 >= 0 && i-1 >= 0){
            if(grounds[i-1][j-1] == num) return false;
        }

        //2. 오른쪽 대각선으로 5칸이 같은 num인지 확인
        for(int k = 1 ; k < 5; k++){
            if(grounds[i+k][j+k] != num) return false;
        }

        //3. 그 다음 칸이 num인지 아닌지 체크
        if(j+5 < 19 && i+5 < 19) {
            if (grounds[i + 5][j + 5] == num) return false;
        }
        //4. 다 통과한 조건이면
        return true;
    }

    public static boolean check_up_diag(int i, int j , int num){
        if(i-4 < 0 || j+4 >= 19) return false;

        //1. 해당 칸 왼쪽 대각선 아래 칸이 범위 안에 있을때, num이면 걍 통과시킴
        if(j-1 >= 0 && i+1 < 19 ){
          if (grounds[i+1][j-1] == num) return false;
        }

        //2. 오른쪽 대각선으로 5칸이 같은 num인지 확인
        for(int k = 1 ; k < 5; k++){
            if(grounds[i-k][j+k] != num) return false;
        }

        //3. 그 다음 칸이 num인지 아닌지 체크
        if(j+5 < 19 && i-5 >= 0) {
            if (grounds[i - 5][j + 5] == num) return false;
        }
        //4. 다 통과한 조건이면
        return true;
    }

    public static boolean check_down(int i, int j , int num){
        if(i+4 >= 19) return false;

        //1. 해당 칸 위 칸이 범위 안에 있을때, num 있으면 걍 통과시킴
        if(i-1 >= 0){
            if (grounds[i-1][j]== num) return false;
        }

        //2. 오른쪽으로 5칸이 같은 num인지 확인
        for(int k = 1 ; k < 5; k++){
            if(grounds[i+k][j] != num) return false;
        }

        //3. 그 다음 칸이 num인지 아닌지 체크
        if(i+5 < 19){
            if(grounds[i+5][j] == num) return false;
        }

        //4. 다 통과한 조건이면
        return true;
    }


}
