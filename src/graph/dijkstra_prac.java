package graph;

/*
5
9
1 2 10
1 3 5
2 3 2
3 1 1
3 2 13
4 1 8
4 5 3
5 4 9
5 2 31
4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dijkstra_prac {

    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static ArrayList<int[]>[] graph2;
    static int n;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // n은 정점의 갯수, m은 간선의 갯수
        n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());

        for(int i = 0 ; i < n+1 ; i++){
            graph.add(new ArrayList<>());
        }

        graph2 = new ArrayList[n+1];
        for(int i = 0 ; i < n+1 ; i++){
            graph2[i] = new ArrayList<>();
        }

        StringTokenizer st;

        for(int i = 0 ; i < m; i++) {
            st = new StringTokenizer(bf.readLine());

            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 방향 없으면 양쪽 다 해줘야함
            graph.get(v).add(new int[] {w, cost});
            graph2[v].add(new int[] {w,cost});
        }

        int start = Integer.parseInt(bf.readLine());

        //다익스트라 알고리즘 수행
        // (노드의 크기, 출발지)
        Dijkstra(start);
    }

    public static void Dijkstra(int start) {

        // 최소 거리 체크한 노드 저장
        boolean[] check = new boolean[n+1];
        // start로부터 해당노드까지의 최소 거리 저장
        int[] dist = new int[n+1];

        // 모든 노드의 dist INF 넣고 시작 노드에 0 넣기
        Arrays.fill(dist, INF);
        dist[start] = 0;

        // 우선순위큐 넣고 출발위치랑 dist넣기
        // 우선순위는 항상 코스트 작은걸로
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) ->{
            return o2[1] - o1[1];
        });
        pq.offer(new int[] {start,dist[start]});

        while(!pq.isEmpty()){ // pq가 빌때까지 반복

            int[] now = pq.poll();

            // 이미 방문한 곳인지 체크
            if(check[now[0]]) continue;
            check[now[0]] = true;

            // 중요!
            for(int[] next : graph.get(now[0])){
                // 지금위치에서 갈수 있는 다음위치에 현재 dist랑 now거쳐서 next가는거랑 비교
                if(dist[next[0]] > dist[now[0]] + next[1]){
                    dist[next[0]] = dist[now[0]] + next[1];
                    pq.offer(new int[] {next[0] , dist[next[0]]});
                }
            }
        }

        // 완성된 dist 출력
        for(int i : dist){
            if(i == INF) System.out.print(0 + " ");
            else System.out.print(i+" ");
        }

    }

}

// 뭐가 문제지..