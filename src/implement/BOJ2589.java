package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2589 {
    static int count = 0;
    static boolean[][] map;
    static boolean[][] visited;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int N,M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N  = Integer.parseInt(st.nextToken());
        M  = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        visited = new boolean[N][M];

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                // 육지에 true
                if(str.charAt(j) == 'L') map[i][j] = true;
            }
        }


        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j]){ // 여기 조건 더 넣기 ..?
                    count = Math.max(count,BFS(i,j));
                    visited = new boolean[N][M];
                }
            }
        }

        System.out.println(count);

    }

    public static int BFS(int i , int j){
        Queue<int[]> q = new LinkedList<>();
        int res = 0;

        q.add(new int[] {i,j,0});
        visited[i][j] = true;

        while(!q.isEmpty()){ // 큐가 빌때까지
            int[] now = q.poll();
            res = now[2];

            for(int k = 0 ; k < 4 ; k++){
                int next_x = now[0]+dx[k];
                int next_y = now[1]+dy[k];

                if(next_x >= 0 && next_x < N && next_y >= 0 && next_y < M){
                    if(map[next_x][next_y] && !visited[next_x][next_y]){
                        visited[next_x][next_y] = true;
                        q.add(new int[]{next_x,next_y,now[2]+1});

                    }
                }
            }
        }

        return res;
    }
}
