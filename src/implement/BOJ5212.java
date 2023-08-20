package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ5212 {

    static char[][] grid;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        HashMap<int[], Boolean> islands = new HashMap<>();

        grid = new char[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int j = 0 ; j < M ; j++){
                grid[i][j]=str.charAt(j);

                if(str.charAt(j) == 'X'){
                    islands.put(new int[] {i,j},true);
                }
            }
        }

        for(int[] key : islands.keySet()){
            if(!check(key[0],key[1])){ // 잠기는 애들
                //grid[key[0]][key[1]] = '.';
                islands.put(key,false);
            }
        }


        int x_min = Integer.MAX_VALUE, x_max = Integer.MIN_VALUE;
        int y_min= Integer.MAX_VALUE, y_max = Integer.MIN_VALUE;

        for(int[] key : islands.keySet()){
            if(islands.get(key)){
                if(x_min > key[0]) x_min = key[0];
                if(x_max < key[0]) x_max = key[0];
                if(y_min > key[1]) y_min = key[1];
                if(y_max < key[1]) y_max = key[1];
            }else{
                grid[key[0]][key[1]] = '.';
            }
        }



        for(int i = x_min ; i < x_max+1 ; i++){
            for(int j = y_min ; j < y_max+1 ; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    static boolean check(int x , int y){
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        int count = 0;
        // 4방 검사
        for(int i = 0 ; i < 4 ; i++){
            int next_x = x+dx[i];
            int next_y = y+dy[i];

            if(next_x < 0 || next_x>=N || next_y < 0 || next_y >=M){ // 범위 벗어나면
                count++;
                continue;
            }

            if(grid[next_x][next_y] == '.') { // 이게 grid 범위를 벗어나거나 . 일경우 ++
                count++;
            }
        }

        if(count >= 3) return false;
        return true;

    }

}
