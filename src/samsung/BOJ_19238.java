package samsung;

import java.io.*;
import java.util.*;

public class BOJ_19238 {
    static int N,M,fuel;
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static HashMap<Integer,int[]> customer = new HashMap<>();
    static int[] taxi = new int[2];
    static boolean res = true;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 1) map[i][j] = -1;
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi[0] = Integer.parseInt(st.nextToken())-1;
        taxi[1] = Integer.parseInt(st.nextToken())-1;

        for(int i = 1 ; i < M+1 ; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken())-1;
            int y1 = Integer.parseInt(st.nextToken())-1;
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;

            customer.put(i,new int[] {x1,y1,x2,y2});
            map[x1][y1] = i;
        }

        // M번 왕복

        for(int i = 0 ; i < M ; i++){

            // 최단 거리 태울 승객 찾기 (BFS) - 연료빼고(이때 -되면 break) 택시 이동시키기
            int nowCustomer = searchCustomer();
            if(nowCustomer == -1){
                res = false;
                break;
            }

            // 태우고나면 최단거리로 목적지 찾기 (BFS) -
            moveCustomer(nowCustomer);
            if(!res) break;

        }

        if(!res) System.out.println(-1);
        else System.out.println(fuel);
    }

    private static void moveCustomer(int nowCustomer) {
        //customer.get(nowCustomer)[2],customer.get(nowCustomer)[3]까지 도착해야함

        if(taxi[0] == customer.get(nowCustomer)[2] && taxi[1] == customer.get(nowCustomer)[3])
            return;

        boolean[][] visit = new boolean[N][N];
        int dist = 0;

        Queue<int[]> q = new LinkedList<>();
        visit[taxi[0]][taxi[1]] = true;
        map[taxi[0]][taxi[1]] = 0;
        q.add(new int[] {taxi[0],taxi[1],0});
        res = false;
        while(!q.isEmpty()) {
            int[] now = q.poll();

            if(now[0] == customer.get(nowCustomer)[2] && now[1] == customer.get(nowCustomer)[3]){
                // 도착시
                dist = now[2];
                if(fuel < dist ){
                    res = false;
                }else{
                    fuel+=dist;
                    taxi[0] = now[0];
                    taxi[1] = now[1];
                    customer.remove(nowCustomer);
                    res = true;
                }
                break;
            }

            for(int i = 0 ; i < 4; i++){
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N ){
                    if(!visit[nx][ny] && map[nx][ny] != -1){ // 빈 칸이면
                        visit[nx][ny] = true;
                        q.add(new int[] {nx,ny,now[2]+1});

                    }
                }
            }
        }
    }

    private static int searchCustomer() {
        // BFS로 현재 taxi[0],taxi[1]로부터 최단 거리 찾기
        if(map[taxi[0]][taxi[1]] > 0) return map[taxi[0]][taxi[1]];

        int dist = Integer.MAX_VALUE;

        ArrayList<int[]> select = new ArrayList<>();
        boolean[][] visit = new boolean[N][N];

        Queue<int[]> q = new LinkedList<>();
        visit[taxi[0]][taxi[1]] = true;
        q.add(new int[] {taxi[0],taxi[1],0});

        while(!q.isEmpty()){
            int[] now = q.poll();
            if(now[2] >= dist) continue;

            for(int i = 0 ; i < 4; i++){
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N ){
                    if(!visit[nx][ny] && map[nx][ny] == 0){ // 빈 칸이면
                        visit[nx][ny] = true;
                        q.add(new int[] {nx,ny,now[2]+1});
                    }else if(!visit[nx][ny] && map[nx][ny] > 0){ // 사람 찾으면
                        visit[nx][ny] = true;
                        dist = now[2]+1;
                        select.add(new int[] {nx,ny});
                    }
                }
            }
        }

        if(select.size() == 0) return -1;

        if(fuel < dist) return -1;

        // select 중에 하나 선택
        Collections.sort(select,(o1,o2)->{
            if(o1[0] == o2[0])
                return o1[1]-o2[1];
            return o1[0]-o2[0];
        });

        // 택시 이동
        fuel -= dist;
        taxi[0] = select.get(0)[0];
        taxi[1] = select.get(0)[1];

        return map[select.get(0)[0]][select.get(0)[1]];

    }
}
