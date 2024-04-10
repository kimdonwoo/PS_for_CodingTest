package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_23291 {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] bowls = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            bowls[0][i] = Integer.parseInt(st.nextToken());
        }

        int res = 0;
        while(true){
            res++;

            // 1. 물고기 넣기
            inputFish(bowls,N);

            // 2. 어항 쌓기 + 공중부양 이동
            rotate(bowls,N);

            // 3. 물고기의 수 조절
            moveFish(bowls,N);

            // 4. 바닥 깔기
            bowls = rowFish(bowls,N);

            // 5. 다시 공중부양
            rotate2(bowls,N);

            // 6. 물고기 수 조절
            moveFish(bowls,N);

            // 7. 바닥 깔기
            bowls = rowFish(bowls,N);

            if(countFish(bowls,N,K)) break;
        }

        System.out.println(res);

    }

    private static void inputFish(int[][] bowls, int N) {
        int temp = Integer.MAX_VALUE;
        for(int i = 0 ; i < N ; i++)
            temp = Math.min(temp,bowls[0][i]);
        for(int i = 0 ; i < N ; i++){
            if(bowls[0][i] == temp) bowls[0][i]++;
        }
    }

    private static boolean countFish(int[][] bowls, int n, int k) {

        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for(int i = 0 ; i < n ; i++){
            minVal = Math.min(minVal,bowls[0][i]);
            maxVal = Math.max(maxVal,bowls[0][i]);
        }

        if(maxVal - minVal <= k ) return true;
        return false;
    }

    private static void rotate(int[][] bowls, int N) {
        bowls[1][1] = bowls[0][0];
        bowls[0][0] = 0;

        int i1=0,j1=1,i2=1,j2=1;
        // 길이 : i2-i1+1
        while(true){
            int temp = j2-j1+1;
            int temp2 = j2+1;
            if((j2+1)+(i2-i1+1) <= N){

                for(int i = i1 ; i <= i2 ; i++){
                    for(int j = j1 ; j <= j2 ; j++){
                        // 이동시키고 0
                        bowls[(j2-j1)-(j-j1)+1][j2+i+1] = bowls[i][j];
                        bowls[i][j] = 0;
                    }
                }

                j1 = temp2; j2 = j1+i2; i2 = temp;

            }else{
                break;
            }
        }

    }

    private static void rotate2(int[][] bowls,int N) {
        for(int j = 0 ; j < N/2 ; j++){
            // 이동시키고 0
            bowls[1][N-1-j] = bowls[0][j];
            bowls[0][j] = 0;
        }

        for(int i = 0 ; i < 2; i++){
            for(int j = N/2 ; j < (N/2)+(N/2)/2 ; j++){
                // 이동시키고 0
                bowls[3-i][N+N/2-1-j] = bowls[i][j];
                bowls[i][j] = 0;
            }
        }
    }

    private static int[][] rowFish(int[][] bowls,int N) {
        int[][] newMap = new int[N][N];
        int k = 0;
        for(int j = 0 ; j < N ;j++){
            for(int i = 0 ; i < N ;i++){
                if(bowls[i][j] > 0){
                    newMap[0][k] = bowls[i][j];
                    k++;
                }
            }
        }
        return newMap;
    }

    private static void moveFish(int[][] bowls, int N) {

        // 물고기 이동
        int[][] temp = new int[N][N];
        for(int i = 0 ; i < N ;i++){
            for(int j = 0 ; j < N ;j++){
                if(bowls[i][j] > 0){
                    for(int k = 0 ; k < 4; k++){
                        // 4방향 비교
                        int nx = i+dx[k];
                        int ny = j+dy[k];

                        if(nx < 0 || nx >= N || ny <0 || ny>= N) continue;
                        if(bowls[nx][ny] > 0){
                            // 둘다 물고기 존재하면 큰값에서 작은 값 나눠
                            if(bowls[i][j] >= bowls[nx][ny]){
                                int d = (bowls[i][j] - bowls[nx][ny])/5;
                                temp[i][j] -= d;
                                temp[nx][ny] += d;
                            }else{
                                int d = (bowls[nx][ny] - bowls[i][j])/5;
                                temp[i][j] += d;
                                temp[nx][ny] -= d;
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0 ; i < N ;i++){
            for(int j = 0 ; j < N ;j++){
                bowls[i][j] += temp[i][j]/2;
            }
        }
    }

}
