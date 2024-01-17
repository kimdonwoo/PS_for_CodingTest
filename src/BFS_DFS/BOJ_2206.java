package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
    public static class Node{
        public int i;
        public int j;
        public boolean crack;
        public int cnt;

        public Node(int i, int j ,boolean crack,int cnt){
            this.i = i;
            this.j = j;
            this.crack = crack;
            this.cnt = cnt;
        }
    }
    static int N,M;
    static boolean[][] map;
    static boolean[][] crackVisit;
    static boolean[][] visit;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        visit = new boolean[N][M];
        crackVisit = new boolean[N][M];

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                if(str.charAt(j)=='0') map[i][j] = true;
            }
        }

        // map true인곳만 갈수있다

        System.out.println(BFS());

    }

    // BFS 꺼낼때 visit인지 (넣을때!!) visit인지 항상고민하는듯
    public static int BFS(){

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,true,1));
        visit[0][0] = true;

        while(!q.isEmpty()){

            Node now = q.poll();

            if(now.i == N-1&& now.j ==M-1){
                return now.cnt;
            }

            for(int i = 0; i < 4 ; i++){
                int nx = now.i+dx[i];
                int ny = now.j+dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                // map이랑 visit에 따라
                if(now.crack && !visit[nx][ny]){
                    if(!map[nx][ny]){
                        // 만약 map[nx][ny]가 벽이면
                        if(now.crack){
                            q.add(new Node(nx,ny,false,now.cnt+1));
                            crackVisit[nx][ny] = true;
                        }
                    }else{
                        // 만약 그냥 빈공간이면
                        q.add(new Node(nx,ny,now.crack,now.cnt+1));
                        visit[nx][ny] = true;
                    }
                }

                if(!now.crack &&!crackVisit[nx][ny]){
                    if(map[nx][ny]){
                        q.add(new Node(nx,ny,now.crack,now.cnt+1));
                        crackVisit[nx][ny] = true;
                    }
                }
            }
        }

        return -1;
    }
}

