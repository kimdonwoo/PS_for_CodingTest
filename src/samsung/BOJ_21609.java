package samsung;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21609 {
    public static class block{
        public int i;
        public int j;
        public int color;

        public block(int i, int j , int color){
            this.i = i;
            this.j = j;
            this.color = color;
        }
    }

    public static class blockGroup{
        public ArrayList<block> blocks = new ArrayList<>();
        public int rainbowCount;
        public block basic;

        public blockGroup(){
            this.rainbowCount = 0;
            this.basic = null;
        }
    }

    static int N,M;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int res = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for( int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for( int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){

            // BFS 돌면서 현재 map에서 최대 블록 그룹 찾기
            blockGroup maxBlockGroup = getMaxBlockGroup();

            if(maxBlockGroup == null || maxBlockGroup.blocks.size() <= 1) break;

            // 모든 블록 제거하거 점수 획득
            res += Math.pow(maxBlockGroup.blocks.size(),2);
            for(block b : maxBlockGroup.blocks){
                map[b.i][b.j] = -2;
            }

            // map에 중력이 작용 + 회전 + 중력
            gravity();

            // map 90도 반시계 방향 회전
            rotation();

            // map에 중력이 작용
            gravity();
        }

        System.out.println(res);
    }


    private static void rotation() {

        int[][] temp = new int[N][N];

        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                temp[N-1-j][i]=map[i][j];
            }
        }

        map = temp;
    }

    private static void gravity() {

        for(int i = N-1 ; i > 0 ; i --){
            for(int j = 0 ; j < N ; j ++){
                if(map[i][j] == -2){
                    for(int k = i-1; k >= 0; k--){
                        if(map[k][j] >= 0){
                            map[i][j] = map[k][j];
                            map[k][j] = -2;
                            break;
                        }else if(map[k][j] == -1){
                            break;
                        }
                    }
                }
            }
        }
    }

    private static blockGroup getMaxBlockGroup() {

        visit = new boolean[N][N];
        ArrayList<blockGroup> bg  = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(map[i][j] > 0 && !visit[i][j]){
                    if(bg.size() > 0){
                        bfs(i,j,bg.get(0).blocks.size(),bg);
                    }else{
                        bfs(i,j,0,bg);
                    }
                }
            }
        }

        // 여기서 bg가 여러개 일때 정렬하기
        Collections.sort(bg,(o1,o2)->{
            if(o1.rainbowCount == o2.rainbowCount){
                if(o2.basic.i == o1.basic.i){
                    return o2.basic.j - o1.basic.j;
                }else{
                    return o2.basic.i - o1.basic.i;
                }
            }else{
                return o2.rainbowCount - o1.rainbowCount;
            }
        });

        if(bg.size() == 0) return null;

        return bg.get(0);

    }

    private static void bfs(int x, int y, int size , ArrayList<blockGroup> bgArr) {

        blockGroup temp = new blockGroup();
        int color = map[x][y];
        temp.basic = new block(x,y,color);
        temp.blocks.add(temp.basic);

        Queue<int[]> q = new LinkedList<>();
        visit[x][y] = true;
        q.add(new int[] {x,y});

        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int i = 0 ; i < 4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N ){
                    if(!visit[nx][ny]){
                        if(map[nx][ny] == color){
                            visit[nx][ny] = true;
                            temp.blocks.add(new block(nx,ny,color));
                            q.add(new int[] {nx,ny});
                        }else if(map[nx][ny] == 0){
                            visit[nx][ny] = true;
                            temp.blocks.add(new block(nx,ny,0));
                            temp.rainbowCount++;
                            q.add(new int[] {nx,ny});
                        }
                    }
                }
            }
        }

        for(block b : temp.blocks){
            if(b.color == 0) visit[b.i][b.j] = false;
        }

        if(size == 0){
            bgArr.add(temp);
        }else if(size == temp.blocks.size() ){
            bgArr.add(temp);
        }else if(size < temp.blocks.size()){
            bgArr.clear();
            bgArr.add(temp);
        }

    }
}
