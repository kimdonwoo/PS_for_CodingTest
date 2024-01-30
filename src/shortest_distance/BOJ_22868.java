package shortest_distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_22868 {

    static class node{

        public int now;
        public int sum;
        public ArrayList<Integer> hist;

        public node(int now,int sum, ArrayList<Integer> hist){
            this.now= now;
            this.sum = sum;
            this.hist = hist;
            hist.add(now);
        }


    }
    static int N,M,res = 0;
    static ArrayList<Integer>[] map;
    static HashMap<Integer,Boolean> hm = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            map[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            map[a].add(b);
            map[b].add(a);
        }

//        for(int i = 0 ; i < N ; i++){
//            Collections.sort(map[i]);
//        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken())-1;
        int end = Integer.parseInt(st.nextToken())-1;

        res += dijkstra(start,end);
        res += dijkstra(end,start);

        System.out.println(res);

    }

    private static int dijkstra(int start,int end) {

        int[] dist = new int[N];
        boolean[] visit = new boolean[N];
        Arrays.fill(dist,100_000);

        // 여기서 현재위치, dist, 지금까지 이동
        PriorityQueue<node> pq = new PriorityQueue((o1,o2)->{
            if(((node)o1).sum == ((node)o2).sum){
                return ((node)o2).now - ((node)o1).now;
            }
            return ((node)o1).sum - ((node)o2).sum;
        });
        dist[start] = 0;
        pq.add(new node(start,0,new ArrayList<>()));

        while(!pq.isEmpty()){
            node now = pq.poll();

            if(visit[now.now]){
                // 이미 방문
                continue;
            }

            visit[now.now] = true;

            if(now.now == end){
                // 이때 now.hist 값을
                for(int p : now.hist){
                    if(p == start) continue;
                    hm.put(p,true);
                }
                break;
            }

            for(int next : map[now.now]){
                if(hm.containsKey(next)) continue;
                if(dist[next] >= now.sum+1) {
                    dist[next] = now.sum+1;
                    ArrayList<Integer> temp = (ArrayList<Integer>) now.hist.clone();
                    pq.add(new node(next, dist[next] , temp));
                }
            }
        }

        return dist[end];

    }
}
