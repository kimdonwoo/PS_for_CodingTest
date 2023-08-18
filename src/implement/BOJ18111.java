package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18111 {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int max_height = Integer.MIN_VALUE;
        int min_height = Integer.MAX_VALUE;

        int[][] grounds = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                int num = Integer.parseInt(st.nextToken());
                max_height = Math.max(max_height,num);
                min_height = Math.min(min_height,num);
                grounds[i][j] = num;
            }
        }

        int res_time = Integer.MAX_VALUE;
        int res_h = 0;


        for(int h = max_height ; h >= min_height ; h--){
            int inven = B;
            int time = 0;
            // 높이보다 큰 것들 부터 인벤토리에 넣는 작업 + 시간 계산
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(grounds[i][j] > h){ // 높이보다 큰것들
                        inven += grounds[i][j]-h;
                        time = time + 2* (grounds[i][j]-h);
                    }
                }
            }
            // 높이보다 작은 것들에 놓기 + 시간 계산
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(grounds[i][j] < h){ // 높이보다 작은것들
                        inven -= h-grounds[i][j];
                        time = time + (h-grounds[i][j]);
                    }
                }
            }

            if(inven >= 0){
                if(res_time > time){
                    res_time = time;
                    res_h = h;
                }else if(res_time == time){
                    res_h = Integer.max(res_h,h);

                }
            }
        }

        System.out.println(res_time+" "+res_h);
    }
}
