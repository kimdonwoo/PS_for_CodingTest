package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15683_ {
    /*
        사각지대 0의 갯수 최소

        모든 CCTV의 방향에 따라 반복문 (이걸 어떻게 구현하지..)
            상황만들어서 0의 갯수를 세주기..?
                -> 최소 count 출력

        1 : 0,1,2,3
        2 : 0,1
        3 : 0,1,2,3
        4 : 0,1,2,3
        5 : x

        2차원 배열 copy 하는 방법

     */

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static int[][][] mode = {
            {{0}},
            {{0}, {1}, {2}, {3}},
            {{2, 3}, {0, 1}},
            {{0, 3}, {1, 3}, {1, 2}, {0, 2}},
            {{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}},
            {{0, 1, 2, 3}}};
    public static ArrayList<Node> cctv;
    public static class Node {
        int x;
        int y;
        int type;
        public Node(int x, int y, int type) {
            this.x= x;
            this.y= y;
            this.type = type;
        }
    }

    public static int n, m, ans;
    public static int[][] map;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cctv = new ArrayList<>();
        int zero_cnt = 0;
        int[][] map = new int[n][m];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<m;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5){ // cctv면 arrayList에 추가해줌 (위치랑 cctv 타입)
                    cctv.add(new Node(i, j, map[i][j]));
                } else if(map[i][j] == 0) zero_cnt++;
            }
        }
        ans = zero_cnt;
        combination(0, cctv.size(), map);
        System.out.println(ans);
    }

    /*
        해당 문제에서 배울 점 !
        1. ***combination을 dfs로 구현하는 방법!!!
        2. 2차원 배열 카피하기

     */
    public static void combination(int depth, int r, int[][] map) {
        // r이 cctv 갯수임 즉, cctv갯수까지 다 세어지면 map확인
        if(depth == r) {
            int cnt = check(map);
            ans = Math.min(ans, cnt);
            return;
        }

        // 현재 cctv
        int cctv_type = cctv.get(depth).type;
        int x = cctv.get(depth).x;
        int y = cctv.get(depth).y;

        // cctv_type에 따라
        for(int i=0;i<mode[cctv_type].length;i++) {
            // map 카피하기
            int[][] map_copy = new int[n][m];
            for(int k=0;k<n;k++) {
                map_copy[k] = map[k].clone();
            }

            // 각 cctv가 지켜보는 방향마다 돌면서
            for(int j=0;j<mode[cctv_type][i].length;j++){

                // 방향
                int dir = mode[cctv_type][i][j];
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                while (true) {
                    // 범위 밖이면 break
                    if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        break;
                    }
                    // 벽(6) 만나면 break
                    if(map[nx][ny] == 6) {
                        break;
                    }

                    // 감시하는 곳은 -1
                    map_copy[nx][ny] = -1;

                    // 방 하나씩
                    nx += dx[dir];
                    ny += dy[dir];
                }
            }

            // 즉, 현재 cctv가 볼수있는 방향에 따라 DFS 들어감 -> 이게 combination임 !!!!
            combination(depth+1, r, map_copy);
        }
    }

    // 이건 그냥 0의 갯수 세는 메소드
    public static int check(int[][] map) {
        int cnt = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


}
