package 그리디;

import java.util.*;

public class 백준_1202 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int k = scan.nextInt();

        ArrayList<Node> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int m = scan.nextInt();
            int v = scan.nextInt();
            list.add(new Node(m, v));
        }
        Collections.sort(list, (o1, o2) -> o1.m - o2.m);


        int[] weight = new int[k];
        for(int i = 0; i < k; i++) {
            weight[i] = scan.nextInt();
        }

        Arrays.sort(weight);
        // 가방 무게 오름차순 정렬

        long total = 0;
        int listIdx = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.v - o1.v); //가격순 정렬


        for(int i = 0; i < k; i++) {

            // 제일 작은 무게의 가방부터 들고와서
            // 무게로 정렬된 보석함에서 가방에 들어가는 보석기준으로
            // 가벼운것부터 들어서 pq에 넣음
            // pq에 모든값 넣고 그중 최대 val 값뽑음
            // 모든 가방에 이를 적용

            while(listIdx < n && list.get(listIdx).m <= weight[i]) {
                Node current = list.get(listIdx++);
                pq.add(new Node(current.m, current.v));
            }
            if(!pq.isEmpty()) total += pq.poll().v;
        }



        System.out.println(total);
    }

    public static class Node {
        int m;
        int v;

        public Node(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
}
