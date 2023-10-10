package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_13023 {
    static int N,M;
    static ArrayList<Integer>[] friends;
    static boolean[] visit;
    static boolean res = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        friends = new ArrayList[N];
        visit = new boolean[N];

        for(int i = 0 ; i < N ; i++){
            friends[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friends[a].add(b);
            friends[b].add(a);
        }

        for(int i = 0 ; i < N ; i++){
            if(res) break;
            visit[i] = true;
            DFS(i,1);
            visit[i] = false;
        }

        if(res){
            System.out.println(1);
        }else{
            System.out.println(0);
        }

    }

    static void DFS(int now ,int depth){
        if(res) return;

        if(depth == 5){
            res = true;
            return;
        }

        for(int next : friends[now]){
            if(!visit[next]){
                // 방문 안했으면
                visit[next] = true;
                DFS(next,depth+1);
                visit[next] = false;
            }
        }
    }
}
