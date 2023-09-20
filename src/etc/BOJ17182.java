package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ17182 {

    static int[][] map;
    static boolean[] visit;
    static int N , res=Integer.MAX_VALUE , sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        int now = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visit = new boolean[N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 0 ; k < N ; k++){
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < N ; j++){
                    // map[i][k]+map[k][j] 랑 map[i][j] 비교
                    if(i==j) continue;
                    map[i][j] = Math.min(map[i][k]+map[k][j],map[i][j]);
                }
            }
        }

        visit[now]=true;
        DFS(now , 1);

        System.out.println(res);
    }

    // now에서 시작해서
    static void DFS(int start,int depth){

        if(depth == N){
            res = Math.min(res,sum);
            return;
        }

        for(int i = 0 ; i < N ; i++){
            if(!visit[i]){
                    visit[i] = true;
                    sum+=map[start][i];
                    DFS(i,depth+1);
                    sum-=map[start][i];
                    visit[i] = false;
            }
        }
    }

}
