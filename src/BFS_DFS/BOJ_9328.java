package BFS_DFS;

import java.io.*;
import java.util.*;

public class BOJ_9328 {
    static int r,c, answer;
    static int keyBit;
    static char[][] map;
    static boolean[][] check;
    static Map<Integer, Queue<int[]>> doorList;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1 ,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int t=0; t<tc; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            // 입구 리스트
            List<int[]> entrance = new ArrayList<>();

            map = new char[r][c];
            keyBit=0;
            answer =0;
            doorList = new HashMap<>();

            for(int i=0; i<r; i++) {
                String line = br.readLine();
                for(int j=0; j<c; j++) {
                    map[i][j] = line.charAt(j);
                    // 벽이 아니면
                    if(map[i][j] != '*') {
                        if(i==0 || i==r-1 || j==0 || j == c-1) {
                            if(map[i][j] == '$') {
                                answer++;
                                map[i][j] ='.';
                            }else if('a'<=map[i][j] && map[i][j] <= 'z') {
                                keyBit |= 1<<(map[i][j]-'a');
                            }
                            entrance.add(new int[] {j,i});
                        }
                    }
                }
            }

            char[] keyList = br.readLine().toCharArray();
            for(char key : keyList) {
                if(key=='0') break;
                keyBit |= (1<<(key-'a'));
            }
            // KeyBit 완성

            check = new boolean[r][c];
            for(int[] pos : entrance) {
                int x = pos[0], y = pos[1];

                // 시작지점이 문이면
                if('A' <= map[y][x] && map[y][x] <='Z') {
                    int door = 1 << (map[y][x] - 65);
                    if((keyBit & door) != door) {
                        // 해당 도어의 키가 있으면
                        putDoorQueue(x,y,door);
                        continue;
                    }
                }
                bfs(x,y);
            }
            System.out.println(answer);
        }
    }

    static void bfs(int x, int y){

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y});
        check[y][x] = true;

        while(!q.isEmpty()) {
            int[] p = q.poll();
            int px= p[0];
            int py= p[1];

            for(int i=0; i<4; i++) {
                int nx  = px + dx[i];
                int ny  = py + dy[i];

                // 범위 밖, 이미 갔던 곳, 벽이면 continue
                if(nx <0 || ny <0|| nx>c-1 || ny > r-1 ||
                        check[ny][nx] || map[ny][nx]=='*') continue;

                if(map[ny][nx]=='$') {
                    answer++;
                } else if('a' <= map[ny][nx] && map[ny][nx] <= 'z') {
                    keyBit |= 1<<(map[ny][nx]-'a');
                    for(int door : doorList.keySet()) {
                        if((keyBit & door)==door) {
                            watingQueueExtract(q, door);
                        }
                    }
                } else if('A' <= map[ny][nx] && map[ny][nx] <='Z') {
                    int door = 1<<(map[ny][nx] - 65);
                    if((keyBit & door) != door) {
                        putDoorQueue(nx, ny, door);
                        continue;
                    }
                }
                check[ny][nx] = true;
                q.add(new int[] {nx,ny});
            }
        }
    }

    private static void watingQueueExtract(Queue<int[]> q, int door) {
        Queue<int[]> waitQueue = doorList.get(door);
        while(!waitQueue.isEmpty()) {
            int[] wq= waitQueue.poll();
            q.add(new int[] {wq[0],wq[1]});
        }
    }

    private static void putDoorQueue(int x, int y, int door) {
        if(doorList.containsKey(door)) {
            doorList.get(door).add(new int[] {x,y});
        }else {
            Queue<int[]> dq = new LinkedList<>();
            dq.add(new int[] {x,y});
            doorList.put(door, dq);
        }
    }
}