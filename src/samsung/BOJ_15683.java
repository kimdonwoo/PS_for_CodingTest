package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15683 {
    static int N,M,res = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visit;
    static int[][] cctv = {{0,1,2,3},{0,1},{0,1,2,3},{0,1,2,3},{0}};
    static ArrayList<int[]> cctvs = new ArrayList<>();
    static int[] cctv_dir;
    // 동 남 서 북
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0 ; i < N ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5 ){
                    // 위치랑 유형 저장
                    cctvs.add(new int[] {i,j,map[i][j]});
                }
            }
        }

        cctv_dir = new int[cctvs.size()];

        combi(0);

        System.out.println(res);
    }

    private static void combi(int count) {
        // 정지
        if(count == cctvs.size()){

            boolean[][] check = new boolean[N][M];

            for(int i = 0 ; i < cctvs.size() ; i++){
                int[] now = cctvs.get(i);

                // 유형이 now[2]
                if(now[2] == 1){
                    watch(now[0],now[1],cctv_dir[i],check);
                }else if(now[2] == 2){
                    watch(now[0],now[1],cctv_dir[i],check);
                    watch(now[0],now[1],(cctv_dir[i]+2)%4,check);
                }else if(now[2] == 3){
                    watch(now[0],now[1],cctv_dir[i],check);
                    watch(now[0],now[1],(cctv_dir[i]+1)%4,check);
                }else if(now[2] == 4){
                    watch(now[0],now[1],cctv_dir[i],check);
                    watch(now[0],now[1],(cctv_dir[i]+1)%4,check);
                    watch(now[0],now[1],(cctv_dir[i]+2)%4,check);
                }else if(now[2] == 5){
                    watch(now[0],now[1],cctv_dir[i],check);
                    watch(now[0],now[1],(cctv_dir[i]+1)%4,check);
                    watch(now[0],now[1],(cctv_dir[i]+2)%4,check);
                    watch(now[0],now[1],(cctv_dir[i]+3)%4,check);
                }
            }

            int com = 0;

            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(map[i][j] == 0 && !check[i][j]) com++;
                }
            }


            res = Math.min(res,com);

            return;
        }

        // 선택
        for(int i = 0 ; i < cctv[cctvs.get(count)[2]-1].length; i++){
            cctv_dir[count] = i;
            combi(count+1);
        }
    }

    private static void watch(int x, int y, int dir, boolean[][] check){
        while(true) {
            x += dx[dir];
            y += dy[dir];

            if(x >= 0 && x < N && y >= 0 && y < M ){
                if(map[x][y] != 6 ){
                    check[x][y] = true;
                }else{
                    // 6이면 탈출
                    return;
                }
            }else {
                return;
            }
        }
    }
}
