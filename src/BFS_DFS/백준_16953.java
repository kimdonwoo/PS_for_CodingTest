package BFS_DFS;

import java.util.Scanner;

public class 백준_16953 {
    static long B ;
    static int res;
    static boolean find;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        long A = sc.nextLong();
        B = sc.nextLong();

        res = 1;

        DFS(A);

        if(find) System.out.println(res);
        else System.out.println(-1);

    }

    private static void DFS(long num) {
        // 찾았으면 전부다 탈출
        if(find) return;
        if(num == B ) find = true;

        if(num*10+1 <= B) {
            res++;
            DFS(num * 10 + 1);
            if(find) return;
            res--;
        }
        if(num*2 <= B){
            res++;
            DFS(num*2);
            if(find) return;
            res--;
        }
    }
}








