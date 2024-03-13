package unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16562 {
    static int[] parents;
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int friendMoney[] = new int[N+1];
        parents = new int[N+1];

        for(int i=1; i<=N; i++) {
            parents[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            friendMoney[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b, friendMoney);
        }

        int sum = 0;

        for(int i=1; i<=N; i++) {
            if(parents[i] == i) {
                sum += friendMoney[i];
            }
        }

        if(k < sum) {
            System.out.println("Oh no");
        }else{
            System.out.println(sum);
        }
    }

    private static void union(int a, int b, int[] friendMoney) {
        int rootA = find(a);
        int rootB = find(b);
        // 현재 다르단건 2개를 연결 시켜줘야함
        if(rootA != rootB) {
            if(friendMoney[rootA] > friendMoney[rootB]) {
                parents[rootA] = rootB;
            }
            else {
                parents[rootB] = rootA;
            }
        }
    }

    private static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}

//public class unionfind.BOJ_16562 {
//    static ArrayList<Integer>[] graph;
//    static int[] moneys;
//    static int res = 0;
//    static int minCost;
//    static boolean[] visit;
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        int k = Integer.parseInt(st.nextToken());
//
//        graph = new ArrayList[N];
//        moneys = new int[N];
//        st = new StringTokenizer(br.readLine());
//
//        for(int i = 0 ; i < N ; i ++){
//            graph[i] = new ArrayList<>();
//            moneys[i] = Integer.parseInt(st.nextToken());
//        }
//
//        for(int i = 0 ; i < M ; i++){
//            st = new StringTokenizer(br.readLine());
//            int start = Integer.parseInt(st.nextToken())-1;
//            int end = Integer.parseInt(st.nextToken())-1;
//
//            graph[start].add(end);
//            graph[end].add(start);
//        }
//
//        visit = new boolean[N];
//
//        for(int i = 0 ; i < N ; i++){
//            if(!visit[i]){
//                // 방문 안했으면 방문해라
//                minCost = moneys[i];
//                visit[i] = true;
//                DFS(i);
//                res+=minCost;
//            }
//        }
//
//
//        if(k >= res){
//            System.out.println(res);
//        }else{
//            System.out.println("Oh no");
//        }
//
//    }
//
//    private static void DFS(int start){
//
//        for(int next : graph[start]){
//            if(!visit[next]){
//                minCost = Math.min(minCost ,moneys[next]);
//                visit[next] = true;
//                DFS(next);
//            }
//        }
//    }
//}
