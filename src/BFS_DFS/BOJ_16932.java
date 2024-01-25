package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16932 {
    static int[][] map;
    static int N,M;
    static int[][] visit;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int res = 0;
    static int color = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] == 1){
                    BFS(i,j);
                }
            }
        }

        // 여기서 visit color 기억하기

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] == 0){
                    int temp = 1;
                    int[] colors = new int[4];
                    for(int k = 0 ; k < 4 ; k++){
                        int nx = i+dx[k];
                        int ny = j+dy[k];

                        if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                            for(int l = 0 ; l < 4 ; l++){
                               if(visit[nx][ny] == colors[l]) break;
                               if(l == 3){
                                   colors[k] = visit[nx][ny];
                                   temp+=map[nx][ny];
                               }
                            }
                        }
                    }
                    res = Math.max(res,temp);
                }
            }
        }


        System.out.println(res);

    }

    // 값바꾸기 흠...
    private static void BFS(int x, int y) {

        ArrayList<int[]> temps = new ArrayList<>();
        Queue<int[]> q = new LinkedList();
        visit[x][y] = 1;
        q.add(new int[]{x,y});
        temps.add(new int[]{x,y});

        int temp = 1;

        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                    if(map[nx][ny] == 1 && visit[nx][ny] == 0){
                        temp++;
                        visit[nx][ny] = 1;
                        q.add(new int[] {nx,ny});
                        temps.add(new int[]{nx,ny});
                    }
                }
            }
        }

        for(int[] t : temps){
            map[t[0]][t[1]] = temp;
            visit[t[0]][t[1]] = color;
        }
        color++;

    }
}
