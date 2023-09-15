package etc;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14466 {

    static int N,K,R;
    static int[][] map;

    static boolean[][] visited;
    // 동서남북
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static int[][] cow;
    static int count=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N*N 맵
        K = Integer.parseInt(st.nextToken()); // 소 K 마리
        R = Integer.parseInt(st.nextToken()); // 길 R개

        map = new int[N][N];

        for(int i = 0 ; i < R; i++){
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken())-1;
            int y1 = Integer.parseInt(st.nextToken())-1;
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;

            if(y2-y1==1 && x2-x1==0){ // 동(8)
                map[x1][y1]+=8;
                map[x2][y2]+=4;
            }else if(y2-y1==-1 && x2-x1==0){ // 서
                map[x1][y1]+=4;
                map[x2][y2]+=8;
            }else if(y2-y1==0 && x2-x1==1){ // 남
                map[x1][y1]+=2;
                map[x2][y2]+=1;
            }else{ // 북
                map[x1][y1]+=1;
                map[x2][y2]+=2;
            }
        }

        cow = new int[K][2];

        for(int i = 0 ; i < K; i++){
            st = new StringTokenizer(br.readLine());
            cow[i][0] = Integer.parseInt(st.nextToken())-1;
            cow[i][1] = Integer.parseInt(st.nextToken())-1;
        }

        for(int i = 0 ; i < K; i++){
            // 여기서 소 하나 뽑아서 visited 초기화하고 map길로 갈수있는 모든 곳 표시
            //cow[i]
            visited = new boolean[N][N];
            visited[cow[i][0]][cow[i][1]] = true;
            DFS(cow[i][0],cow[i][1]);

            // combination 돌면서 false 값가진 소 count++
            for(int j = i+1 ; j < K; j++){
                if(!visited[cow[j][0]][cow[j][1]]) count++;
            }
        }

        System.out.println(count);
    }

    static void DFS(int cow_x, int cow_y){

        int dir = map[cow_x][cow_y];
        for(int i = 0 ; i < 4;i++){ // i : 0 1 2 3
            int temp = (int) Math.pow(2,(3-i));
            if(dir/temp == 1){ // 갈수 없는 방향이면
                dir = dir - (dir/temp)*(temp);
                continue;
            }

            int next_x = cow_x+dx[i];
            int next_y = cow_y+dy[i];

            if(next_x >= 0 && next_x < N && next_y >= 0 && next_y < N){
                if(!visited[next_x][next_y]){
                    visited[next_x][next_y]=true;
                    DFS(next_x,next_y);
                }
            }

        }


    }
}