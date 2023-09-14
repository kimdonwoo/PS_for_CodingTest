import java.util.*;
import java.io.*;


public class Main {
    public static void main(String args[]) throws IOException {

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) ->{
            return o1[0]-o2[0];
        });
        //PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(new int[]{1,4});
        pq.add(new int[]{2,3});
        pq.add(new int[]{3,2});
        pq.add(new int[]{4,1});

        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            System.out.println(temp[0]+" : "+temp[1]+" ");
        }






        // Arrays.copyOf
    }
}
