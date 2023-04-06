package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14620 {

    static int n;
    static int[][] map;
    static int[] dx ={1,0,-1,0};
    static int[] dy ={0,1,0,-1};
    static boolean[][] visited;

    private static int res = Integer.MAX_VALUE;

    public static void ps_14620(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);

        System.out.println(res);

    }

    private static void dfs(int level, int sum) {
        if(level == 3){
            res = Math.min(res, sum);
            return;
        }

        for(int i=1; i<n-1; i++) {
            for(int j=1; j<n-1; j++) {
                if(isPossible(i,j)){
                    int tmp_sum = get_sum(i,j);
                    check_visited(i,j,true);
                    dfs(level+1,sum+tmp_sum);
                    check_visited(i,j,false);
                }
            }
        }
    }

    public static void check_visited(int x, int y,boolean flag) {
        if(flag) {
            visited[x][y] =true;
            for(int i=0; i<4; i++) {
                visited[x+dx[i]][y+dy[i]] = true;
            }
        }
        else {
            visited[x][y]= false;
            for(int i=0; i<4; i++) {
                visited[x+dx[i]][y+dy[i]]= false;
            }
        }
    }

    public static int get_sum(int x, int y) {
        int sum = map[x][y];
        for(int i=0; i<4; i++) {
            sum+=map[x+dx[i]][y+dy[i]];
        }
        return sum;
    }


    public static boolean isPossible(int x, int y) {
        if(visited[x][y]) {
            return false;
        }
        for(int i=0; i<4; i++) {
            if(visited[x+dx[i]][y+dy[i]]) {
                return false;
            }
        }
        return true;
    }
}
