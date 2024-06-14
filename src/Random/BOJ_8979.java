package Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class BOJ_8979 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] nations = new int[N][5];

        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            nations[i][0] = Integer.parseInt(st.nextToken());
            nations[i][1] = Integer.parseInt(st.nextToken()); // 금
            nations[i][2] = Integer.parseInt(st.nextToken()); // 은
            nations[i][3] = Integer.parseInt(st.nextToken()); // 동

        }

        Arrays.sort(nations,(o1,o2) -> {
           if(o1[1] == o2[1]){
               if(o1[2] == o2[2]){
                   return o2[3] - o1[3];
               }
               return o2[2] - o1[2];
           }
           return o2[1] - o1[1];
        });

        nations[0][4] = 1;
        for(int i = 1 ; i < N ; i++){
            // 이전이랑 같으면 그 값넣기
            if(nations[i-1][1] == nations[i][1] && nations[i-1][2] == nations[i][2] && nations[i-1][3] == nations[i][3]){
                nations[i][4] = nations[i-1][4];
            }else{
                nations[i][4] = i+1;
            }
        }

        for(int i = 0 ; i < N ; i++){
            if(nations[i][0] == K){
                System.out.println(nations[i][4]);
            }
        }

    }
}
