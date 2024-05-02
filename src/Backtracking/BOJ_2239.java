package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2239 {

    static int[][] map;
    static HashMap<Integer,Boolean>[] row;
    static HashMap<Integer,Boolean>[] col;
    static HashMap<Integer,Boolean>[][] box;

    static boolean check = false;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];
        row = new HashMap[9];
        col = new HashMap[9];
        box = new HashMap[3][3];
        for(int i = 0 ; i < 9 ; i++){
            row[i] = new HashMap<>();
            col[i] = new HashMap<>();
        }

        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                box[i][j] = new HashMap<>();
            }
        }

        for(int i = 0 ; i < 9 ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < 9 ; j++){
                int temp = Integer.parseInt(str.substring(j,j+1));
                map[i][j] = temp;

                if(temp != 0){
                    col[j].put(temp,true);
                    row[i].put(temp,true);
                    box[i/3][j/3].put(temp,true);
                }
            }
        }


        // 값찾기
        // 그리디 실패 -> 백트래킹
        sdokuDFS(0);

        // 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                sb.append(map[i][j]);
            }
            if(i != 8) sb.append("\n");
        }

        System.out.println(sb);

    }

    private static void sdokuDFS(int now) {

        if(now == 81){
            check = true;
            return;
        }

        // 현재 위치
        int x = now/9;
        int y = now%9;

        if(map[x][y] != 0){
            sdokuDFS(now+1);
        }else{
            for(int k = 1 ; k <= 9 ; k++){
                if(col[y].containsKey(k)) continue;
                if(row[x].containsKey(k)) continue;
                if(box[x/3][y/3].containsKey(k)) continue;

                // 백트래킹
                map[x][y] = k;
                col[y].put(k,true);
                row[x].put(k,true);
                box[x/3][y/3].put(k,true);
                sdokuDFS(now+1);
                if(check) return;
                map[x][y] = 0;
                col[y].remove(k);
                row[x].remove(k);
                box[x/3][y/3].remove(k);
            }
        }
    }
}

/*
    그니깐 스도쿠는 그리디가 아니다
    dfs가 되어야할듯?


 */