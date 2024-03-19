package samsung;

import java.util.*;
import java.io.*;

public class BOJ_13460 {
    public static class Turn{
        int turn;
        int redX;
        int redY;
        int blueX;
        int blueY;

        public Turn(int turn, int redX, int redY, int blueX, int blueY){
            this.turn = turn;
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
        }
    }
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static int[] hole = new int[2];
    static boolean[][] map;
    static Queue<Turn> q;
    static int N,M;
    static int isSuc = 0;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        map = new boolean[N][M];

        int[] red = new int[2];
        int[] blue = new int[2];

        // map의 false만 이동이 가능함
        for(int i = 0 ; i < N ; i++){
            String input = br.readLine();
            for(int j = 0 ; j < M ;j++){
                String temp = input.substring(j,j+1);
                if(temp.equals("#")){
                    map[i][j] = true;
                }else if(temp.equals("R")){
                    red[0] = i;
                    red[1] = j;
                }else if(temp.equals("B")){
                    blue[0] = i;
                    blue[1] = j;
                }else if(temp.equals("O")){
                    hole[0] = i;
                    hole[1] = j;
                }
            }
        }

        q = new LinkedList<>();
        q.add(new Turn(0,red[0],red[1],blue[0],blue[1]));

        while(!q.isEmpty()){
            Turn now = q.poll();

            // 10번까지만 움직이기
            if(now.turn == 10) break;

            // 여기서 map 4방향으로 돌리고 큐에 넣기
            move(now.turn, now.redX,now.redY,now.blueX,now.blueY);

        }

        System.out.println(-1);
    }

    private static void move(int turn, int redX, int redY, int blueX, int blueY) {
        int[] nextRed = null;
        int[] nextBlue = null;

        map[redX][redY] = true;
        map[blueX][blueY] = true;

        for(int i = 0 ; i < 4 ; i++){
            isSuc = 0;
            // 아래, 위, 오, 왼
            if(i == 0){
                // X가 더 큰놈 부터
                if(redX >= blueX){
                    map[redX][redY] = false;
                    nextRed = check(redX,redY,i,true,turn);
                    if(isSuc == 0) map[nextRed[0]][nextRed[1]] = true;
                    map[blueX][blueY] = false;
                    nextBlue = check(blueX,blueY,i,false,turn);
                    map[nextRed[0]][nextRed[1]] = false;
                }else{
                    map[blueX][blueY] = false;
                    nextBlue = check(blueX,blueY,i,false,turn);
                    if(isSuc > 0) continue;
                    map[nextBlue[0]][nextBlue[1]] = true;
                    map[redX][redY] = false;
                    nextRed = check(redX,redY,i,true,turn);
                    map[nextBlue[0]][nextBlue[1]] = false;
                }
            }else if(i == 1){
                // X가 더 작은 놈 부터
                if(redX <= blueX){
                    map[redX][redY] = false;
                    nextRed = check(redX,redY,i,true,turn);
                    if(isSuc == 0) map[nextRed[0]][nextRed[1]] = true;
                    map[blueX][blueY] = false;
                    nextBlue = check(blueX,blueY,i,false,turn);
                    map[nextRed[0]][nextRed[1]] = false;
                }else{
                    map[blueX][blueY] = false;
                    nextBlue = check(blueX,blueY,i,false,turn);
                    if(isSuc > 0) continue;
                    map[nextBlue[0]][nextBlue[1]] = true;
                    map[redX][redY] = false;
                    nextRed = check(redX,redY,i,true,turn);
                    map[nextBlue[0]][nextBlue[1]] = false;
                }
            }else if(i == 2){
                // Y가 더 큰놈 부터
                if(redY >= blueY){
                    map[redX][redY] = false;
                    nextRed = check(redX,redY,i,true,turn);
                    if(isSuc == 0) map[nextRed[0]][nextRed[1]] = true;
                    map[blueX][blueY] = false;
                    nextBlue = check(blueX,blueY,i,false,turn);
                    map[nextRed[0]][nextRed[1]] = false;
                }else{
                    map[blueX][blueY] = false;
                    nextBlue = check(blueX,blueY,i,false,turn);
                    if(isSuc > 0) continue;
                    map[nextBlue[0]][nextBlue[1]] = true;
                    map[redX][redY] = false;
                    nextRed = check(redX,redY,i,true,turn);
                    map[nextBlue[0]][nextBlue[1]] = false;
                }
            }else if(i == 3){
                // Y가 더 작은 놈 부터
                if(redY <= blueY){
                    map[redX][redY] = false;
                    nextRed = check(redX,redY,i,true,turn);
                    if(isSuc == 0) map[nextRed[0]][nextRed[1]] = true;
                    map[blueX][blueY] = false;
                    nextBlue = check(blueX,blueY,i,false,turn);
                    map[nextRed[0]][nextRed[1]] = false;
                }else{
                    map[blueX][blueY] = false;
                    nextBlue = check(blueX,blueY,i,false,turn);
                    if(isSuc > 0) continue;
                    map[nextBlue[0]][nextBlue[1]] = true;
                    map[redX][redY] = false;
                    nextRed = check(redX,redY,i,true,turn);
                    map[nextBlue[0]][nextBlue[1]] = false;
                }
            }

            /*
                isSuc
                    0 : 둘다 실패 ->
                    1 : red만 성공
                    2 : blue만 성공
                    3 : 둘다 성공 -> x
             */

            if (isSuc == 0){
                q.add(new Turn(turn+1,nextRed[0],nextRed[1],nextBlue[0],nextBlue[1]));
            }else if(isSuc == 1){
                System.out.println(turn+1);
                System.exit(0);
            }
        }
    }

    private static int[] check(int X, int Y, int dir, boolean isRed,int turn) {
        int[] res = {X,Y};

        while(true){
            int nextX = res[0]+dx[dir];
            int nextY = res[1]+dy[dir];

            // 불가능하면 탈출
            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) break;
            if(map[nextX][nextY]) break;

            res[0]+=dx[dir];
            res[1]+=dy[dir];

            // 여기서 체크 하자
            if(isRed){
                // red 구멍 통과
                if (res[0] == hole[0] && res[1] == hole[1]){
                    isSuc +=1;
                }
            }else{
                // blue 구멍 통과
                if (res[0] == hole[0] && res[1] == hole[1]){
                    isSuc+=2;
                }
            }
        }
        return res;
    }


}

/*
    내가 생각 못한거

    1. R이랑 B가 겹치는 상황
        와 이게 어렵네...
        R이랑 B는 동시에 움직이는데
        안겹치게 어떻게 처리해주지 ?

        방향에 따라 특정 값이 더 작은 구슬을 먼저 떨어뜨려줘야겠따!
        -> 해결

    2. 이동하면서 구멍으로 빠지는 상황

    3. R이랑 B가 같은 턴에 빠지면 안되나?
 */