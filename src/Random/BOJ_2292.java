package Random;

import java.util.*;
import java.io.*;

public class BOJ_2292 {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int r = 0;

        // N : 1_000_000_000
        // (r-1)r >= 333_333_333

        while(true){
            r++;
            if(3*(r-1)*r >= N-1) break;
        }

        System.out.println(r);


    }
}
