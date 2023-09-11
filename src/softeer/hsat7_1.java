package softeer;

import java.util.*;
import java.io.*;


public class hsat7_1
{
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<int[]> goal;
    static int count = 0;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int n,m;

    public static void main(String args[]) throws IOException
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visited= new boolean[n][n];
        goal = new ArrayList<>();

        for(int i = 0 ; i < n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < m;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            goal.add(new int[] {x-1,y-1});
            map[x-1][y-1] = 2;
        }

        int[] temp = goal.get(0);
        int[] goal_temp = goal.get(1);
        visited[temp[0]][temp[1]] = true;
        map[goal_temp[0]][goal_temp[1]] = 0;
        DFS(1,temp[0],temp[1],goal_temp[0],goal_temp[1]);

        System.out.println(count);
    }

    static void DFS(int depth, int now_x,int now_y,int goal_x,int goal_y){

        for(int i = 0 ; i < 4 ; i++){
            int next_x = now_x+dx[i];
            int next_y = now_y+dy[i];

            if(next_x >= 0 && next_x < n && next_y >= 0 && next_y < n){
                if(!visited[next_x][next_y] && map[next_x][next_y] == 0){
                    // 방문안했고 0이면 들어가
                    if(next_x == goal_x && next_y == goal_y){

                        if(depth == goal.size()-1) { // 찾았으면
                            count++;
                            map[goal_x][goal_y] = 2;
                            visited[goal_x][goal_y] = false;
                            return;
                        }else{
                            visited[next_x][next_y] = true;
                            int[] goal_temp = goal.get(depth+1);
                            map[goal_temp[0]][goal_temp[1]] = 0;
                            DFS(depth+1,next_x,next_y,goal_temp[0],goal_temp[1]);
                            map[goal_temp[0]][goal_temp[1]] = 2;
                            visited[next_x][next_y] = false;
                        }
                    }else {
                        visited[next_x][next_y] = true;
                        DFS(depth, next_x, next_y, goal_x, goal_y);
                        visited[next_x][next_y] = false;
                    }
                }
            }

        }

    }

}

