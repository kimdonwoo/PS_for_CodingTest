package unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197 {
    public static class Edge{
        int start;
        int end;
        int cnt;

        public Edge(int start, int end, int cnt){
            this.start = start;
            this.end = end;
            this.cnt = cnt;
        }
    }
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[E];

        for(int i = 0 ; i < E ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(s,e,cnt);
        }

        Arrays.sort(edges,(o1,o2)->{
            return o1.cnt - o2.cnt;
        });


        // 여기서 유니온 파인드
        int res = 0;
        int select = 0;
        int[] parent = new int[V+1];
        for(int i = 0 ; i < V+1 ; i++) parent[i] = i;

        for(int i = 0 ; i < E ; i++){
            if(select == V-1) break;

            Edge now = edges[i];

            if(find(now.start,parent) != find(now.end, parent) ){
                union(now.start,now.end,parent);
                select++;
                res+=now.cnt;
            }
        }

        System.out.println(res);


    }

    private static void union(int a, int b, int[] parent) {
        a = find(a,parent);
        b = find(b,parent);

        if(a < b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }

    private static int find(int a, int[] parent ) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a],parent);
    }

}
