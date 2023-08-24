package implement;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16926 {
    static int[][] map;
    static int nums;
    static int N , M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int count = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 맵 초기화
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nums = Math.min(N, M) / 2;

        for(int i = 0 ; i < count; i++){
            rotate();
        }

        // 여기서 출력
        for(int j=0; j<N; j++) {
            for(int k=0; k<M; k++) {
                System.out.print(map[j][k] + " ");
            }
            System.out.println();
        }
    }

    public static void rotate(){
        /*      <--
            (00) 01 02 03 04
             10 11 12 13 14
             20 21 22 23 24
             30 31 32 33 34
         */

        // 라인마다 돌리기
        for(int i=0; i<nums; i++) {
            // 맨마지막 원소 저장해두기
            int temp = map[i][i];

            // 일단 i를 생각하지 않고 이동 방식만 생각한다
            // 그리고 j의 범위를 정하고
            // i값이 어떤식으로 정해질지 생각
            // 즉 작은것부터 천천히 생각하기!!!

            // 북쪽 줄
            for(int j=i+1; j<M-i; j++)
                map[i][j-1] = map[i][j];

            // 동쪽 줄
            for(int j=i+1; j<N-i; j++)
                map[j-1][M-1-i] = map[j][M-1-i];

            // 남쪽 줄
            for(int j=M-2-i; j>=i; j--)
                map[N-1-i][j+1] = map[N-1-i][j];

            // 서쪽 줄
            for(int j=N-2-i; j>=i; j--)
                map[j+1][i] = map[j][i];

            // 저장해둔거 넣기
            map[i+1][i] = temp;
        }

    }
}
