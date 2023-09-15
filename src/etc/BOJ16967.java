package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16967 {
    /*
        H*W인 배열 A

         (H + X) × (W + Y)인 배열 B


        1. 두 배열 모두에 포하뫼지 않으면 0
        2. 모두에 포함되면 2개 더하기

        B에서 A를 구하는거네

        H*W인 배열 A <= 이걸 구해라

        A1
        1 2 3 4 0
        5 6 7 8 0
        0 0 0 0 0

        A2
        0 0 0 0 0
        0 1 2 3 4
        0 5 6 7 8

        (H + X) × (W + Y)인 배열 B

     */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int[][] B = new int[H + X][W + Y];

        for(int i = 0 ; i < H+X ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < W + Y ; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] A = new int[H][W];

        for(int i = 0 ; i < H ; i++){
            for(int j = 0 ; j < W ; j++){
                if(i >= X && j >= Y){
                    A[i][j] = B[i][j]-A[i-X][j-Y];
                }else{
                    A[i][j] = B[i][j];
                }
            }
        }

        for(int i = 0 ; i < H ;i++){
            for(int j = 0 ; j < W ;j++){
                System.out.print(A[i][j]+" ");
            }
            System.out.println();
        }




    }
}






