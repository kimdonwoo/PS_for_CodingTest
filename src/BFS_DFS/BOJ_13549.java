package BFS_DFS;

import java.util.Scanner;

public class BOJ_13549 {
    /*
        수빈이는 현재 N (100_000)
        동생은 K (100_000)

        수빈이는
            (1초) N+1
            (1초) N-1
            (0초) 2*N
        ex : 5 17

            가장 빠른 시간이니깐 BFS ??
            2배가 0초라 애매하네

            우선순위 큐에 넣고 찾을때까지 반복
            현재꺼 꺼내서 큐에 2+n개 넣기
                여기서 n이란 현재값을 2배씩하다가 K의 2배를 넘어가는 경우까지만 넣기

     */
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();



    }
}
