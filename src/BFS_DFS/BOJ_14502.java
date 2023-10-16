package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14502 {
    static int N,M;
    static int[][] map;
    static ArrayList<int[]> virus = new ArrayList<>();
    static ArrayList<int[]> space = new ArrayList<>();
    static int res = 0;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] visit;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0){
                    // 빈칸
                    space.add(new int[] {i,j});
                }else if(map[i][j] == 2){
                    // 바이러스
                    virus.add(new int[] {i,j});
                }
            }
        }
        
        for(int i = 0 ; i < space.size(); i++){
            for(int j = i+1 ; j < space.size(); j++){
                for(int k = j+1 ; k < space.size(); k++){
                    // 벽 세우기
                    map[space.get(i)[0]][space.get(i)[1]] = 1;
                    map[space.get(j)[0]][space.get(j)[1]] = 1;
                    map[space.get(k)[0]][space.get(k)[1]] = 1;

                    // 완성된 map에 바이러스 기준으로 DFS 돌려서 2 채우기
                    visit = new boolean[N][M];
                    for(int l = 0 ; l < virus.size() ; l++){
                        visit[virus.get(l)[0]][virus.get(l)[1]]=true;
                        DFS(virus.get(l)[0],virus.get(l)[1]);
                    }

                    // 안전 지역 갯수 세어서 res min
                    int count = 0;
                    for(int a = 0 ; a < N ; a++){
                        for(int b = 0 ; b < M ; b++){
                            if(!visit[a][b] && map[a][b] == 0){
                                count++;
                            }
                        }
                    }

                    res=Math.max(res,count);

                    // 벽 허물기
                    map[space.get(i)[0]][space.get(i)[1]] = 0;
                    map[space.get(j)[0]][space.get(j)[1]] = 0;
                    map[space.get(k)[0]][space.get(k)[1]] = 0;
                }
            }
        }

        System.out.println(res);

    }

    static void DFS(int x, int y){


        for(int i = 0 ; i < 4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx >= 0 && ny >= 0 && nx < N && ny < M){
                if(!visit[nx][ny] && map[nx][ny] == 0){
                    visit[nx][ny] = true;
                    DFS(nx,ny);
                }
            }
        }
    }
}
