package 완전탐색_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 백준_2606{
    static boolean[] visited;
    static ArrayList<Integer>[] A;
    static int count;
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        A = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i = 1; i < N+1 ; i++){
            A[i] = new ArrayList<Integer>();
        }

        for(int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()); // 시작점
            int e = Integer.parseInt(st.nextToken()); // 종료점

            A[s].add(e); // 방향없을 때는 양쪽다 해줘야함 (중요!)
            A[e].add(s);

        }

        count = 0;

        DFS(1);

        System.out.println(count);
    }

    private static void DFS(int v) {
        visited[v] = true;

        // 현재 위치에서 연결된곳 확인
        for(int i : A[v]){
            if(!visited[i]){
                count ++;
                DFS(i);
            }
        }


    }
}
