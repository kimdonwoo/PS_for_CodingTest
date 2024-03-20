package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    내가 놓친 부분
    1. 이동이 불가능하면 그 자리에 복제되는걸 캐치못함
    2. 이미 방문한 곳도 다시 갈 수 있다 (이때는 물고기는 없는 상태임)

 */

public class BOJ_23290 {
    public static class Fish{
        int dir;

        public Fish(int dir){
            this.dir = dir;
        }
    }

    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int[] fishDr = {0,-1,-1,-1,0,1,1,1};
    static int[] fishDc = {-1,-1,0,1,1,1,0,-1};
    // 상좌하우
    static int[] sharkDr = {-1,0,1,0};
    static int[] sharkDc = {0,-1,0,1};

    static int[] select, temp;
    static ArrayList<Fish>[][] map;
    static int maxFish;
    static boolean[][] visit;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[][] smell = new int[4][4];
        map = new ArrayList[4][4];
        for(int i = 0 ; i < 4; i ++){
            for(int j = 0 ; j < 4; j ++){
                map[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
            int dir = Integer.parseInt(st.nextToken())-1;

            map[r][c].add(new Fish(dir));
        }

        st = new StringTokenizer(br.readLine());
        int[] shark = new int[2];
        shark[0] = Integer.parseInt(st.nextToken())-1;
        shark[1] = Integer.parseInt(st.nextToken())-1;

        while(S --> 0){

            ArrayList<Fish>[][] newMap = new ArrayList[4][4];
            for(int i = 0 ; i < 4; i ++){
                for(int j = 0 ; j < 4; j ++){
                    newMap[i][j] = new ArrayList<>();
                }
            }

            // 물고기 이동
            for(int i = 0 ; i < 4; i ++){
                for(int j = 0 ; j < 4; j ++){
                    if(map[i][j].size() > 0){
                        for(Fish f : map[i][j]){
                            // 여기서 이동 (상어, 물고기 냄새 칸, 격자 밖 x)
                            int dir = f.dir;
                            boolean isSuc = false;
                            for(int d = 0 ; d < 8 ; d++){
                                int nextX = i+fishDr[dir];
                                int nextY = j+fishDc[dir];

                                if(nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4){
                                    dir = (dir + 7) % 8;
                                    continue;
                                }

                                if(shark[0] == nextX && shark[1] == nextY){
                                    dir = (dir + 7) % 8;
                                    continue;
                                }

                                if(smell[nextX][nextY] > 0){
                                    dir = (dir + 7) % 8;
                                    continue;
                                }

                                // 이제 이동 가능한 경우
                                newMap[nextX][nextY].add(new Fish(dir));
                                isSuc = true;
                                break;
                            }
                            if (!isSuc){
                                newMap[i][j].add(new Fish(f.dir));
                            }
                        }
                    }
                }
            }



            // 여기서 newMap이 이동한 물고기들 저장 되어 있음
            // 상어의 3칸 이동
            // 현재 위치, 몇번째move, 물고기 수
            select = new int[3];
            temp = new int[3];
            maxFish = -1;
            // 이전 방향 어떻게 기억 ?


            visit = new boolean[4][4];
            sharkMove(shark[0],shark[1],0,0,newMap);

            // 선택하고 나면 select대로 움직임
            // -> 물고기들 물고기 냄새로 바꾸기
            for(int i = 0 ; i < 3 ; i++){
                shark[0] += sharkDr[select[i]];
                shark[1] += sharkDc[select[i]];

                if(newMap[shark[0]][shark[1]].size() > 0){
                    newMap[shark[0]][shark[1]].clear();
                    smell[shark[0]][shark[1]] = 3;
                }
            }

            // 냄새는 두턴 후 사라짐
            for(int i = 0 ; i < 4 ;i++){
                for(int j = 0 ; j < 4 ;j++){
                    if(smell[i][j] > 0) smell[i][j]--;
                }
            }

            // map이랑 newMap 합치기
            for(int i = 0 ; i < 4 ;i++){
                for(int j = 0 ; j < 4 ;j++){
                    if(newMap[i][j].size() > 0){
                        map[i][j].addAll(newMap[i][j]);
                    }
                }
            }


        }

        // 현재 map에 있는
        int res = 0;
        for(int i = 0 ; i < 4 ;i++){
            for(int j = 0 ; j < 4 ;j++){
                res+=map[i][j].size();
            }
        }

        System.out.println(res);

    }

    private static void sharkMove(int nowR, int nowC, int moveCnt, int fishCnt, ArrayList<Fish>[][] newMap) {

        if(moveCnt == 3){
            if(maxFish < fishCnt){
                maxFish = fishCnt;
                select = temp.clone();
            }
            return;
        }

        for(int i = 0 ; i < 4 ; i++){
            int nextR = nowR+sharkDr[i];
            int nextC = nowC+sharkDc[i];

            if(nextR < 0 || nextR >= 4 || nextC < 0 || nextC >= 4) continue;
            if(visit[nextR][nextC]){
                // 만약 이미 방문한 곳이면
                temp[moveCnt] = i;
                sharkMove(nextR,nextC,moveCnt+1,fishCnt, newMap);
            }else{
                visit[nextR][nextC] = true;
                temp[moveCnt] = i;
                sharkMove(nextR,nextC,moveCnt+1,fishCnt+newMap[nextR][nextC].size(), newMap);
                visit[nextR][nextC] = false;
            }
        }
    }
}
