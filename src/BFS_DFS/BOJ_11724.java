package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11724 {
    /**
     *
     * 여기서 인접 리스트 만드는 방법 익히기 !!!
     *
     *
     */
    static boolean visited[];
    static ArrayList<Integer>[] A;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];
        A = new ArrayList[n+1];

        for(int i = 1; i < n+1 ; i++){
            A[i] =new ArrayList<Integer>();
        }

        for(int i= 0; i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[a].add(b);
            A[b].add(a);
        }

        int count = 0;

        for(int i=1; i<n+1; i++){
            if(!visited[i]){
                count++;
                DFS(i);
            }
        }
        System.out.println(count);
    }

    private static void DFS(int v) {
        if(visited[v]) return;
        visited[v] = true;

        for(int i :A[v]){
            if(!visited[i]) DFS(i);
        }
    }
}
