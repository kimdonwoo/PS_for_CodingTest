package BFS_DFS;


import java.io.*;
import java.util.*;


public class BOJ_2178 {
    static boolean visited[][];
    static int ground[][];

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n][m];
        ground = new int[n][m];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j = 0 ; j < m ; j++){
                 ground[i][j] = Integer.parseInt(line.substring(j,j+1));
            }
        }

        BFS(0,0);

        System.out.println(ground[n-1][m-1]);

    }

    private static void BFS(int i, int j){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i,j});
        visited[i][j] = true;

        while(!q.isEmpty()){
            int now[] = q.poll();

            for(int k =0; k< 4; k++){
                int next_x = now[0]+dx[k];
                int next_y = now[1]+dy[k];

                if(next_x >= 0 && next_x <n && next_y >= 0 && next_y < m){
                    if(ground[next_x][next_y]!=0 && !visited[next_x][next_y]){
                        visited[next_x][next_y] = true;
                        ground[next_x][next_y] = ground[now[0]][now[1]] + 1;
                        q.add(new int[] {next_x,next_y});
                    }
                }

            }

        }

    }
}
