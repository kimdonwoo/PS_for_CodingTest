package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_17142 {
    static int N,M,zero_cnt=0;
    static int[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static ArrayList<int[]> virus = new ArrayList<>();
    static int res = Integer.MAX_VALUE;
    static int[] select;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        select = new int[M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virus.add(new int[] {i,j});
                if(map[i][j] == 0) zero_cnt++;
            }
        }

        combi(0,0);

        if(res == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(res);

    }

    private static void combi(int idx, int count) {
        // 선택 완료
        if(count == M){

            boolean[][] visited = new boolean[N][N];
            Queue<int[]> q = new LinkedList<>();

            // 바이러스 -1로 표시
            for(int s : select){
                int[] v = virus.get(s);
                visited[v[0]][v[1]] = true;
                q.add(new int[] {v[0],v[1],0});
            }

            int time = 0;
            int cnt = 0;

            // 퍼지기
            while(!q.isEmpty()){
                int[] now = q.poll();

                for(int i = 0 ; i < 4 ; i++){
                    int nx = now[0]+dx[i];
                    int ny = now[1]+dy[i];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N ) continue; // 범위 밖
                    if(!visited[nx][ny]){ // 0 아닌 곳은 이미 간곳임
                        if(map[nx][ny] == 0) {
                            cnt++;
                            time = Math.max(time, now[2] + 1);
                            visited[nx][ny] = true;
                            q.add(new int[] {nx,ny,now[2]+1});
                        } else if(map[nx][ny] == 2){
                            visited[nx][ny] = true;
                            q.add(new int[] {nx,ny,now[2]+1});
                        }
                    }
                }
            }

            if(cnt != zero_cnt) return;

            // 결과 비교
            res = Math.min(res, time);

            return;
        }

        // 선택
        for(int i = idx ; i < virus.size(); i++){
            select[count] = i;
            combi(i+1,count+1);
        }


    }

}