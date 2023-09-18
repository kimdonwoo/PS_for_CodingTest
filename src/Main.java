import java.util.*;
import java.io.*;


public class Main {
    static int[] arr;
    static ArrayList<Integer> check;
    public static void main(String args[]) throws IOException {


        check  = new ArrayList<>();
        arr = new int[]{1, 2, 3, 4, 5};
        DFS(0,3,-1);

    }
    public static void DFS(int depth, int goal ,int idx){
        if(depth == goal){
            for( int i : check) System.out.print(i+" ");
            System.out.println();
            return;
        }

        for(int i = idx+1 ; i < arr.length ; i++){
            check.add(arr[i]);
            DFS(depth+1,goal,i);
            check.remove(check.size()-1);
        }
    }
}
