package implement;


import java.io.*;
import java.util.*;

public class BOJ_17135{
    static int N, M, D;
    static int[][] map;
    static int[][] copyMap;
    static int ans;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        copyMap = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                copyMap[i][j] = map[i][j];
            }
        }

        ArrayList<Integer> archer = new ArrayList<>();
        ans = 0;
        comb(1, M, 3, archer);

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // map을 원래대로 변경.
    public static void init() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = copyMap[i][j];
            }
        }
    }

    // 거리
    public static int distance(int r1, int r2, int c1, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    // 조합
    public static void comb(int start, int n, int r, ArrayList<Integer> archer) {
        if (r == 0) {
            init();
            attack(archer);
            return;
        }

        for (int i = start; i <= n; i++) {
            archer.add(i);
            comb(i + 1, n, r - 1, archer);
            archer.remove(archer.size() - 1);
        }
    }

    // 궁수가 적을 공격하는 함수.
    public static void attack(ArrayList<Integer> archer) {
        int res = 0;

        // 최대 N턴까지 진행할 수 있음.
        for (int n = 1; n <= N; n++) {
            boolean[][] visited = new boolean[N + 1][M + 1];

            for (int k = 0; k < archer.size(); k++) {
                int temp = archer.get(k); // 궁수가 서 있는 x좌표
                int minD = Integer.MAX_VALUE; // 최소 거리
                int minR = Integer.MAX_VALUE; // 최소 거리의 y좌표
                int minC = Integer.MAX_VALUE; // 최소 거리의 x좌표

                // 맵 전체를 탐색해서 최단거리를 찾아내는 것이 목적.
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= M; j++) {
                        if (map[i][j] == 1) { // 적이 있을 경우
                            if (minD >= distance(i, N + 1, j, temp)) {
                                // 현재 구한 최소 거리보다 더 짧은 거리가 발생할 경우
                                // 최단거리와 좌표들을 다시 초기화.
                                if (minD > distance(i, N + 1, j, temp)) {
                                    minD = distance(i, N + 1, j, temp);
                                    minR = i;
                                    minC = j;
                                } else {
                                    // 현재 구한 최소 거리와 지금 구한 최소 거리가 같을 경우,
                                    // 가장 왼쪽 적부터 처지해야하므로 x좌표가 더 작은지 검사해야 함.
                                    if (minC > j) {
                                        minR = i;
                                        minC = j;
                                    }
                                }
                            }
                        }
                    }
                }

                // 위에 과정을 모두 거친 후, 최소 거리가 D보다 작으면,
                // 그 좌표에 visited 처리를 해 준다.
                if (minD <= D) {
                    visited[minR][minC] = true;
                }
            }

            // visited가 true인 좌표만 적을 처지한다.
            // 궁수가 같은 적을 쏠 수도 있기때문에 바로 바로 map[i][j] = 0하면 안 된다.
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (visited[i][j]) {
                        map[i][j] = 0;
                        res++;
                    }
                }
            }

            // 성 바로 위 줄을 모두 0으로 초기화.
            for (int i = 1; i <= M; i++) {
                map[N][i] = 0;
            }

            // i번째 줄을 i-1번째 줄로 초기화.
            for (int i = N; i >= 1; i--) {
                for (int j = 1; j <= M; j++) {
                    map[i][j] = map[i - 1][j];
                }
            }
        }

        ans = Math.max(ans, res);
    }

}



