package prac_zone;

import java.util.PriorityQueue;

public class Free {
    public static void main(String[] args) throws Exception {

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->{
            return o2[2] - o1[2];
        });

        pq.add(new int[] {1,5,3});
        pq.add(new int[] {2,4,2});
        pq.add(new int[] {3,3,1});
        pq.add(new int[] {4,2,4});
        pq.add(new int[] {5,1,5});


        while(!pq.isEmpty()){
            int[] now = pq.poll();
            System.out.println(now[0]+ " " +now[1] + " " +now[2]);
        }




    }
}
