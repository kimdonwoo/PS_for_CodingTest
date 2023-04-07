package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int i;
    int j;

    Node(int i, int j){
        this.i = i;
        this.j = j;
    }
}

public class 백준_11000 {

    public static void ps_11000(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        ArrayList<Node> arr = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            arr.add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        // Arrays.sort(lectures, (l1, l2) -> l1.start == l2.start ? l1.end - l2.end : l1.start - l2.start);
        Collections.sort(arr,(o1,o2) -> o1.i == o2.i ? o1.j-o2.j : o1.i-o2.i);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(arr.get(0).j);

        for(int k = 1; k < N ; k++){
            if(pq.peek() <= arr.get(k).i){
                pq.poll();
            }
            pq.add(arr.get(k).j);
        }

        System.out.println(pq.size());

    }
}

