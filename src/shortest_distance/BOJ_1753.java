package shortest_distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753 {
    static ArrayList<int[]>[] map;
    static boolean[] visit;
    static int[] dist;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine())-1;

        map = new ArrayList[V];
        for(int i = 0 ; i < V ; i++){
            map[i] = new ArrayList<>();
        }

        dist = new int[V];
        Arrays.fill(dist,Integer.MAX_VALUE);

        visit = new boolean[V];

        for(int i = 0 ; i < E ; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int val = Integer.parseInt(st.nextToken());

            map[a].add(new int[] {b,val});
        }

        // map 완성 이제 다익 시작
        dijkstra(start);

        // dist  출력
        StringBuilder sb = new StringBuilder();
        for(int d : dist){
            if(d == Integer.MAX_VALUE) sb.append("INF \n");
            else sb.append(d+"\n");
        }

        System.out.println(sb.toString());

    }

    public static void dijkstra(int start){

        /*
            가중치 작은게 pq 먼저 나와야 함
                나온 애가 visit 한 애면 패스
                아니면 dist 비교해서 갱신

         */

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
            return o1[1]-o2[1];
        });
        dist[start] = 0;
        pq.add(new int[] {start,0});

        while(!pq.isEmpty()){
            int[] now = pq.poll();

            if(visit[now[0]]) continue;
            visit[now[0]] = true;

            // 여기서 거리 계산 + pq에 뭘 넣는지 헷갈리네
            // dist[now[0]] 흠..
            // next[1]이 현재 now에서
            for(int[] next : map[now[0]]){
                if(dist[next[0]] > dist[now[0]] + next[1] ){
                    dist[next[0]] = dist[now[0]] + next[1];
                    pq.add(new int[] {next[0],dist[next[0]]});
                }
            }
        }

    }
}
/*
            시작 점에서 모든 다른 점 최단 경로

            V,E -> 다익 ElogV 인가 반대

            - 인접리스트
            - dist
            - visit

         */