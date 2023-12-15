package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_3190 {
    static boolean[][] map; // 사과
    static boolean[][] body;
    static ArrayList<int[]> snake = new ArrayList<>();
    static int dir = 0;
    static int N,K;
    static HashMap<Integer,Character> roads = new HashMap<>();
    // 오른쪽으로 시작 시계방향 ++
    static int[] dx ={0,1,0,-1};
    static int[] dy ={1,0,-1,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][N];
        snake.add(new int[] {0,0});

        body = new boolean[N][N];
        body[0][0] = true;

        for(int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;

            map[x][y] = true;
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < L ; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            char y = st.nextToken().charAt(0);

            roads.put(x,y);
        }

        int res = 0;

        while(true){

            // 시간 증가
            res++;

            // 몸길이를 늘려 머리를 다음칸에 위치시킨다.
            int[] before = snake.get(snake.size()-1);
            snake.add(new int[] {before[0]+dx[dir],before[1]+dy[dir]});

            // 벽이나 자기몸이나 부딪히면 끝
            int[] now = snake.get(snake.size()-1);
            if(now[0] < 0 || now[0] >= N || now[1] < 0 || now[1] >= N) break;
            if(body[now[0]][now[1]]) break;
            body[now[0]][now[1]] = true;

            // 사과가 존재하면 꼬리는 움직이지 않고 머리만 생기는거
            if(!map[now[0]][now[1]]){
                int[] tail = snake.get(0);
                body[tail[0]][tail[1]] = false;
                snake.remove(0);
            }else{
                map[now[0]][now[1]] = false;
            }

            // 방향 돌리기 now[2]
            if(roads.containsKey(res)){
                if(roads.get(res) == 'D'){
                    dir= (dir+1)%4;
                }else{
                    dir= (dir+3)%4;
                }
            }
        }

        System.out.println(res);

    }
}
