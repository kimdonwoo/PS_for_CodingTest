package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20055 {
    static int N,K;
    static int[] belt;
    static boolean[] robots;
    static int res = 1 , now = 0;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        belt = new int[2*N];
        robots = new boolean[N];

        for(int i = 0 ; i < 2*N ; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }

        while(true){

            //1. 벨트가 각 칸 위에 있는 로봇과 함께 한칸 회전한다
            now = (now + (2*N) -1) % (2*N);
            for(int i = N-2 ; i >= 0 ; i--){
                robots[i+1] = robots[i];
            }
            robots[N-1] = false;
            robots[0] = false;

            //2. 로봇이 한칸 이동할 수 있으면(앞에 로봇 x, 내구도 1이상) 이동한다
            for(int i = N-2 ; i >= 0 ; i--){
                //robots[i]의 belt 인덱스 : (now + i) % N
                if(robots[i]){
                    if(!robots[i+1] && belt[(now + i+1) % (2*N)] >= 1 ){
                        robots[i+1] = true;
                        robots[i] = false;
                        belt[(now + i+1) % (2*N)]--;
                    }
                }
            }
            robots[N-1] = false;

            //3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 로봇 올린다
            if(!robots[0] && belt[now] >= 1){
                robots[0] = true;
                belt[now]--;
            }

            //4. 내구도 0인 칸 K개 이상이면 종료
            int check = 0;
            for(int i = 0 ; i < 2*N ; i++){
                if(belt[i] == 0) check++;
            }

            //5. 탈출
            if(check >= K){
                break;
            }

            res++;
        }

        System.out.println(res);

    }
}
