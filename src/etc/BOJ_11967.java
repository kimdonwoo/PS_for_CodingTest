package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11967 {
    static int[] dx ={1,-1,0,0};
    static int[] dy ={0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[N][N];
        boolean[][] check = new boolean[N][N];
        map[0][0] = true;

        HashMap<String, ArrayList<int[]>> switchs = new HashMap<>();
        HashMap<String, Boolean> tempSpace = new HashMap<>();

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            String k = x+","+y;
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            if(switchs.containsKey(k)){
                switchs.get(k).add(new int[]{a,b});
            }else{
                ArrayList<int[]> temp = new ArrayList<>();
                temp.add(new int[] {a,b});
                switchs.put(k,temp);
            }
        }


        // BFS
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        check[0][0] = true;
        int res = 0;

        while(!q.isEmpty()){

            // 방 입장
            int[] now = q.poll();
            String key = now[0]+","+now[1];

            // 스위치로 불켜기
            if(switchs.containsKey(key)){
                for(int[] s : switchs.get(key)){
                    map[s[0]][s[1]] = true;
                    // 만약 이전에 방문 기회가 있었다면 큐에 넣어줘야함
                    if(tempSpace.containsKey(s[0]+","+s[1])){
                        q.add(new int[]{s[0],s[1]});
                        tempSpace.remove(s[0]+","+s[1]);
                        check[s[0]][s[1]] = true;
                    }
                }
            }

            for(int i = 0 ; i < 4; i++){
                int nx = now[0]+dx[i];
                int ny = now[1]+dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(check[nx][ny]) continue;
                if(map[nx][ny]){
                    // 만약 불이 켜진 곳이면
                    check[nx][ny] = true;
                    q.add(new int[]{nx,ny});
                }else{
                    // 불이 안켜진 곳이면 HM에 저장
                    tempSpace.put(nx+","+ny,true);
                }
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(map[i][j]) res++;
            }
        }

        System.out.println(res);


    }
}
