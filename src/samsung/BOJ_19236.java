package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_19236 {

    static int[][] sea = new int[4][4];
    static HashMap<Integer,int[]> fishes = new HashMap<>();

    // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    // 1  2  3  4  5  6  7  8
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,-1,-1,-1,0,1,1,1};
    static int[] shark = new int[3];
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0 ; i < 4 ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 4 ; j++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                sea[i][j] = num;
                fishes.put(num, new int[] {i,j,dir});
            }
        }

        // 상어 (0,0) 출현
        ans = sea[0][0];
        shark[2] = fishes.get(sea[0][0])[2];
        fishes.remove(sea[0][0]);
        sea[0][0] = 0;

        backtracking(sea, ans , fishes);

        System.out.println(ans);
    }

    private static void backtracking(int[][] map, int res,HashMap<Integer, int[]> nowFishes) {
        ans = Math.max(ans,res);

        // 상어 이동 시키기
        // 새로운 map 생성
        moveFish(map , nowFishes);

        // 현재 상어가 갈수 있는 모든 칸에 대해서 들어가기
        int nx = shark[0];
        int ny = shark[1];

        while(true){

            int[][] newMap = new int[4][4];
            for(int i = 0 ; i < 4; i++){
                newMap[i] = map[i].clone();
            }
            HashMap<Integer,int[]> newFishes = new HashMap<>();
            for(int k : nowFishes.keySet()){
                newFishes.put(k,new int[]{nowFishes.get(k)[0] , nowFishes.get(k)[1] , nowFishes.get(k)[2]  });
            }

            // 이동
            nx += dx[shark[2]];
            ny += dy[shark[2]];

            // 만약 범위 밖이라면 break
            if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) break;

            // 범위 안이라면

            if(newMap[nx][ny] != 0){
                // 상어 이동
                int[] temp = shark.clone();
                shark[0] = nx;
                shark[1] = ny;
                shark[2] = newFishes.get(newMap[nx][ny])[2];

                // 물고기 없애기
                newFishes.remove(newMap[nx][ny]);
                int count = newMap[nx][ny];
                newMap[nx][ny] = 0;

                backtracking(newMap,res+count,newFishes);

                // 상어 다시 원위치
                shark = temp.clone();
            }
        }
    }

    private static void moveFish(int[][] map , HashMap<Integer, int[]> nowFishes) {
        /*
        물고기 이동
                - 한칸 이동
                - 빈 칸 , 다른 물고기 있는 칸 이동 가능 (상어 칸 or 장외 x)
                - 이동할 수 있는 방향이 나올때까지 45도 반시계 회전시킨다. (없으면 이동 x)
                - 다른 물고기가 있는 경우에는 위치 바꾸기
         */
        for(int i = 1 ; i <= 16 ; i++){
            if(nowFishes.containsKey(i)){
                int now[] = nowFishes.get(i);

                for(int j = 0 ; j < 8 ; j++){
                    int nd = (now[2] + j) % 8;
                    int nx = now[0] + dx[nd];
                    int ny = now[1] + dy[nd];

                    // 장외나 상어칸이 아니면 즉 이동할 수 있는 상황이면 -> now 물고기 이동 시키기
                    // 이동 시켰으면 for-j 탈출
                    if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4){
                        if(!(nx == shark[0]) || !(ny == shark[1])){
                            if(map[nx][ny] == 0){
                                // 빈 칸으로 이동
                                map[nx][ny] = map[now[0]][now[1]];
                                map[now[0]][now[1]] = 0;
                                now[0] = nx;
                                now[1] = ny;
                                now[2] = nd;

                            }else{
                                // 물고기 있는 칸 - 스왑
                                int temp;
                                int[] next = nowFishes.get(map[nx][ny]);
                                // map 스왑
                                temp = map[nx][ny];
                                map[nx][ny] = map[now[0]][now[1]];
                                map[now[0]][now[1]] = temp;
                                // fishes 스왑
                                temp = now[0];
                                now[0] = nx;
                                next[0] = temp;

                                temp = now[1];
                                now[1] = ny;
                                next[1] = temp;
                                now[2] = nd;
                            }
                            break;
                        }
                    }
                }
            }
        }

    }
}
