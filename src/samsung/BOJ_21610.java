package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_21610 {
    static int[][] map;
    static boolean[][] check;
    static ArrayList<int[]> clouds = new ArrayList<>();

    static int N,M;
    static int[] dx={0,-1,-1,-1,0,1,1,1};
    static int[] dy={-1,-1,0,1,1,1,0,-1};
    static int[] ddx={-1,-1,1,1};
    static int[] ddy={-1,1,1,-1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds.add(new int[] {N-1,0});
        clouds.add(new int[] {N-1,1});
        clouds.add(new int[] {N-2,0});
        clouds.add(new int[] {N-2,1});


        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            check = new boolean[N][N];

            // 1. 모든 구름이 d 방향만큼 s 칸 이동 + 2. 비가 내린다.
            for(int[] c : clouds){
                c[0] = (c[0]+dx[d]*s)%N;
                c[1] = (c[1]+dy[d]*s)%N;
                c[0] = c[0] < 0 ? c[0]+N : c[0];
                c[1] = c[1] < 0 ? c[1]+N : c[1];

                map[c[0]][c[1]]+=1;
                check[c[0]][c[1]] = true;
            }

            // 4. 물복사 버그
            for(int[] c : clouds){
                for(int j = 0 ; j < 4 ;j++){
                    int nx = c[0]+ddx[j];
                    int ny = c[1]+ddy[j];

                    if(nx >= 0 && nx < N && ny >= 0 && ny < N ){
                        if(map[nx][ny] > 0) map[c[0]][c[1]]++;
                    }
                }
            }

            clouds.clear();

            for(int j = 0 ; j < N ; j++){
                for(int k = 0 ; k < N ; k++){
                    if(map[j][k] >= 2 && !check[j][k]){
                        clouds.add(new int[] {j,k});
                        map[j][k] -= 2;
                    }
                }
            }
        }

        int sum = 0;

        for(int j = 0 ; j < N ; j++){
            for(int k = 0 ; k < N ; k++){
                sum += map[j][k];
            }
        }

        System.out.println(sum);

    }
}
