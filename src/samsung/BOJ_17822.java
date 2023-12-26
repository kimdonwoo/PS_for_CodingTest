package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17822 {
    static int N,M,T;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx ={0,0,1,-1};
    static int[] dy ={1,-1,0,0};
    static int trans,sum,count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N+1][M];

        for(int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M ; j++){
                map[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < T ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 배열 이동
            move(x,d,k);

            // 이동 시키고 값 수정
            sum = 0;
            count = 0;
            boolean flag = false;
            visit = new boolean[N+1][M];
            for(int j = 1 ; j <= N ; j++){
                for(int l = 0 ; l < M ; l++){
                    if(map[j][l] != 0){
                        trans = 0;
                        sum+=map[j][l];
                        count++;
                        visit[j][l] = true;
                        dfs(j,l);
                        if(trans > 0){
                            flag = true;
                            map[j][l] = 0;
                        }
                    }
                }
            }

            // 만약 아무도 없는 경우에는
            // 평균보다 큰수에서 1을 빼고 작은 수에서 1 더하기
            if(!flag){
                double avg = (double)sum / (double)count;
                for(int j = 1 ; j <= N ; j++){
                    for(int l = 0 ; l < M ; l++){
                        if(map[j][l] != 0){
                            if((double)map[j][l] > avg) map[j][l]-=1;
                            else if((double)map[j][l] < avg) map[j][l]+=1;
                        }
                    }
                }
            }
        }

        // 합
        int res = 0;
        for(int i = 1 ; i <= N ; i++){
            for(int j = 0 ; j < M ; j++){
                res += map[i][j];
            }
        }

        System.out.println(res);

    }

    private static void dfs(int x, int y) {

        for(int i = 0 ; i < 4; i++){
            int nx = x+dx[i];
            int ny = (y+dy[i]+M)%M;

            if(nx > 0 && nx <= N){
                if(map[x][y] == map[nx][ny] && !visit[nx][ny]){
                    trans++;
                    visit[nx][ny] = true;
                    dfs(nx,ny);
                    map[nx][ny] = 0;
                }
            }

        }


    }

    private static void move(int x, int d, int k) {

        int[] temp = new int[M];

        if(d == 1) k = M-k;

        // N 보다 작은 x 배수 옮기기
        for(int i = x ; i <= N ; i+=x){
            // 시계방향으로 k번 움직이기
            // TODO : 여기 더빠르게 할 수 있는데.. 흠..
            for(int j = 0 ; j < M ; j++){
                temp[j] =map[i][(j-k+M)%M];
            }

            for(int j = 0 ; j < M ; j++){
                map[i][j] = temp[j];
            }
        }
    }
}









