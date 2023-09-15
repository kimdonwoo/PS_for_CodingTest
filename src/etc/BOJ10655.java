package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10655 {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] check= new int[N][2];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            check[i][0] = Integer.parseInt(st.nextToken());
            check[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] arr1 = new int[N-1];
        int[] arr2 = new int[N-2];

        int sum = 0;

        for(int i = 0 ; i < N-1 ; i++){
            arr1[i] = Math.abs(check[i][0]-check[i+1][0])+ Math.abs(check[i][1]-check[i+1][1]);
            sum+= arr1[i];
        }

        for(int i = 0 ; i < N-2 ; i++){
            arr2[i] = Math.abs(check[i][0]-check[i+2][0])+ Math.abs(check[i][1]-check[i+2][1]);
        }

        int maxNum = Integer.MIN_VALUE;
        for(int i = 0 ; i < N-2 ; i++){
            maxNum=Math.max(maxNum,arr1[i]+arr1[i+1]-arr2[i]);
        }

        System.out.println(sum-maxNum);

    }
    /*
        arr1 : 11 7 2 : 20 (N-1개)
        arr2 : 12 5     (N-2개)

        arr1[0]+arr1[1]-arr2[0]가 최대인거 구하기
     */
}
