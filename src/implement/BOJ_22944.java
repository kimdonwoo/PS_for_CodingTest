package implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_22944 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static class node{
        int i;
        int j;
        int h;
        int time;
        int um;

        node(int i, int j, int h, int time, int um){
            this.i = i;
            this.j = j;
            this.h = h;
            this.time = time;
            this.um = um;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken()); // 체력
        int D = Integer.parseInt(st.nextToken()); // 내구도

        boolean[][] map = new boolean[N][N];
        int[][] check = new int[N][N];
        int[] start = null, end= null;

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < N ; j++){
                String temp = str.substring(j,j+1);
                if(temp.equals("S")){ // 현재
                    start = new int[]{i, j};
                }else if(temp.equals("U")){
                    map[i][j] = true;
                }else if(temp.equals("E")){ // 도착
                    end = new int[]{i, j};
                }
            }
        }

        // BFS (i,j,체력,걸린시간,우산 내구도)
        Queue<node> q = new LinkedList<>();
        q.add(new node(start[0],start[1],H+1,0,0));
        check[start[0]][start[1]] = H;

        while(!q.isEmpty()){

            node now = q.poll();

            // 도착
            if(now.i == end[0] && now.j == end[1]){
                System.out.println(now.time);
                return;
            }

            // 현재 위치에 우산 있는지 없는지에 따라 다르지
            if(map[now.i][now.j]){
                // map에 우산 있으면 우산들기
                now.um = D;
            }

            // 죽음의 비 맞음 판단
            if(now.um > 0 ){
                now.um--;
            }else{
                now.h--;
                if(now.h == 0) continue;
            }

            // 이동
            for(int i = 0 ; i < 4; i++){
                int nextI = now.i+dx[i];
                int nextJ = now.j+dy[i];

                if(nextI >= 0 && nextI < N && nextJ >= 0 && nextJ < N){
                    // 여기서 현재 우산 유무에 따라 다름
                    if(check[nextI][nextJ] < now.h+now.um){
                        check[nextI][nextJ] = now.h+now.um;
                        q.add(new node(nextI,nextJ,now.h,now.time+1,now.um));
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
