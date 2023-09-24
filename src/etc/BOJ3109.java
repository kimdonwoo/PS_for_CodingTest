package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3109 {
    static boolean[][] map;
    static boolean[][] check;
    static int res = 0;
    static int[] dx = {-1,0,1};
    static int R,C;
    static boolean isSuccess;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // true가 벽임
        map = new boolean[R][C];
        check = new boolean[R][C];

        for(int i = 0 ; i < R ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < C ; j++){
                if(str.charAt(j) == 'x') map[i][j] = true;
            }
        }

        for(int i = 0 ; i < R ; i++){
            isSuccess = false;
            DFS(i,0);
            if(isSuccess) res++;
        }

        System.out.println(res);

    }

    static void DFS(int nowR, int nowC){
        // 끝까지 가거나 check가 true인곳에 도착하면 res++하고
        if(nowC == C-1){
            isSuccess = true;
            return;
        }

        for(int i = 0 ; i < 3; i++){
            int nextR = nowR + dx[i];
            if(nextR >= 0 && nextR < R){
                // map은 true는 벽이라 못감 + check는 true는 이미 지나간 길
                if(!map[nextR][nowC+1] && !check[nextR][nowC+1]){ // true면 벽임
                    check[nowR][nowC] = true;
                    DFS(nextR,nowC+1);
                    if(isSuccess){
                        return;
                    }
                }
            }
        }
    }
}
