package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOj_14502 {
    static int[][] map;
    static boolean[][] visit;
    static int N,M;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] select;
    static int res = Integer.MIN_VALUE;
    static int[][] temp;

    static ArrayList<int[]> emp = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        select = new int[3][2];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) emp.add(new int[] {i,j});
            }
        }

        build(0,0);

        System.out.println(res);

    }

    private static void build(int idx, int count) {
        // 종료 : 3개 선택한 경우
        if(count == 3){

            // temp가 완성된 새로운 map임
            temp = new int[N][M];
            visit = new boolean[N][M];

            for(int i = 0 ; i < N ; i++){
                temp[i] = map[i].clone();
            }
            for(int i = 0 ; i < 3; i++){
                temp[select[i][0]][select[i][1]] = 1;
            }

            // temp에 바이러스 퍼뜨리기
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(temp[i][j] == 2 && !visit[i][j]){
                        visit[i][j] = true;
                        DFS(i,j);
                    }
                }
            }

            // 갯수 세기
            int comp = 0;
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(temp[i][j] == 0){
                        comp++;
                    }
                }
            }

            res = Math.max(res,comp);

            return;
        }

        // 선택
        for(int i = idx ; i < emp.size() ; i++){
            select[count][0] = emp.get(i)[0];
            select[count][1] = emp.get(i)[1];
            build(i+1,count+1);
        }
    }

    private static void DFS(int x, int y) {

        for(int i = 0 ; i < 4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                if(!visit[nx][ny] && temp[nx][ny] == 0){
                    temp[nx][ny]=2;
                    visit[nx][ny] = true;
                    DFS(nx,ny);
                }
            }
        }

    }
}
