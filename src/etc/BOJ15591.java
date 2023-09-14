package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15591 {

    static int N,Q,count;
    static ArrayList<ArrayList<int[]>> map;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        for(int i = 0 ; i < N+1 ; i++){
            map.add(new ArrayList<>());
        }

        for(int i = 0 ; i < N-1 ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map.get(a).add(new int[] {b,val});
            map.get(b).add(new int[] {a,val});
        }

        for(int i = 0 ; i < Q ;i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            visited = new boolean[N+1];
            count = -1;
            // v 출발해서 DFS
            visited[v] = true;
            DFS(v,Integer.MAX_VALUE,k);

            System.out.println(count);

        }


    }

    static void DFS(int now, int minVal, int k){
        if(minVal >= k){
            count++;
        }

        for(int[] num : map.get(now)){
            if(!visited[num[0]]){
                visited[num[0]] = true;
                DFS(num[0],Math.min(minVal,num[1]),k);
            }

        }

    }

}
