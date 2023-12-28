package samsung;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_19237 {

    public static class space{
        int smellCount;
        int smellShark;
        ArrayList<Integer> sharks;

        public space(){
            this.smellCount = 0;
            this.smellShark = 0;
            sharks = new ArrayList<>();
        }
    }

    public static class shark{
        int i;
        int j;
        int nowDir = -1;
        int[][] dir = new int[4][4];

        public shark(int i , int j){
            this.i = i;
            this.j = j;
        }

    }

    static int N,M,K;
    static space[][] map;
    static HashMap<Integer,shark> hm = new HashMap<>();

    // 위 아래 왼 오른
    static int[] dx ={ -1,1,0,0};
    static int[] dy ={0,0,-1,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new space[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j  < N ; j++){
                map[i][j] = new space();
                int num = Integer.parseInt(st.nextToken());
                // 상어 추가
                if(num != 0){
                    map[i][j].sharks.add(num);
                    map[i][j].smellShark = num;
                    map[i][j].smellCount = K;
                    hm.put(num,new shark(i,j));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i < M+1 ; i++){
            hm.get(i).nowDir = Integer.parseInt(st.nextToken())-1;
        }

        for(int i = 1 ; i < M+1 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0 ; k < 4 ; k++){
                    hm.get(i).dir[j][k] = Integer.parseInt(st.nextToken())-1;
                }
            }
        }

        int count = 0;

        while(true) {
            count++;
            move();
            removeShark();
            if(hm.size() == 1 || count == 1001) break;
        }

        if(count == 1001) System.out.println(-1);
        else System.out.println(count);

    }

    private static void removeShark() {
        // 상어 2마리 이상 있는 곳은 상어 없애기 + 냄새 반영하기
        // + 상어 없는 곳들 전부다 k-- 시키기

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(map[i][j].sharks.size() == 0){
                    if(map[i][j].smellCount > 1){
                        map[i][j].smellCount--;
                    }else if(map[i][j].smellCount == 1){
                        map[i][j].smellCount = 0;
                        map[i][j].smellShark = 0;
                    }

                }else if(map[i][j].sharks.size() == 1){
                    map[i][j].smellShark = map[i][j].sharks.get(0);
                    map[i][j].smellCount = K;
                }else{
                    //여러 마리 있으면
                    int minShark = Integer.MAX_VALUE;
                    for(int s : map[i][j].sharks){
                        minShark = Math.min(minShark,s);
                    }
                    for(int s : map[i][j].sharks){
                        if(minShark != s){
                            hm.remove(s);
                        }
                    }

                    map[i][j].sharks.clear();
                    map[i][j].sharks.add(minShark);
                    map[i][j].smellShark = minShark;
                    map[i][j].smellCount = K;
                }
            }
        }


    }

    private static void move() {
        // 모든 상어들 이동시키기 + 이동한 space에 냄새 반영
        //
        for(int k = 1 ; k < M+1 ; k++){
            if (hm.containsKey(k)){
                shark now = hm.get(k);
                // 빈공간 탐색
                boolean flag = false;
                for(int i = 0 ; i < 4; i++){
                    int nx = now.i + dx[now.dir[now.nowDir][i]];
                    int ny = now.j + dy[now.dir[now.nowDir][i]];

                    if(nx >= 0 && nx < N && ny >= 0 && ny < N ){
                        if(map[nx][ny].smellCount == 0){ // 빈공간 있으면 상어 이동
                            map[now.i][now.j].sharks.remove(0);
                            now.i = nx;
                            now.j = ny;
                            now.nowDir = now.dir[now.nowDir][i];
                            map[nx][ny].sharks.add(k);
                            flag = true;
                            break;
                        }
                    }
                }
                if(flag) continue;

                // 빈공간 없으면 본인 냄새나는 곳으로
                for(int i = 0 ; i < 4; i++){
                    int nx = now.i + dx[now.dir[now.nowDir][i]];
                    int ny = now.j + dy[now.dir[now.nowDir][i]];

                    if(nx >= 0 && nx < N && ny >= 0 && ny < N ){
                        if(map[nx][ny].smellShark == k){
                            map[now.i][now.j].sharks.remove(0);
                            now.i = nx;
                            now.j = ny;
                            now.nowDir = now.dir[now.nowDir][i];
                            map[nx][ny].sharks.add(k);
                            break;
                        }
                    }
                }
            }
        }
    }
}
