package Topological;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2623 {
    static ArrayList<Integer>[] singer;
    static int[] topology;
    static ArrayList<Integer> res = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        topology = new int[N];
        singer = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            singer[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken()) - 1;

            for(int j = 0 ; j < cnt-1 ; j++){
                int now = Integer.parseInt(st.nextToken()) - 1;

                // start -> now
                singer[start].add(now);
                topology[now]++;

                start = now;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0 ; i < N ; i++){
            if(topology[i] == 0) q.add(i);
        }

        // 불가능한 경우
        // 1. 다 선택했는데 큐가 남아 있는 경우 ?
        // 2. 아직 다 선택 안했는데 큐가 비어 있는 경우 ? (이거인가 ?)
        while(!q.isEmpty()){

            int v = q.poll();

            // v 출력
            res.add(v);

            for(int next : singer[v]){
                topology[next]--;
                if(topology[next] == 0){
                    q.add(next);
                }
            }
        }

        if (res.size() != N){
            System.out.println(0);
        }else{
            for(int r : res){
                System.out.println(r+1);
            }
        }

    }
}
