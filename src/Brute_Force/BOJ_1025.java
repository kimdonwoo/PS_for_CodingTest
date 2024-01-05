package Brute_Force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1025 {
    static int N,M;
    static int[][] map;
    static long res = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(str.substring(j,j+1));
                if(map[i][j] == 0 || map[i][j] == 1 ||map[i][j] == 4 ||map[i][j] == 9){
                    res = Math.max(res,map[i][j]);
                }
            }
        }

        // 숫자 만들기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int dx = 0; dx < N; dx++) {
                    for (int dy = 0; dy < M; dy++) {
                        if(dx == 0 && dy == 0) continue;
                        else if(dx > 0 && dy == 0){
                            findNumber(i,j,dx,dy); // 아래로
                            findNumber(i,j,-1*dx,dy); // 위로
                        }else if(dy > 0 && dx == 0){
                            findNumber(i,j,dx,dy); // 오른쪽
                            findNumber(i,j,dx,-1*dy); // 왼쪽
                        }else{
                            findNumber(i,j,dx,dy); // 오아래
                            findNumber(i,j,-1*dx,-1*dy); // 왼위
                            findNumber(i,j,dx,-1*dy); // 왼아래
                            findNumber(i,j,-1*dx,dy); // 오위
                        }
                    }
                }
            }
        }

        System.out.println(res);


    }

    private static void findNumber(int i, int j, int dx, int dy) {
        long temp = map[i][j];
        int nx = i;
        int ny = j;

        while(true){

            nx +=dx;
            ny +=dy;

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
            temp = temp*10 + map[nx][ny];

            if(res > temp) continue;
            else{
                if((long)Math.sqrt(temp)*(long)Math.sqrt(temp) == temp){
                    // Max 비교
                    res = temp;
                }
            }

        }

    }
}
