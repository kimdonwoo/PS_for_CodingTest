package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16918 {

    /*
        폭탄은 3초후 폭발
            폭발 시 4방향이 빈칸 (폭탄이 있어도 빈칸이 됨 연쇄반응 x)

        제일 처음 특정 위치에 설치
        1초
            x
        2초
           나머지 칸에 모두 폭탄 설치
        3초
            ㅇㅎ 이걸 무한 반복


        1초가 O랑 X로 차있음
        2초떄 O 터짐 / 4초때 X 터짐 ...

     */
    static char[][] grounds;
    static int R;
    static int C;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // R*C
        R =Integer.parseInt(st.nextToken());
        C =Integer.parseInt(st.nextToken());

        int N =Integer.parseInt(st.nextToken());

        grounds = new char[R][C];

        // grounds 초기 세팅 완료
        for(int i = 0 ; i < R ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < C ; j++){
                grounds[i][j] = str.charAt(j);
            }
        }

        // N이 3부터

        for(int i = 1 ; i <= N-1 ; i++){
            // i초때 벌어질 상황 구현
            if(i%4 == 1){
                full('X');
            }else if(i%4 == 2){
                bomb('O');
            }else if(i%4 == 3){
                full('O');
            }else if(i%4 == 0){
                bomb('X');
            }
        }

        // 출력하기
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                if(grounds[i][j] == '.'){
                    System.out.print(grounds[i][j]);
                }else{
                    System.out.print('O');
                }
            }
            System.out.println();
        }



    }

    /*
        1초때 X가 채워짐, 3초때 O가 채워짐 5 7반복
        2초떄 O 터짐 / 4초때 X 터짐 ...

     */

    public static void bomb(char ch){
        // 만약 ch가 O 라면

        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                if(grounds[i][j] == ch){
                    // 여기서 O 주변이 . 이거나 X 일때만 터트리고 O 일때는 그대로 나둠
                    for(int k = 0 ; k < 4; k++){
                        int next_x = i+dx[k];
                        int next_y = j+dy[k];
                        if(0 <= next_x && next_x <R && 0 <= next_y && next_y < C ){ // 모두 범위 이내
                            if(grounds[next_x][next_y] == '.' || grounds[next_x][next_y] == (ch=='O'? 'X':'O')){
                                grounds[next_x][next_y] = '.';
                            }
                        }
                    }
                }
            }
        }

        // 여기서 모든 O를 .로 바꿔줌
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                if(grounds[i][j] == ch){
                    grounds[i][j] = '.';
                }
            }
        }

    }

    public static void full(char ch){

        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                if(grounds[i][j] == '.'){
                    grounds[i][j] = ch;
                }
            }
        }

    }




}
