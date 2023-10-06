package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {

    static int m,n,h;
    static int tomato[][][];
    static Queue<int[]> queue = new LinkedList<>();

    static int[] dr={1,-1,0,0,0,0};
    static int[] dc={0,0,1,-1,0,0};
    static int[] dh={0,0,0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        tomato=new int[h][n][m];

        for(int i = 0 ; i < h ; i++){ // 2
            for(int j = 0 ; j < n ; j++){ // 3
                st = new StringTokenizer(br.readLine());
                for(int k = 0 ; k < m ; k++){ // 5
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    // 익은거 큐에 넣기
                    if(tomato[i][j][k] > 0) queue.add(new int[] {i,j,k});
                }
            }
        }

        System.out.println(bfs());


    }

    static int bfs(){

        while(!queue.isEmpty()){
            // 토마토 하나 꺼냄
            int[] now = queue.poll();

            for(int i = 0 ; i < 6 ; i++){
                int nh = now[0]+dh[i];
                int nr = now[1]+dr[i];
                int nc = now[2]+dc[i];

                if(nh>=0 && nr>=0 && nc>=0 && nh < h && nr < n && nc < m){
                    if(tomato[nh][nr][nc] == 0){
                        tomato[nh][nr][nc] = tomato[now[0]][now[1]][now[2]]+1;
                        queue.add(new int[] {nh,nr,nc});
                    }
                }
            }

        }

        // 최대 토마토 + 안익은 토마토 확인해보기
        int res = Integer.MIN_VALUE;

        for(int i = 0 ; i < h ; i++){ // 2
            for(int j = 0 ; j < n ; j++){ // 3
                for(int k = 0 ; k < m ; k++){ // 5
                    if(tomato[i][j][k] == 0){
                        return -1;
                    }else if(tomato[i][j][k] > 0){
                        res=Math.max(res,tomato[i][j][k]);
                    }
                }
            }
        }

        return res-1;
    }
}
