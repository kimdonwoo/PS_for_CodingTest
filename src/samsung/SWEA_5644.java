package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_5644 {
    static int[] dx = {0,-1,0,1,0};
    static int[] dy = {0,0,1,0,-1};

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;

        for(int test_case = 1; test_case <= T; test_case++){

            st = new StringTokenizer(br.readLine().trim());

            int time = Integer.parseInt(st.nextToken());
            int BCCnt = Integer.parseInt(st.nextToken());
            int res = 0;

            int[][] move = new int[2][time];
            HashMap<Integer,Integer> BCVal = new HashMap<>();
            ArrayList<Integer>[][] map = new ArrayList[10][10];
            for(int i = 0 ; i < 10 ; i++){
                for(int j = 0 ; j < 10 ; j++){
                    map[i][j] = new ArrayList<>();
                }
            }


            for(int j = 0 ; j < 2; j ++){
                st = new StringTokenizer(br.readLine().trim());
                for(int i = 0 ; i < time ; i++){
                    move[j][i] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] BC = new int[BCCnt][4];
            for(int i = 0 ; i < BCCnt ; i++){
                st = new StringTokenizer(br.readLine().trim());

                BC[i][1] = Integer.parseInt(st.nextToken())-1;
                BC[i][0] = Integer.parseInt(st.nextToken())-1;
                BC[i][2] = Integer.parseInt(st.nextToken());
                BC[i][3] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(BC,(o1,o2)->{
               return o2[3]-o1[3];
            });

            for(int i = 0 ; i < BCCnt ; i++){
                BCVal.put(i,BC[i][3]);
                bfs(map,BC[i][0],BC[i][1],BC[i][2],i);
            }

            // 이제여기서 (0,0) (9,9) 출발
            int[] A = {0,0};
            int[] B = {9,9};

            ArrayList<Integer> aScore = map[A[0]][A[1]];
            ArrayList<Integer> bScore = map[B[0]][B[1]];

            // 둘다 1이면 ?
            // 둘다 0이면 pass
            if (aScore.size() > 0 && bScore.size() > 0){
                // 둘다 0보다 커
                if(aScore.get(0) != bScore.get(0)){
                    res+=BCVal.get(aScore.get(0));
                    res+=BCVal.get(bScore.get(0));
                }else{
                    // 만약 같으면
                    // 만약 둘인데 같으면
                    if(aScore.size() >= 2 && bScore.size()>= 2){
                        res+= BCVal.get(aScore.get(0));
                        res+= Math.max(BCVal.get(aScore.get(1)),BCVal.get(bScore.get(1)));
                    }else if(aScore.size() >= 2){
                        res+=BCVal.get(aScore.get(0))+BCVal.get(aScore.get(1));
                    }else if(aScore.size() == 1 && bScore.size() == 1){
                        // 둘다 1이면 ?
                        res+= BCVal.get(aScore.get(0));
                    }else{
                        res+=BCVal.get(bScore.get(0))+BCVal.get(bScore.get(1));
                    }

                }
            }else if(aScore.size() > 0){
                res+=BCVal.get(aScore.get(0));
            }else if(bScore.size() > 0){
                res+=BCVal.get(bScore.get(0));
            }

            for(int i = 0 ; i < time ; i++){
                // 이동
                A[0]+=dx[move[0][i]];
                A[1]+=dy[move[0][i]];

                B[0]+=dx[move[1][i]];
                B[1]+=dy[move[1][i]];

                // 점수 계산
                aScore = map[A[0]][A[1]];
                bScore = map[B[0]][B[1]];

                // 둘다 1이면 ?
                // 둘다 0이면 pass
                if(aScore.size() == 0 && bScore.size() == 0) continue;
                else if (aScore.size() > 0 && bScore.size() > 0){
                    // 둘다 0보다 커
                    if(aScore.get(0) != bScore.get(0)){
                        res+=BCVal.get(aScore.get(0));
                        res+=BCVal.get(bScore.get(0));
                    }else{
                        // 만약 같으면
                        // 만약 둘인데 같으면
                        if(aScore.size() >= 2 && bScore.size()>= 2){
                            res+= BCVal.get(aScore.get(0));
                            res+= Math.max(BCVal.get(aScore.get(1)),BCVal.get(bScore.get(1)));
                        }else if(aScore.size() >= 2){
                            res+=BCVal.get(aScore.get(0))+BCVal.get(aScore.get(1));
                        }else if(aScore.size() == 1 && bScore.size() == 1){
                            // 둘다 1이면 ?
                            res+= BCVal.get(aScore.get(0));
                        }else{
                            res+=BCVal.get(bScore.get(0))+BCVal.get(bScore.get(1));
                        }

                    }
                }else if(aScore.size() > 0){
                    res+=BCVal.get(aScore.get(0));
                }else{
                    res+=BCVal.get(bScore.get(0));
                }

            }

            System.out.println("#"+test_case+" "+res);

        }
    }

    private static void bfs(ArrayList<Integer>[][] map, int x, int y, int c , int idx) {

        boolean[][] visit = new boolean[10][10];

        Queue<int[]> q = new LinkedList<>();

        map[x][y].add(idx);
        visit[x][y] = true;
        q.add(new int[] {x,y,0});

        while(!q.isEmpty()){
            int[] now = q.poll();

            if(now[2] == c) continue;

            for(int i = 1 ; i <= 4; i++){
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];

                if(nx < 0 || nx >= 10 || ny < 0 || ny >= 10) continue;
                if(!visit[nx][ny]){
                    map[nx][ny].add(idx);
                    visit[nx][ny] = true;
                    q.add(new int[]{nx,ny,now[2]+1});
                }
            }
        }


        /*
        map에서 (x,y)시작


         */


    }


}
