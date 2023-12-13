package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891 {
    static int[][] state;
    static int[] idx ;
    public static void main(String[] args) throws IOException{

        state = new int[4][8];
        idx = new int[4];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        for(int i = 0 ; i < 4 ; i ++){
            String str = br.readLine();
            for(int j = 0 ; j < 8 ; j ++){
                state[i][j] = Integer.parseInt(str.substring(j,j+1));
            }
        }

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            int[] temp = new int[4];

            temp[target-1] = dir;

            // 다르면 반대회전
            if(target == 1){
                if(state[0][(idx[0]+2)%8] != state[1][(idx[1]+6)%8]){
                    temp[1] = temp[0] > 0 ? -1 : 1;
                    if(state[1][(idx[1]+2)%8] != state[2][(idx[2]+6)%8]){
                        temp[2] = temp[1] > 0 ? -1 : 1;
                        if(state[2][(idx[2]+2)%8] != state[3][(idx[3]+6)%8]){
                            temp[3] = temp[2] > 0 ? -1 : 1;
                        }
                    }
                }
            }else if(target == 4){
                if(state[3][(idx[3]+6)%8] != state[2][(idx[2]+2)%8]){
                    temp[2] = temp[3] > 0 ? -1 : 1;
                    if(state[2][(idx[2]+6)%8] != state[1][(idx[1]+2)%8]){
                        temp[1] = temp[2] > 0 ? -1 : 1;
                        if(state[1][(idx[1]+6)%8] != state[0][(idx[0]+2)%8]){
                            temp[0] = temp[1] > 0 ? -1 : 1;
                        }
                    }
                }
            }else if(target == 2){
                // 왼쪽
                if(state[1][(idx[1]+6)%8] != state[0][(idx[0]+2)%8]){
                    temp[0] = temp[1] > 0 ? -1 : 1;
                }
                // 오른쪽
                if(state[1][(idx[1]+2)%8] != state[2][(idx[2]+6)%8]){
                    temp[2] = temp[1] > 0 ? -1 : 1;
                    if(state[2][(idx[2]+2)%8] != state[3][(idx[3]+6)%8]){
                        temp[3] = temp[2] > 0 ? -1 : 1;
                    }
                }
            }else if(target == 3){
                // 왼쪽
                if(state[2][(idx[2]+6)%8] != state[1][(idx[1]+2)%8]){
                    temp[1] = temp[2] > 0 ? -1 : 1;
                    if(state[1][(idx[1]+6)%8] != state[0][(idx[0]+2)%8]){
                        temp[0] = temp[1] > 0 ? -1 : 1;
                    }
                }
                // 오른쪽
                if(state[2][(idx[2]+2)%8] != state[3][(idx[3]+6)%8]){
                    temp[3] = temp[2] > 0 ? -1 : 1;
                }
            }

            // temp에 따라 idx 수정
            for(int j = 0 ; j < 4 ; j++){
                if(temp[j] == 1){
                    idx[j] = (idx[j]+7) % 8;
                }else if(temp[j] == -1){
                    idx[j] = (idx[j]+1) % 8;
                }
            }

        }

        int sum = 0;
        int s = 1;
        for(int i = 0 ; i < 4 ; i++){
            if(state[i][idx[i]] == 1){
               sum+= s;
            }
            s*=2;
        }

        System.out.println(sum);

    }
}
