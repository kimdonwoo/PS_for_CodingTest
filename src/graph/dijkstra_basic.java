package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

        /*
            다익스트라 알고리즘

            - 그래프의 최단 경로 구하는 알고리즘
            - 하나의 정점에서 출발하는 최단 거리를 구함
            - 음수 가중치 없어야 함
            - 인접행렬 : O(n^2) But 우선순위큐 사용하면 O(mlogn)까지 낮출 수 있음 (m : 간선, n : 정점)
                (정점 마다 적용시키면 O(nmlogn))

            pq , check(확정), dist(거리)

            1. 출발지를 pq에 넣고, check를 true로, dist는 0넣기
            2. pq에서 꺼내서 now에 저장하고 방문 체크
            3. 갈수 있는 정점중 현재 dist랑 now의 dist + now에서 next거리 비교
            4. 변경되면 pq에 넣기
         */

class Node implements Comparable<Node>{
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}

public class dijkstra_basic {
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {

        //그래프 입력 받기
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        //정점의 개수, 간선의 개수
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());

        graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++)  graph[i] = new ArrayList<>();

        StringTokenizer st;

        for(int i = 0 ; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 방향 없으면 양쪽 다 해줘야함
            graph[v].add(new Node(w, cost));
        }

        int start = Integer.parseInt(bf.readLine());

        //다익스트라 알고리즘 수행
        Dijkstra(n, start);
    }


    //노드의 크기, 출발지
    public static void Dijkstra(int n, int start) {

        boolean[] check = new boolean[n + 1];
        int[] dist = new int[n + 1];
        int INF = Integer.MAX_VALUE;

        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            // 이미 방문한 곳이면
            if(check[nowVertex]) continue;
            check[nowVertex] = true;

            //index의 연결된 정점 비교
            for(Node next : graph[nowVertex]) {
                if(dist[next.index] > dist[nowVertex]+ next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;

                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        //최소거리 출력
        for(int i : dist) {
            if(i == INF) System.out.print(0 + " ");
            else System.out.print(i+" ");
        }
    }
}
