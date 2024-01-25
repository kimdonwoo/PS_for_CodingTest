import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_22865 {
    static ArrayList<int[]>[] map;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken())-1;
        int B = Integer.parseInt(st.nextToken())-1;
        int C = Integer.parseInt(st.nextToken())-1;

        map = new ArrayList[N];
        int[] distA = new int[N];
        int[] distB = new int[N];
        int[] distC = new int[N];

        Arrays.fill(distA,500_000);
        Arrays.fill(distB,500_000);
        Arrays.fill(distC,500_000);

        for(int i = 0 ; i < N ; i++) {
            map[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken())-1;
            int E = Integer.parseInt(st.nextToken())-1;
            int dist = Integer.parseInt(st.nextToken());

            map[D].add(new int[] {E,dist});
            map[E].add(new int[] {D,dist});
        }

        dijkstra(distA,A);
        dijkstra(distB,B);
        dijkstra(distC,C);

        int[] res = distA.clone();
        int maxCnt = Integer.MIN_VALUE;
        for(int i = 0 ; i < N ; i++){
            res[i] = Math.min(res[i] , distB[i]);
            res[i] = Math.min(res[i] , distC[i]);
            if(res[i] != 500_000) maxCnt = Math.max(res[i],maxCnt);
        }

        for(int i = 0 ; i < N ; i++){
            if(res[i] == maxCnt){
                System.out.println(i+1);
                break;
            }
        }
        /*
            -> 친구들이 살고 있는 집으로부터 가장 먼 곳의 땅 번호를 출력한다.
            만약 가장 먼 곳이 여러 곳이라면 번호가 가장 작은 땅의 번호를 출력한다.

            A,B,C에 대해 dijkstra 돌리기?
            map은 100_000
         */

    }

    private static void dijkstra(int[] dist, int start) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
            return o1[1]-o2[1];
        });
        boolean[] visit = new boolean[N];

        pq.add(new int[] {start,0});
        dist[start] = 0;

        while(!pq.isEmpty()){
            int[] now = pq.poll();

            if(visit[now[0]]) continue;
            visit[now[0]] = true;

            for(int[] next : map[now[0]]){
                if(dist[next[0]] > dist[now[0]]+next[1]){
                    dist[next[0]] = dist[now[0]]+next[1];
                    pq.add(new int[] {next[0],dist[next[0]]});
                }
            }
        }
    }


}
