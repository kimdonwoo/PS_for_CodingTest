package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_23289 {

    // 오왼위아
    static int[][] airRangeDx = {{-1,0,1},{-1,0,1},{-1,-1,-1},{1,1,1}};
    static int[][] airRangeDy = {{1,1,1},{-1,-1,-1},{-1,0,1},{-1,0,1}};
    static int[] airDx = {0,0,-1,1};
    static int[] airDy = {1,-1,0,0};


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        int[][] warmArea = new int[R][C];
        int[][] wallBitMap = new int[R][C];
        ArrayList<int[]> airCondition = new ArrayList<>();
        ArrayList<int[]> checkArea = new ArrayList<>();

        for(int i = 0 ; i < R ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < C ; j++){
                int temp = Integer.parseInt(st.nextToken());

                if(temp == 5){
                    checkArea.add(new int[]{i,j});
                }else if(temp >= 1 && temp <= 4){
                    // 위치랑 방향 저장
                    airCondition.add(new int[]{i,j,temp-1});
                }
            }
        }

        int W = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < W ; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int wall = Integer.parseInt(st.nextToken());

            if(wall == 0){
                if(x > 0) wallBitMap[x-1][y]+=8; // 아래로 불가능
                wallBitMap[x][y]+=4; // 위로 불가능
            }else{
                if(y < C-1) wallBitMap[x][y+1]+=2; // 왼쪽 불가능
                wallBitMap[x][y]+=1; // 오른쪽 불가능
            }
        }

        // 1. 여기서 온도캐시 맵 초기화
        for(int[] now : airCondition){
            int nowX = now[0];
            int nowY = now[1];
            // 오 왼 위 아
            int dir = now[2];

            // bfs 돌리기 (현재 위치랑, val)
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visit = new boolean[R][C];
            int nx = nowX+airDx[dir];
            int ny = nowY+airDy[dir];
            if(nx >= 0 && nx < R && ny >= 0 && ny < C){
                // 여기다 벽도 없어야함
                if((wallBitMap[nowX][nowY] & (1<<dir)) == 0){
                    visit[nx][ny] = true;
                    warmArea[nx][ny]+=5;
                    q.add(new int[]{nx,ny,5});
                }
            }


            // 3방향으로
            while(!q.isEmpty()){
                int[] wind = q.poll();

                // 3곳 조사
                for(int i = 0 ; i < 3; i++){
                    nx = wind[0]+airRangeDx[dir][i];
                    ny = wind[1]+airRangeDy[dir][i];

                    if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                    if(visit[nx][ny]) continue;

                    // 벽이면 continue
                    if(i == 1){
                        if((wallBitMap[wind[0]][wind[1]] & (1<<dir)) > 0) continue;
                    }else{
                        if(dir == 0){ // 오
                            if(i ==0){
                                if((wallBitMap[nx][ny] & (1<<1)) > 0) continue;
                                if((wallBitMap[wind[0]][wind[1]] & (1<<2)) > 0) continue;
                            }else{
                                if((wallBitMap[nx][ny] & (1<<1)) > 0) continue;
                                if((wallBitMap[wind[0]][wind[1]] & (1<<3)) > 0) continue;
                            }
                        }else if(dir == 1){ // 왼
                            if(i ==0){
                                if((wallBitMap[nx][ny] & 1) > 0) continue;
                                if((wallBitMap[wind[0]][wind[1]] & (1<<2)) > 0) continue;
                            }else{
                                if((wallBitMap[nx][ny] & 1) > 0) continue;
                                if((wallBitMap[wind[0]][wind[1]] & (1<<3)) > 0) continue;
                            }
                        }else if(dir == 2){ // 위
                            if(i ==0){
                                if((wallBitMap[nx][ny] & (1<<3)) > 0) continue;
                                if((wallBitMap[wind[0]][wind[1]] & (1<<1)) > 0) continue;
                            }else{
                                if((wallBitMap[nx][ny] & (1<<3)) > 0) continue;
                                if((wallBitMap[wind[0]][wind[1]] & 1) > 0) continue;
                            }
                        }else if(dir == 3){ // 아
                            if(i ==0){
                                if((wallBitMap[nx][ny] & (1<<2)) > 0) continue;
                                if((wallBitMap[wind[0]][wind[1]] & (1<<1)) > 0) continue;
                            }else{
                                if((wallBitMap[nx][ny] & (1<<2)) > 0) continue;
                                if((wallBitMap[wind[0]][wind[1]] & 1) > 0) continue;
                            }
                        }
                    }

                    visit[nx][ny] = true;
                    warmArea[nx][ny]+=wind[2]-1;
                    if(wind[2] != 1) q.add(new int[]{nx,ny,wind[2]-1});

                }
            }
        }

        int res = 0;
        int[][] nowMap = new int[R][C];

        // 돌리기
        while(res < 101){

            // 2. 현재 맵에 온도 조절
            for(int i = 0 ; i < R ; i++){
                for(int j = 0 ; j < C ; j++){
                    nowMap[i][j]+=warmArea[i][j];
                }
            }

            // 3. 현재 맵에서 온도 이동
            moveTemp(nowMap,wallBitMap);


            // 4. 바깥쪽 1씩 감소
            for(int i = 0 ; i < R ; i++){
                for(int j = 0 ; j < C ; j++){
                    if( i == 0 || j == 0 || i == R-1 || j == C-1){
                        if(nowMap[i][j] >= 1) nowMap[i][j]--;
                    }
                }
            }

            // 5. 초콜릿 증가
            res++;

            // 6. checkArea 지역 체크 (초콜릿 101까지 반복)
            boolean check =false;
            for(int[] ca : checkArea){
                // 모두 K 이상이면 탈출
                if(nowMap[ca[0]][ca[1]] < K ){
                    check = true;
                    break;
                }
            }
            if(!check) break;
        }

        System.out.println(res);

    }

    private static void moveTemp(int[][] nowMap, int[][] wallBitMap) {

        // 여기도 벽 포함
        int R = nowMap.length;
        int C = nowMap[0].length;
        int[][] temp = new int[R][C];

        // 4방향에서 더 작은 값이 있으면 이동
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                for(int k = 0 ; k < 4 ; k++){
                    int nx = i+airDx[k];
                    int ny = j+airDy[k];

                    if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                    if((wallBitMap[i][j] & (1<<k)) > 0) continue;
                    if(nowMap[i][j] > nowMap[nx][ny]){
                        // 지가 더 높으면 이동
                        int dif = (nowMap[i][j] - nowMap[nx][ny])/4;
                        temp[i][j] -=dif;
                        temp[nx][ny] += dif;
                    }

                }
            }
        }

        for(int i = 0 ; i < R ; i++) {
            for (int j = 0; j < C; j++) {
                nowMap[i][j]+=temp[i][j];

            }
        }
    }
}
