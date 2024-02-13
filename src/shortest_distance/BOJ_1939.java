package shortest_distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1939 {
    static ArrayList<int[]>[] map;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            map[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken())-1;
            int B = Integer.parseInt(st.nextToken())-1;
            int C = Integer.parseInt(st.nextToken()); // 1_000_000_000

            map[A].add(new int[] {B,C});
            map[B].add(new int[] {A,C});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken())-1;
        int end = Integer.parseInt(st.nextToken())-1;

        System.out.println(dijkstra(start,end));

    }

    private static int dijkstra(int start, int end) {

        int[] dist = new int[N];
        Arrays.fill(dist,0);

        boolean[] visit = new boolean[N];
        // 무게 높은 길이 우선되야 하기 때문에
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
            return o2[1]-o1[1];
        });

        dist[start] = 1_000_000_001;
        pq.add(new int[] {start,1_000_000_001});

        while(!pq.isEmpty()){

            int[] now = pq.poll();

            if(visit[now[0]]) continue;
            visit[now[0]] = true;

            for( int[] next : map[now[0]]){
                if(dist[next[0]] < Math.min(dist[now[0]],next[1])){
                    dist[next[0]] = Math.min(dist[now[0]],next[1]);
                    pq.add(new int[] {next[0],dist[next[0]]});
                }
            }
        }

        return dist[end];


    }
}
