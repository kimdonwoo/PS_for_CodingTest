import java.util.*;
import java.io.*;


public class Main {
    public static void main(String args[]){

        int N = 5;

        for (int k = 1; k < N; k++) {
            for (int i = 0; i + k < N; i++) {
                for (int j = i; j < i + k; j++)
                    System.out.println(k +" " + i +" "+j);

            }
        }


    }
}
