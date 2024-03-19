package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class BOJ_17143 {
    public static class Shark{
        int r;
        int c;
        int speed;
        int dir;
        int size;

        public Shark(int r, int c, int speed, int dir, int size){
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
    static int map[][];
    // 위 아래 오 왼
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static HashMap<Integer,Shark> sharks = new HashMap<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] map = new int[R][C];

        int M = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= M ; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken())-1;
            int size = Integer.parseInt(st.nextToken());

            map[r][c] = i;
            if(dir == 0 || dir == 1){
                sharks.put(i, new Shark(r,c,speed % (2*(R-1)),dir,size));
            }else{
                sharks.put(i, new Shark(r,c,speed % (2*(C-1)),dir,size));
            }
        }

        int fishking = -1;
        int res = 0;
        
        while(fishking < C-1){

            // 1. 낚시왕이 오른쪽으로 한칸 이동
            fishking++;
            
            // 2. 상어 잡기
            for(int i = 0 ; i < R ; i++){
                if(map[i][fishking] > 0){
                    Shark now = sharks.get(map[i][fishking]);
                    res += now.size;
                    sharks.remove(map[i][fishking]);
                    map[i][fishking] = 0;
                    break;
                }
            }

            // 3. 남은 상어들 이동
            int[][] newMap = new int[R][C];

            ArrayList<Integer> removeList = new ArrayList<>();

            for(Integer s : sharks.keySet()){
                Shark now = sharks.get(s);

                // 이동 시키기 now.speed만큼
                for(int i = 0 ; i < now.speed ; i++){
                    int nextR = now.r + dx[now.dir];
                    int nextC = now.c + dy[now.dir];

                    // 범위 밖이면 방향 바꿔
                    if(nextR < 0 || nextR >= R || nextC < 0 || nextC >= C){
                        // 1 <-> 2 / 3 <-> 4
                        if(now.dir == 0 || now.dir == 2) now.dir++;
                        else now.dir--;
                        nextR = now.r + dx[now.dir];
                        nextC = now.c + dy[now.dir];
                    }
                    now.r = nextR;
                    now.c = nextC;
                }


                if(newMap[now.r][now.c] == 0){
                    newMap[now.r][now.c] = s;
                }else{
                    Shark other = sharks.get(newMap[now.r][now.c]);

                    if(other.size > now.size){
                        // 기존애가 더 크면
                        removeList.add(s);
                    }else{
                        // 새로온 애가 더크면
                        removeList.add(newMap[now.r][now.c]);
                        newMap[now.r][now.c] = s;
                    }
                }
            }

            // map을 newMap으로 교체
            map = newMap;

            for(Integer num : removeList){
                sharks.remove(num);
            }
            
        }

        System.out.println(res);

    }
}
