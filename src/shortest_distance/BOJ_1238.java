package shortest_distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238 {
    static int N,M,X;
    static ArrayList<int[]>[] map;
    static int[] tempDist;
    static int res = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken())-1;

        map = new ArrayList[N];

        for(int i = 0 ; i < N ; i++){
            map[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int time = Integer.parseInt(st.nextToken());

            map[start].add(new int[] {end,time});
        }

        // 먼저 X에서 시작해서 다익 돌리기
        dijkstraX();

        // 돌고 나면 tempDist가 다 초기화 되어 있을거임
        for(int i = 0 ; i < N ; i++){
            if(i == X) continue;
            dijkstra(i);
        }

        System.out.println(res);
    }


    private static void dijkstra(int start) {

        int[] dist = new int[N];
        Arrays.fill(dist,Integer.MAX_VALUE);
        boolean[] visit = new boolean[N];

        // X에서 시작해서 우선순위 큐로 돌리기
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
            return o1[1]-o2[1];
        });

        dist[start] = 0;
        pq.add(new int[] {start,0});

        while(!pq.isEmpty()){

            int[] now = pq.poll();

            if(visit[now[0]]) continue;
            visit[now[0]] = true;

            for(int[] next : map[now[0]]){
                // next에는 end랑 time이 존재

                if(dist[next[0]] > dist[now[0]] + next[1]){
                    dist[next[0]] = dist[now[0]] + next[1];
                    pq.add(new int[] {next[0],dist[next[0]]});
                }
            }
        }

        res = Math.max(res,tempDist[start]+dist[X]);

    }

    private static void dijkstraX() {

        tempDist = new int[N];
        Arrays.fill(tempDist,Integer.MAX_VALUE);
        boolean[] visit = new boolean[N];

        // X에서 시작해서 우선순위 큐로 돌리기
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
            return o1[1]-o2[1];
        });

        tempDist[X] = 0;
        pq.add(new int[] {X,0});

        while(!pq.isEmpty()){

            int[] now = pq.poll();

            if(visit[now[0]]) continue;
            visit[now[0]] = true;

            for(int[] next : map[now[0]]){
                // next에는 end랑 time이 존재

                if(tempDist[next[0]] > tempDist[now[0]] + next[1]){
                    tempDist[next[0]] = tempDist[now[0]] + next[1];
                    pq.add(new int[] {next[0],tempDist[next[0]]});
                }
            }
        }
    }
}
