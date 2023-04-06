package 그리디;

import java.util.ArrayList;
import java.util.Scanner;

public class 백준_11047 {
    static int n;
    static int k;
    static int[] arr;

    public static void ps_11047(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();

        arr = new int[n];

        for(int i = 0 ; i<n;i++){
            arr[i] = sc.nextInt();
        }

        int res = 0;

        for(int i = 0 ; i<n;i++){
            if(k / arr[n-i-1] > 0){
                res += k / arr[n-i-1];
                k = k%arr[n-i-1];
            }
        }

        System.out.println(res);
    }

}
