package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10836 {
    static int M, N;
    static int[][] map;
    static int[] temp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][M];
        temp = new int[2*M-1];

        for(int i = 0 ; i < M ; i ++){
            Arrays.fill(map[i],1);
        }

        for(int i = 0 ; i < N ; i++){ // 1_000_000
            st = new StringTokenizer(br.readLine());

            // 여기서 temp 만들기 (여기서 시간단축 O(n) or O(logn))
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            // temp 만든다음
            int j = 0;
            while(zero != 0){
                temp[j]+=0;
                j++;
                zero--;
            }

            while(one != 0){
                temp[j]+=1;
                j++;
                one--;
            }

            while(two != 0){
                temp[j]+=2;
                j++;
                two--;
            }

        }

        // temp map에다가 합치기
        for(int i = 0 ; i < M ; i++){
            map[M-1-i][0] += temp[i];
        }
        // temp : 0,1,2 ... ,M-1 / M , M+1 , ... 2M-2
        for(int i = 0 ; i < M ; i++){
            for(int j = 1 ; j < M ; j++){
                map[i][j] += temp[M+j-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if(i != 0 && j != 0) {
                    sb.append(map[0][j]).append(" ");
                } else sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
