import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;

public class BOJ_3055 {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N =Integer.parseInt(st.nextToken());
        int M =Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visit = new boolean[N][M];
        int[] start = null;
        Queue<int[]> water = new LinkedList<>();
        int res = -1;

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                char c = str.charAt(j);
                if(c == 'D'){ // 비버굴
                    map[i][j] = 2;
                }else if(c == 'X'){ // 돌
                    map[i][j] = -1;
                }else if(c == '*'){ // 물
                    map[i][j] = 1;
                    water.add(new int[]{i,j});
                }else if(c == 'S'){ // 고슴도치
                    start = new int[]{i,j};
                }
            }
        }


        // BFS
        Queue<int[]> move = new LinkedList<>();
        move.add(new int[] {start[0],start[1],0});
        visit[start[0]][start[1]] = true;

        while(!move.isEmpty()){

            // 1. 현재 move에 있는 모든 값 움직이기
            // 이때 자기 위치에서 갈 수 있는 4방향에 굴 존재하면 break;
            int moveSize = move.size();
            for(int i = 0 ; i < moveSize; i++){
                int[] now = move.poll();

                if(map[now[0]][now[1]] == 2){
                    res = now[2];
                    break;
                }else if(map[now[0]][now[1]] == 1){
                    continue;
                }

                for(int j = 0 ; j < 4 ; j++){
                    int nx = now[0]+dx[j];
                    int ny = now[1]+dy[j];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if((map[nx][ny] == 0 || map[nx][ny] == 2) && !visit[nx][ny]){
                        move.add(new int[]{nx,ny,now[2]+1});
                        visit[nx][ny] = true;
                    }// 물이나 돌이면 패스
                }
            }

            if(res > 0) break;

            // 2. 물 퍼트리기
            int waterSize = water.size();
            for(int i = 0 ; i < waterSize; i++){
                int[] nowWater = water.poll();

                for(int j = 0 ; j < 4 ; j++){
                    int nx = nowWater[0]+dx[j];
                    int ny = nowWater[1]+dy[j];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if(map[nx][ny] == 0){
                        map[nx][ny] = 1;
                        water.add(new int[]{nx,ny});
                    }
                }
            }

        }


        if(res > 0){
            System.out.println(res);
        }else{
            System.out.println("KAKTUS");
        }

    }
}
