package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9205 {
    public static void main(String[] args) throws IOException{


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T --> 0){

            int n = Integer.parseInt(br.readLine());

            // 0 : start, n+1 : end
            boolean[][] map = new boolean[n+2][n+2];

            int[][] place = new int[n+2][2];

            for(int i = 0 ; i < n+2 ; i++){
                st = new StringTokenizer(br.readLine());
                place[i][0] = Integer.parseInt(st.nextToken());
                place[i][1] = Integer.parseInt(st.nextToken());
            }

            // 거리 체크
            for(int i = 0 ; i < n+2 ; i++){
                for(int j = 0 ; j < n+2 ; j++){
                    if(i == j) continue;
                    if(Math.abs(place[i][0]-place[j][0]) + Math.abs(place[i][1]-place[j][1]) <= 1000 ){
                        map[i][j] = true;
                    }
                }
            }

            // BFS ㄱㄱ
            Queue<Integer> q = new LinkedList<>();
            boolean[] visit = new boolean[n+2];
            visit[0] = true;
            q.add(0);
            boolean check = false;

            while(!q.isEmpty()){

                int now = q.poll();

                // 도착
                if(now == n+1){
                    sb.append("happy\n");
                    check = true;
                    break;
                }

                for(int i = 0 ; i < n+2 ;i++){
                    if(map[now][i] && !visit[i]){
                        visit[i] = true;
                        q.add(i);
                    }
                }
            }

            if(!check) sb.append("sad\n");

        }

        System.out.println(sb);

    }
}
