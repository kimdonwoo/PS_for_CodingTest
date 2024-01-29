import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14391 {
    static int N,M;
    static int[][] map;
    static int res = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(str.substring(j,j+1));
            }
        }

        boolean[][] visit = new boolean[N][M];

        DFS(0,visit,0);

        System.out.println(res);

    }

    private static void DFS(int now, boolean[][] visit, int sum) {
        if(now == N*M){
            res = Math.max(res,sum);
            return;
        }

        int x = now/M;
        int y = now%M;

        if(visit[x][y]){
            DFS(now+1,visit,sum);
        }else{
            // 가로
            int temp = 0;
            int i ;
            for(i = y ; i < M;i++){
                // 만약 막힌곳이면 그까지하고
                if(visit[x][i]) break;
                // 안막혔으면 계속 DFS 들어가기
                temp = temp*10 + map[x][i];

                // new visit
                visit[x][i] = true;
                DFS(now+1,visit,sum+temp);
            }

            for(int j = y ; j < i; j++) visit[x][j] = false;

            // 세로
            temp = 0;
            for(i = x ; i < N;i++){
                // 만약 막힌곳이면 그까지하고
                if(visit[i][y]) break;
                // 안막혔으면 계속 DFS 들어가기
                temp = temp*10 + map[i][y];

                // new visit
                visit[i][y] = true;
                DFS(now+1,visit,sum+temp);
            }

            for(int j = x ; j < i; j++) visit[j][y] = false;

        }
    }
}
