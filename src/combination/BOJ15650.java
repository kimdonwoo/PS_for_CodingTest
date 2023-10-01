package combination;

import java.util.Scanner;

public class BOJ15650 {
    static int N,M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[M];

        DFS_comb(0,0);
        System.out.println(sb.toString());

    }

    static void DFS_comb(int depth, int now){
        if(depth == M){
            for(int i = 0 ; i < M ; i++){
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for(int i = now+1 ; i < N+1 ; i++ ){
            arr[depth] = i;
            DFS_comb(depth+1,i);
        }
    }
}
