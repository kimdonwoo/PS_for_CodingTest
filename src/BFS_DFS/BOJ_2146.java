package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146 {
    static public class Move{
        int x;
        int y;
        int start;
        int cnt;

        Move(int x, int y, int start, int cnt){
            this.x = x;
            this.y = y;
            this.start = start;
            this.cnt = cnt;
        }
    }

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int N;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[N][N];
        Queue<Move> q = new LinkedList<>();

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                if(st.nextToken().equals("1"))
                    map[i][j] = true;
            }
        }

        // 먼저 각 섬에 다른 색 할당
        int[][] idxMap = new int[N][N];
        int idx = 1;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(map[i][j] && idxMap[i][j] == 0){
                    idxMap[i][j] = idx;
                    dfs(i,j,map,idxMap,idx);
                    idx++;
                }
            }
        }

        // start랑 값기억하기
        int[][][] cnt = new int[N][N][2];

        // 큐에 넣기
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(idxMap[i][j] > 0){
                    q.add(new Move(i,j,idxMap[i][j],0));
                }
            }
        }


        int res = 1000;

        while(!q.isEmpty()){
            // cnt마다 돌리기
            int temp = q.size();
            for(int k = 0 ; k < temp; k++){
                // x,y,color,cnt
                Move now = q.poll();

                for(int i = 0 ; i < 4 ; i++){
                    int nx = now.x+dx[i];
                    int ny = now.y+dy[i];

                    // 범위 밖이면 패스
                    if(nx < 0 || nx >= N || ny <0 || ny >= N) continue;
                    // 섬 내부면 패스
                    if(map[nx][ny]) continue;
                    // 같은 섬에서 온거면 패스
                    if(now.start == cnt[nx][ny][0]) continue;
                    // 만약 다른 섬이면
                    if(cnt[nx][ny][0] != 0){
                        res = Math.min(res,cnt[nx][ny][1] + now.cnt);
                    }else{
                        cnt[nx][ny][0] = now.start;
                        cnt[nx][ny][1] = now.cnt+1;
                        q.add(new Move(nx,ny,now.start,now.cnt+1));
                    }
                }
            }

            if(res != 1000) break;
        }


        System.out.println(res);

    }

    private static void dfs(int x, int y, boolean[][] map, int[][] idxMap, int idx) {

        for(int i = 0 ; i < 4 ; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx < 0 || nx >= N || ny <0 || ny >= N) continue;
            if(map[nx][ny] && idxMap[nx][ny] == 0){
                idxMap[nx][ny] = idx;
                dfs(nx,ny,map,idxMap,idx);
            }
        }
    }

}

