package Greedy;

import java.util.Scanner;

public class 백준_2839 {
    public static void ps_2839(String args[]){

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int res = 0;

        for(int i = N/5 ; i >= 0 ; i--){
            if((N-i*5)%3==0){
                res += i;
                res += (N-i*5)/3;
                System.out.println(res);
                break;
            }
        }
        if(res == 0) System.out.println(-1);

    }
}
