package shortest_distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_14938 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        HashMap<Integer,Integer> item = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        int[][] map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            item.put(i,Integer.parseInt(st.nextToken()));
            Arrays.fill(map[i],100000);
            map[i][i] = 0;
        }


        for(int i = 0 ; i < R ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int dist = Integer.parseInt(st.nextToken());

            map[a][b] = dist;
            map[b][a] = dist;
        }


        for(int k = 0 ; k < N ; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int res = 0;

        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if(map[i][j] <= M){
                    count += item.get(j);
                }
            }
            res = Math.max(res,count);
        }


        System.out.println(res);

    }
}
