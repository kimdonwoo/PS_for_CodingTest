package samsung;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    내가 어려워 한 이유
    -> 가장 가까운 물고기를 먹으러 갈때
    -> 특정 물고기를 먹으러 가는도중에 다른 물고기를 먹을 걱정 x
 */

public class BOJ_16236 {
    static int N;
    static int[][] map;
    static int dx[] = {-1, 0, 1, 0}; //위 왼 아래 오
    static int dy[] = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    q.add(new int[] {i,j,0});
                    map[i][j] = 0;
                }
            }
        }

        int eat = 0;
        int time = 0;
        int age = 2;

        while(true){
            ArrayList<int []> fish = new ArrayList<>();
            int[][] dist = new int[N][N];

            while (!q.isEmpty()) {
                int[] now = q.poll();

                for(int i=0; i<4; i++){
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];

                    if(nx >= 0 && ny >= 0 && nx < N && ny < N){
                        if(dist[nx][ny]==0 && map[nx][ny] <= age){
                            // 범위 안에 있으면서
                            // dist는 이번 턴 거리 정보
                            // 나이 작은 물고기일때
                            dist[nx][ny] = dist[now[0]][now[1]] + 1;
                            q.add(new int[] {nx,ny,dist[nx][ny]});

                            // 물고기 나이가 더 작으면 먹어버림
                            // 먹으면 fish에 추가
                            if(1 <= map[nx][ny] && map[nx][ny] <= 6 && map[nx][ny] < age)
                                fish.add(new int[] {nx, ny, dist[nx][ny]});
                        }
                    }
                }
            }

            // 아무것도 못먹으면 return
            if(fish.size() == 0){
                System.out.println(time);
                return;
            }

            // 먹을 수 있는 물고기들중에
            // 조건 만족하는 한마리 찾기 !
            Collections.sort(fish,(o1,o2)->{
                // dist가 작은 물고기
                // 가장 위에 있는 물고기
                // 가장 왼쪽에 있는 물고기
                if(o1[2] == o2[2]){
                    if(o1[0] == o2[0]){
                        return o1[1] - o2[1];
                    }else{
                        return o1[0] - o2[0];
                    }
                }else{
                    return o1[2] - o2[2];
                }
            });


            // 해당 물고기 먹어버리기 -> 시간 추가, eat++;
            // 먹으면 레벨업
            int[] nowfish = fish.get(0);

            time += nowfish[2];
            eat++;
            map[nowfish[0]][nowfish[1]] = 0;
            if(eat == age) {
                age++;
                eat = 0;
            }
            // 큐에 현재 위치 추가
            q.add(new int[] {nowfish[0],nowfish[1],0});
        }
    }
}