package Brute_Force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21278 {
    static int N,M;
    static ArrayList<Integer>[] city;

    static ArrayList<int[]> buildings = new ArrayList<>();
    static int[][] dist;
    static int[] select = new int[2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1. 먼저 2차원 배열로 map 만들기
        city = new ArrayList[N];
        for(int i = 0 ; i < N; i++){
            city[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            city[a].add(b);
            city[b].add(a);
        }

        // city로 최단 거리 알고리즘 적용하기
        dist = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
            dijkstra(i);
        }

        // 조합으로 건물 2개 선택하기
        combi(0,0);

        // 정렬하기
        Collections.sort(buildings,(o1,o2)->{
            if(o1[2] == o2[2]){
                if(o1[0] == o2[0]){
                    return o1[1]-o2[1];
                }else{
                    return o1[0]-o2[0];
                }
            }else {
                return o1[2]-o2[2];
            }
        });

        // 출력
        System.out.print((buildings.get(0)[0]+1)+" "+(buildings.get(0)[1]+1)+" "+buildings.get(0)[2]);
    }

    private static void combi(int idx, int count) {
        // 선택 완료
        if(count == 2){
            //4. buildings에 건물 번호 2개랑 최단거리 계산
            int distSum = 0;

            // dist 돌기
            for(int i = 0 ; i < N ; i++){
                if(i == select[0] || i == select[1]) continue;
                distSum += Math.min(dist[select[0]][i],dist[select[1]][i]);
            }

            distSum*=2;

            // 여기서 buildings 로직들어가기
            buildings.add(new int[] {select[0],select[1],distSum});

            return;
        }

        // 선택
        for(int i = idx; i < N ; i++){
            select[count] = i;
            combi(i+1,count+1);
        }
    }

    private static void dijkstra(int start) {

        boolean[] check = new boolean[N];

        dist[start][start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) ->{
            return o1[1] - o2[1];
        });
        pq.offer(new int[] {start,dist[start][start]});

        while(!pq.isEmpty()){
            int[] now = pq.poll();
            if(check[now[0]]) continue;
            check[now[0]] = true;

            for(int next : city[now[0]]){
                // 지금위치에서 갈수 있는 다음위치에 현재 dist랑 now거쳐서 next가는거랑 비교
                if(dist[start][next] > dist[start][now[0]] + 1 ){
                    dist[start][next] = dist[start][now[0]] + 1;
                    pq.add(new int[] {next, dist[start][next]});
                }
            }
        }
    }
}
