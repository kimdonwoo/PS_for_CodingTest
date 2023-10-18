package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18513 {

    public static int n, k;
    public static Set<Integer> visited;
    public static Queue<int []> q;
    public static int[] dx = {-1, 1};

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        q = new LinkedList<>();
        visited = new HashSet<>();

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++){
            int num = Integer.parseInt(st.nextToken());;
            q.add(new int [] {num, 0});
            visited.add(num);
        }

        System.out.println(bfs());
    }

    public static long bfs() {

        long dist = 0;
        int cnt = 0;

        while(!q.isEmpty()) {
            int[] now = q.poll();

            for(int i = 0 ; i < 2 ; i++) {
                int nx = now[0] + dx[i];
                // 방문한 곳이면 넘겨
                if(visited.contains(nx)) continue;

                dist += now[1]+1;
                cnt++;

                visited.add(nx);

                if(cnt == k) return dist;
                q.add(new int[] {nx, now[1]+1});
            }
        }

        return dist;
    }

}