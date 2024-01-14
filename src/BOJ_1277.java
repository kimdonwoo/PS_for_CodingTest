import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1277 {
    static class plant{

        public int num;
        public double dist;

        public plant(int num, double dist){
            this.num = num;
            this.dist = dist;
        }
    }
    static int[][] map;
    static int N,W;
    static double M;
    static double doubleM;
    static ArrayList<plant>[] plants;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        M = Double.parseDouble(br.readLine());
        doubleM = M*M;

        map = new int[N][2];

        // N개의 줄에 발전소 위치 넣음
        for(int i = 0 ; i < N ;i++){
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }

        // 다익스트라를 위한 인접리스트 만들기 초기화
        plants = new ArrayList[N];
        for(int i = 0 ; i < N ;i++){
            plants[i] = new ArrayList<>();
        }

        // 여기서 계산 + 전선(W : 10_000) 놓기 구현

        // 전선 다 추가
        // 전선에 없으면 계산했을때 M^2보다 작으면 넣기
        // 전선 구현
        boolean[][] free = new boolean[N][N];
        for(int i = 0 ; i < W ;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            free[x][y] = true;
            free[y][x] = true;
        }

        // plants 만들기
        for(int i = 0 ; i < N ; i++){
            for(int j = i+1 ; j < N ; j++){
                if(free[i][j]){
                    plants[i].add(new plant(j,0));
                    plants[j].add(new plant(i,0));
                }else{
                    // 여기서 map[i]랑 map[j] 거리가 M보다 작으면 plants 넣기 가능
                    double temp = Math.pow(map[i][0] - map[j][0], 2) + Math.pow(map[i][1] - map[j][1], 2);
                    if(temp <= doubleM){
                        plants[i].add(new plant(j,temp));
                        plants[j].add(new plant(i,temp));
                    }
                }
            }
        }

        // plants로 다익스트라 구현
        dijkstra(0);

    }

    public static void dijkstra(int start){

        double[] dist = new double[N];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[start] = 0;

        boolean[] visit = new boolean[N];
        PriorityQueue<plant> pq = new PriorityQueue<>((o1,o2)->{
            return (int) (o1.dist - o2.dist);
        });
        pq.add(new plant(start,0));


        while(!pq.isEmpty()){
            plant now = pq.poll();

            if(visit[now.num]) continue;
            visit[now.num] = true;

            // 만약 N-1이면 출력하기
            if(now.num == N-1){
                System.out.println((int)(dist[N-1]*1000));
                return;
            }

            for(plant p : plants[now.num]){
                // 여기서 다익스트라 공식 + doubleM 적용
                if(dist[p.num] > dist[now.num] + Math.sqrt(p.dist)){
                    dist[p.num] = dist[now.num] + Math.sqrt(p.dist);
                    pq.add(new plant(p.num,dist[p.num]));
                }
            }
        }
    }
}