//public class BOJ_17135 {
//    static int N,M;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken())+1;
//        M = Integer.parseInt(st.nextToken());
//        int D = Integer.parseInt(st.nextToken());
//
//        int[][] map = new int[N][M];
//        int monster = 0;
//
//        for(int i = 0 ; i < N-1 ; i++){
//            st = new StringTokenizer(br.readLine());
//            for(int j = 0 ; j < M ; j++){
//                map[i][j] = Integer.parseInt(st.nextToken());
//                if(map[i][j] == 1) monster++;
//            }
//        }
//
//        int res = 0;
//        int[][] arch = new int[3][2];
//
//        for(int i = (N-1)*M ; i < N*M ; i++){
//            for(int j = i+1 ; j < N*M ; j++){
//                for(int k = j+1 ; k < N*M ; k++){
//
//                    int deathCnt = 0;
//                    int monsterCnt = monster;
//
//                    int[][] newMap = new int[N][M];
//                    for(int l = 0 ; l < N ; l++) newMap[l] = map[l].clone();
//
//                    // 1. 궁수 설치
//                    arch[0] = new int[]{i/M, i%M};
//                    arch[1] = new int[]{j/M, j%M};
//                    arch[2] = new int[]{k/M, k%M};
//
//                    // 2. 공격 시뮬레이션
//                    int turn = N;
//                    while (turn --> 0) {
//                        if(monsterCnt == 0) break;
//
//                        // 2-1. 궁수의 공격
//                        ArrayList<int[]> arr = new ArrayList<>();
//                        kill(arch[0],newMap,arr,D);
//                        kill(arch[1],newMap,arr,D);
//                        kill(arch[2],newMap,arr,D);
//
//                        for(int[] s : arr){
//                            if(newMap[s[0]][s[1]] != 0){
//                                deathCnt++;
//                                monsterCnt--;
//                                newMap[s[0]][s[1]] = 0;
//                            }
//                        }
//                        arr.clear();
//
//                        // 2-2. 적들의 이동
//                        for(int l = 0 ; l < M ; l++){
//                            if(newMap[N-2][l] == 1){
//                                newMap[N-2][l] = 0;
//                                monsterCnt--;
//                            }
//                        }
//                        for(int l = N-2 ; l >= 0 ; l--){
//                            newMap[l+1] = newMap[l];
//                        }
//                        newMap[0] = new int[M];
//                        Arrays.fill(newMap[0],0);
//
//                    }
//
//                    if(res < deathCnt){
//                        res = deathCnt;
//                    }
//
//                }
//            }
//        }
//        System.out.println(res);
//    }
//
//    private static void kill(int[] arch, int[][] newMap, ArrayList<int[]> set, int d) {
//        // 잠시
//        // j < i-j (둘다 양수)
//        ArrayList<int[]> temp = new ArrayList<>();
//
//        for(int i = 1 ; i <= d ; i++){
//            for(int j = 0 ; j <= i ; j++){
//
//                if(arch[0]+j >= 0 && arch[0]+j < N && arch[1]-(i-j) >= 0 && arch[1]-(i-j) < M ) {
//                    if (newMap[arch[0] + j][arch[1] - (i - j)] == 1){
//                        temp.add(new int[]{arch[0] + j, arch[1] - (i - j),d});
//                    }
//                }
//                if(arch[0]-j >= 0 && arch[0]-j < N && arch[1]-(i-j) >= 0 && arch[1]-(i-j) < M ) {
//                    if (newMap[arch[0] - j][arch[1] - (i - j)] == 1) {
//                        temp.add(new int[]{arch[0] - j, arch[1] - (i - j),d});
//                    }
//                }
//                if(arch[0]-j >= 0 && arch[0]-j < N && arch[1]+(i-j) >= 0 && arch[1]+(i-j) < M ) {
//                    if (newMap[arch[0] - j][arch[1] + (i - j)] == 1){
//                        temp.add(new int[]{arch[0] - j, arch[1] + (i - j),d});
//                    }
//                }
//            }
//        }
//
//        Collections.sort(temp,(o1,o2)->{
//            if(o1[2] == o2[2]){
//                if(o1[1] == o2[1]){
//                    return o1[0] - o2[0];
//                }
//                return o1[1] - o2[1];
//            }
//            return o1[2] - o2[2];
//        });
//
//        if(temp.size() > 0){
//            set.add(new int[] {temp.get(0)[0],temp.get(0)[1]});
//        }
//
//    }
//}
