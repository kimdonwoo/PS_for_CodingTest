package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1987 {
    static HashMap<Character,Boolean> hm = new HashMap<>();
    static int res = 0,N,M;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static char[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = str.charAt(j);
            }
        }

        hm.put(map[0][0],true);
        dfs(0,0,1);
        hm.remove(map[0][0]);

        System.out.println(res);

    }

    private static void dfs(int x, int y, int cnt) {

        res = Math.max(res,cnt);

        for(int i = 0 ; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if(!hm.containsKey(map[nx][ny])){
                hm.put(map[nx][ny],true);
                dfs(nx,ny,cnt+1);
                hm.remove(map[nx][ny]);
            }
        }
    }
}
