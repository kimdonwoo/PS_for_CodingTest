package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5547 {
    static int W,H;
    static int[][] map;
    static boolean[][] visit;
    static int res = 0;
    static int[][] d_even = {{-1,-1},{-1,0},{0,1},{1,0},{1,-1},{0,-1}};
    static int[][] d_odd = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
            벽면 흠..

            칸을 한칸 씩 더 넓게 해서 (0,0)에서 BFS를 돌려버려
            6방향으로 BFS 돌리고 각 노드마다 6방향으로 체크하다가 특정 방향에
            벽이 존재하면 더하기만하고 들어가지는 x
         */

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 4,8 ->
        map = new int[H+2][W+2];
        visit = new boolean[H+2][W+2];

        for(int i = 0 ; i < H ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < W ; j++){
                map[i+1][j+1] = Integer.parseInt(st.nextToken());
            }
        }

        visit[0][0] = true;
        DFS(0,0);

        System.out.println(res);

    }

    static void DFS(int x, int y){

        // 짝수면
        if(x%2 == 0){
            for(int i = 0 ; i < 6 ; i++){
                int nx = x+d_even[i][0];
                int ny = y+d_even[i][1];

                if(nx >= 0 && nx <=H+1 && ny >= 0 && ny <=W+1){
                    if(!visit[nx][ny] && map[nx][ny] == 0){
                        visit[nx][ny] = true;
                        DFS(nx,ny);
                    }else if(map[nx][ny] == 1){
                        res++;
                    }
                }
            }
        }else{
            for(int i = 0 ; i < 6 ; i++){
                int nx = x+d_odd[i][0];
                int ny = y+d_odd[i][1];

                if(nx >= 0 && nx <=H+1 && ny >= 0 && ny <=W+1){
                    if(!visit[nx][ny] && map[nx][ny] == 0){
                        visit[nx][ny] = true;
                        DFS(nx,ny);
                    }else if(map[nx][ny] == 1){
                        res++;
                    }
                }
            }
        }
    }
}
