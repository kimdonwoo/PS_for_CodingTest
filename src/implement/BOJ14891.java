package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891 {

    static boolean[][] tire;
    static int[] up;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        tire = new boolean[4][8];
        up = new int[4];

        // 세팅
        for(int i = 0 ; i < 4 ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < 8 ; j++){
                if(Integer.parseInt(str.substring(j,j+1)) == 0){
                    tire[i][j] = false;
                }else{
                    tire[i][j] = true;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());

            int ord = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());

            rotate(ord,dir);
        }


        // 마지막 점수 계산 1이면 true S
        int sum = 0;
        for(int i = 0 ; i < 4 ; i++){
            if(tire[i][up[i]]){ // true일때 더하기
                sum+=Math.pow(2,i);
            }
        }
        System.out.println(sum);

    }

    public static void rotate(int ord, int dir){

        // 난 먼저 돌리고 했는데.. 흠..
        if(dir == 1){
            // 시계 방향으로 돌리기
            //up[ord] = (up[ord] + 7) % 8;
            //int right = (up[ord]+2)%8;
            //int left = (up[ord]+6)%8;

            if(ord == 0){
                if(tire[ord][(up[ord]+2)%8] != tire[ord+1][(up[ord+1]+6)%8]){ // 같으면 패스, 다르면 반대 회전
                    //up[ord+1] = (up[ord+1] + 1) % 8;
                    if(tire[ord+1][(up[ord+1]+2)%8] != tire[ord+2][(up[ord+2]+6)%8]){
                        //up[ord+2] = (up[ord+2] + 7) % 8;
                        if(tire[ord+2][(up[ord+2]+2)%8] != tire[ord+3][(up[ord+3]+6)%8]){
                            up[ord+3] = (up[ord+3] + 1) % 8;
                        }
                        up[ord+2] = (up[ord+2] + 7) % 8;
                    }
                    up[ord+1] = (up[ord+1] + 1) % 8;
                }
                up[ord] = (up[ord] + 7) % 8;
            }else if(ord == 1){
                if(tire[ord][(up[ord]+2)%8] != tire[ord+1][(up[ord+1]+6)%8]){ // 같으면 패스, 다르면 반대 회전
                    //up[ord+1] = (up[ord+1] + 1) % 8;
                    if(tire[ord+1][(up[ord+1]+2)%8] != tire[ord+2][(up[ord+2]+6)%8]){
                        up[ord+2] = (up[ord+2] + 7) % 8;
                    }
                    up[ord+1] = (up[ord+1] + 1) % 8;
                }
                if(tire[ord][(up[ord]+6)%8] != tire[ord-1][(up[ord-1]+2)%8]){ // 같으면 패스, 다르면 반대 회전
                    up[ord-1] = (up[ord-1] + 1) % 8;
                }
                up[ord] = (up[ord] + 7) % 8;
            }else if(ord == 2){
                if(tire[ord][(up[ord]+2)%8] != tire[ord+1][(up[ord+1]+6)%8]){ // 같으면 패스, 다르면 반대 회전
                    up[ord+1] = (up[ord+1] + 1) % 8;
                }
                if(tire[ord][(up[ord]+6)%8] != tire[ord-1][(up[ord-1]+2)%8]){ // 같으면 패스, 다르면 반대 회전
                    if(tire[ord-1][(up[ord-1]+6)%8] != tire[ord-2][(up[ord-2]+2)%8]){ // 같으면 패스, 다르면 반대 회전
                        up[ord-2] = (up[ord-2] + 7) % 8;
                    }
                    up[ord-1] = (up[ord-1] + 1) % 8;
                }
                up[ord] = (up[ord] + 7) % 8;
            }else if(ord == 3){
                if(tire[ord][(up[ord]+6)%8] != tire[ord-1][(up[ord-1]+2)%8]){ // 같으면 패스, 다르면 반대 회전
                    if(tire[ord-1][(up[ord-1]+6)%8] != tire[ord-2][(up[ord-2]+2)%8]){
                        if(tire[ord-2][(up[ord-2]+6)%8] != tire[ord-3][(up[ord-3]+2)%8]){
                            up[ord-3] = (up[ord-3] + 1) % 8;
                        }
                        up[ord-2] = (up[ord-2] + 7) % 8;
                    }
                    up[ord-1] = (up[ord-1] + 1) % 8;
                }
                up[ord] = (up[ord] + 7) % 8;
            }

        }else{ // 반시계 방향으로 돌리기
            //int right = (up[ord]+2)%8;
            //int left = (up[ord]+6)%8;
            if(ord == 0){
                if(tire[ord][(up[ord]+2)%8] != tire[ord+1][(up[ord+1]+6)%8]){ // 같으면 패스, 다르면 반대 회전
                    //up[ord+1] = (up[ord+1] + 7) % 8;
                    if(tire[ord+1][(up[ord+1]+2)%8] != tire[ord+2][(up[ord+2]+6)%8]){
                        //up[ord+2] = (up[ord+2] + 1) % 8;
                        if(tire[ord+2][(up[ord+2]+2)%8] != tire[ord+3][(up[ord+3]+6)%8]){
                            up[ord+3] = (up[ord+3] + 7) % 8;
                        }
                        up[ord+2] = (up[ord+2] + 1) % 8;
                    }
                    up[ord+1] = (up[ord+1] + 7) % 8;
                }
                up[ord] = (up[ord] + 1) % 8;
            }else if(ord == 1){
                if(tire[ord][(up[ord]+2)%8] != tire[ord+1][(up[ord+1]+6)%8]){ // 같으면 패스, 다르면 반대 회전
                    if(tire[ord+1][(up[ord+1]+2)%8] != tire[ord+2][(up[ord+2]+6)%8]){
                        up[ord+2] = (up[ord+2] + 1) % 8;
                    }
                    up[ord+1] = (up[ord+1] + 7) % 8;
                }
                if(tire[ord][(up[ord]+6)%8] != tire[ord-1][(up[ord-1]+2)%8]){ // 같으면 패스, 다르면 반대 회전
                    up[ord-1] = (up[ord-1] + 7) % 8;
                }
                up[ord] = (up[ord] + 1) % 8;
            }else if(ord == 2){
                if(tire[ord][(up[ord]+2)%8] != tire[ord+1][(up[ord+1]+6)%8]){ // 같으면 패스, 다르면 반대 회전
                    up[ord+1] = (up[ord+1] + 7) % 8;
                }
                if(tire[ord][(up[ord]+6)%8] != tire[ord-1][(up[ord-1]+2)%8]){ // 같으면 패스, 다르면 반대 회전
                    if(tire[ord-1][(up[ord-1]+6)%8] != tire[ord-2][(up[ord-2]+2)%8]){ // 같으면 패스, 다르면 반대 회전
                        up[ord-2] = (up[ord-2] + 1) % 8;
                    }
                    up[ord-1] = (up[ord-1] + 7) % 8;
                }
                up[ord] = (up[ord] + 1) % 8;
            }else if(ord == 3){
                if(tire[ord][(up[ord]+6)%8] != tire[ord-1][(up[ord-1]+2)%8]){ // 같으면 패스, 다르면 반대 회전
                    //up[ord-1] = (up[ord-1] + 7) % 8;
                    if(tire[ord-1][(up[ord-1]+6)%8] != tire[ord-2][(up[ord-2]+2)%8]){
                        //up[ord-2] = (up[ord-2] + 1) % 8;
                        if(tire[ord-2][(up[ord-2]+6)%8] != tire[ord-3][(up[ord-3]+2)%8]){
                            up[ord-3] = (up[ord-3] + 7) % 8;
                        }
                        up[ord-2] = (up[ord-2] + 1) % 8;
                    }
                    up[ord-1] = (up[ord-1] + 7) % 8;
                }
                up[ord] = (up[ord] + 1) % 8;
            }

        }


    }
}
