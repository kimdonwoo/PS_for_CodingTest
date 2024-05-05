package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BOJ_11559 {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[][] map;
    static int row,col,res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        row = 12;
        col = 6;
        map = new int[row][col];

        for(int i = 0 ; i < row ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < col ; j++){
                char c = str.charAt(j);
                if(c == 'R'){
                    map[i][j] = 1;
                }else if(c == 'G'){
                    map[i][j] = 2;
                }else if(c == 'B'){
                    map[i][j] = 3;
                }else if(c == 'P'){
                    map[i][j] = 4;
                }else if(c == 'Y'){
                    map[i][j] = 5;
                }
            }
        }

        while(true){

            // 1. 터질 뿌요 찾기
            Stack<int[]> stack = new Stack<>();
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visit = new boolean[row][col];

            findPuyo(stack,q,visit);

            // 2. 하나도 안터졌으면 break
            if(stack.size() == 0) break;
            else{
                BombPuyo(stack);
            }
            // 3. 중력적용하기
            gravityPuyo();

        }

        System.out.println(res);
    }

    private static void gravityPuyo() {
        for(int i = row-1 ; i >= 0 ; i--){
            for(int j = 0 ; j < col ; j++) {
                if(map[i][j] != 0){
                    int now = i;
                    while(now+1 < row){
                        if(map[now+1][j] == 0)
                            now++;
                        else break;
                    }

                    if(now != i){
                        map[now][j] = map[i][j];
                        map[i][j] = 0;
                    }

                }

            }
        }
    }

    private static void findPuyo(Stack<int[]> stack, Queue<int[]> q, boolean[][] visit){

        for(int i = 0 ; i < row ; i++){
            for(int j = 0 ; j < col ; j++) {
                if(map[i][j] != 0 && !visit[i][j]){
                    // 1. 먼저 큐에 넣기
                    int cnt = 0;
                    q.add(new int[]{i,j});
                    visit[i][j] = true;
                    stack.add(new int[]{i,j});

                    // 2. 꺼내면서 cnt++
                    while(!q.isEmpty()){

                        int[] now = q.poll();
                        cnt++;

                        for(int k = 0 ; k < 4; k++){
                            int nx = now[0]+dx[k];
                            int ny = now[1]+dy[k];

                            if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
                            if(!visit[nx][ny] && map[nx][ny] == map[i][j] ){
                                q.add(new int[]{nx,ny});
                                visit[nx][ny] = true;
                                stack.add(new int[]{nx,ny});
                            }

                        }
                    }

                    // 만약 cnt가 4안넘으면
                    if(cnt < 4){
                        for(int k = 0 ; k < cnt ; k++){
                            stack.pop();
                        }
                    }
                }
            }
        }
    }


    private static void BombPuyo(Stack<int[]> stack){
        res++;
        while(!stack.isEmpty()){
            int[] now = stack.pop();
            map[now[0]][now[1]] = 0;
        }
    }

}

