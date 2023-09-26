package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*



 */

public class BOJ6087 {

    static int w, h;
    static char[][] map;
    static Node[] target = new Node[2];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Node implements Comparable<Node> {
        int x, y, dir, mirrors;

        public Node(int x, int y, int dir, int mirrors) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirrors = mirrors;
        }

        @Override
        public int compareTo(Node o) {
            return this.mirrors - o.mirrors;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new char[h][w];


        for (int i = 0, idx = 0; i < h; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 'C') target[idx++] = new Node(i, j, -5, -1);
            }
        }

        System.out.println(bfs(target[0]));
    }

    private static int bfs(Node start) {
        int min = Integer.MAX_VALUE;
        Node goal = target[1];

        PriorityQueue<Node> q = new PriorityQueue<>();
        int[][][] visited = new int[4][h][w];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < h; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        q.offer(start);

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == goal.x && now.y == goal.y) {
                min = Math.min(min, now.mirrors);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                int nextMirrors = (now.dir == i) ? now.mirrors : now.mirrors + 1;
                if (!isInRange(nextX, nextY) || map[nextX][nextY] == '*' || Math.abs(now.dir - i) == 2) continue;

                if (visited[i][nextX][nextY] > nextMirrors) {
                    q.offer(new Node(nextX, nextY, i, nextMirrors));
                    visited[i][nextX][nextY] = nextMirrors;
                }
            }
        }

        return min;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }

}
//
//    static int N,M;
//    static char[][] map;
//    static int[][] visit;
//    static ArrayList<int []> c = new ArrayList<>();
//    static int res = Integer.MAX_VALUE;
//    static int[] dx = {0,0,1,-1};
//    static int[] dy = {1,-1,0,0};
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        M = Integer.parseInt(st.nextToken());
//        N = Integer.parseInt(st.nextToken());
//
//        map = new char[N][M];
//        visit = new int[N][M];
//
//        for(int i = 0 ; i < N ; i++){
//            String str = br.readLine();
//            for(int j = 0 ; j < M ; j++){
//                map[i][j] = str.charAt(j);
//                if(map[i][j] == 'C'){
//                    c.add(new int[] {i,j});
//                }
//            }
//        }
//
//        for(int i = 0 ; i < N; i++){
//            for(int j = 0 ; j < M; j++){
//                visit[i][j] = Integer.MAX_VALUE;
//            }
//        }
//
//        int[] start = c.get(0);
//        visit[start[0]][start[1]] = 0;
//        DFS(start[0], start[1] ,-1,0);
//
//        System.out.println(res-1);
//
//    }
//
//    static void DFS(int x , int y , int beforeDir, int count){
//
//        if(res <= count) return;
//
//        if(x == c.get(1)[0]  && y == c.get(1)[1]){
//            res = Math.min(res,count);
//            return;
//        }
//
//        for(int i = 0 ; i < 4 ;i++){
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//
//            if(nx >= 0 && nx < N && ny >= 0 && ny < M ){
//                if(map[nx][ny] != '*' ){
//                    if(beforeDir == i && visit[nx][ny] >= count){
//                        visit[nx][ny] = count;
//                        DFS(nx,ny,i,count);
//                    }else if ( beforeDir != i && visit[nx][ny] >= count+1){
//                        visit[nx][ny] = count+1;
//                        DFS(nx,ny,i,count+1);
//                    }
//                }
//            }
//        }
//    }
//}


/*

    static void BFS(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y,-1,0});

        visit[x][y] = true;

        while(!q.isEmpty()){
            int[] now =q.poll();

            if(now[0]== c.get(1)[0] && now[1] ==c.get(1)[1]){
                res = Math.min(res,now[3]);
                continue;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M ){
                    if(map[nx][ny] != '*' && !visit[nx][ny]){
                        visit[nx][ny] = true;
                        // 같은 방향
                        if(now[2] == i) q.add(new int[] {nx,ny,i,now[3]});
                        // 다른 방향
                        else q.add(new int[] {nx,ny,i, now[3]+1});
                    }
                }
            }
        }
    }
 */