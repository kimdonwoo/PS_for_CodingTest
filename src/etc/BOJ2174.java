package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ2174 {
    static int[][] map;
    static HashMap<Integer,int[]> hm;
    static int[] dx = {1,0,-1,0}; // 동 남 서 북
    static int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        hm = new HashMap<>();

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        map = new int[B][A];

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            hm.put(i+1,new int[] {x-1,y-1});

            if(c=='E') {
                map[x - 1][y - 1] = 1;
            }else if(c=='S'){
                map[x - 1][y - 1] = 2;
            }else if(c=='W'){
                map[x - 1][y - 1] = 3;
            }else{
                map[x - 1][y - 1] = 4;
            }
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int robot = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            int count = Integer.parseInt(st.nextToken());

            for(int j = 0 ; j < count ; j++){

                int[] nowRobot = hm.get(robot);
                // 1 (0,0) E(1) / 2 (4,3) W(2)

                if (c == 'L'){
                    map[nowRobot[0]][nowRobot[1]] = (map[nowRobot[0]][nowRobot[1]]+4)%5;
                    if(map[nowRobot[0]][nowRobot[1]] == 0) map[nowRobot[0]][nowRobot[1]]=4;
                }else if(c == 'R'){
                    map[nowRobot[0]][nowRobot[1]] = (map[nowRobot[0]][nowRobot[1]]+1)%5;
                    if(map[nowRobot[0]][nowRobot[1]] == 0) map[nowRobot[0]][nowRobot[1]]++;
                }else{
                    // 앞으로 한칸
                    int dir = map[nowRobot[0]][nowRobot[1]];
                    int next_x = nowRobot[0]+dx[dir-1];
                    int next_y = nowRobot[1]+dy[dir-1];

                    if(next_x < 0 || next_x >=B || next_y < 0 || next_y >= A){
                        // 범위 밖으로 나가면
                        System.out.printf("Robot %d crashes into the wall",robot);
                        return;
                    }

                    if(map[next_x][next_y] != 0) {
                        // 충돌
                        for(int k : hm.keySet()){
                            int[] temp = hm.get(k);
                            if(temp[0] == next_x && temp[1] == next_y){
                                System.out.printf("Robot %d crashes into robot %d",robot,k);
                                return;
                            }
                        }

                    }

                    // 이동
                    map[nowRobot[0]][nowRobot[1]] = 0;
                    map[next_x][next_y] = dir;
                    hm.put(robot ,new int[] {next_x,next_y});
                }
            }
        }

        System.out.println("OK");
    }
}
