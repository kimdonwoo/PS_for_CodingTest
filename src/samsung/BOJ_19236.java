package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_19236 {
    /*
        이런식으로 map과 HashMap을 사용하게 되면
        map의 위치로부터 HashMap 접근도 가능하고 그 반대도 가능
     */

    static int[][] map = new int[4][4];
    static HashMap<Integer,int[]> fishes = new HashMap<>();

    // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    // 1  2  3  4  5  6  7  8
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,-1,-1,-1,0,1,1,1};
    static int[] shark = new int[3];
    static int res = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;



        for(int i = 0 ; i < 4 ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 4 ; j++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = num;
                fishes.put(num, new int[] {i,j,dir});
            }
        }

        // 상어 (0,0) 출현
        res += map[0][0];
        shark[2] = fishes.get(map[0][0])[2];
        fishes.remove(map[0][0]);
        map[0][0] = 0;

        // 물고기 이동
        moveFish();

        // 상어 이동
        backtracking(map, res);

        /*
            4*4 map 각 칸에 물고기(번호, 방향)가 있음
                번호는 1~16, 방향은 8방

            상어 (0,0)먹고 방향은 그 물고기 방향
            물고기 이동
                - 한칸 이동
                - 빈 칸 , 다른 물고기 있는 칸 이동 가능 (상어 칸 or 장외 x)
                - 이동할 수 있는 방향이 나올때까지 45도 반시계 회전시킨다. (없으면 이동 x)
                - 다른 물고기가 있는 경우에는 위치 바꾸기
            상어 이동
                - 한번에 여러개 칸 이동 가능
                - 물고기 있는 칸으로 가면 물고기 먹고 그 방향 가지게 된다
                - 물고기 없는 칸 x , 이동할 칸 없으면 집으로 간다

            backtracking(new map[][], 현재 점수, ){
                // 상어가 갈곳없으면 정지

                // 갈곳 있으면 먹은 상황 map 만들고 점수더해서 다음 backtracking 들어가기

         */



    }

    private static void backtracking(int[][] map, int res) {



    }

    private static void moveFish() {
        /*
        물고기 이동
                - 한칸 이동
                - 빈 칸 , 다른 물고기 있는 칸 이동 가능 (상어 칸 or 장외 x)
                - 이동할 수 있는 방향이 나올때까지 45도 반시계 회전시킨다. (없으면 이동 x)
                - 다른 물고기가 있는 경우에는 위치 바꾸기
         */
        for(int i = 1 ; i <= 16 ; i++){
            if(fishes.containsKey(i)){
                int now[] = fishes.get(i);

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
                                int[] next = fishes.get(map[nx][ny]);
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
