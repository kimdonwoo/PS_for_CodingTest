package shortest_distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            Arrays.fill(map[i],10000000);
            map[i][i] = 0;
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int val = Integer.parseInt(st.nextToken());

            map[start][end] = Math.min(map[start][end],val);
        }


        for(int k = 0 ; k < N ; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    map[i][j] = Math.min(map[i][j],map[i][k]+map[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(map[i][j] == 10000000) sb.append(0+" ");
                else sb.append(map[i][j]+" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }
}
