package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ2234 {
    static int N,M;
    static int[][] map;
    static int[][] rooms;
    static int roomCount = 0;

    static int temp = 0;
    static int maxRoomSize = Integer.MIN_VALUE;
    static int[] dx = {1,0,-1,0}; // 남 동 북 서
    static int[] dy = {0,1,0,-1};
    static HashMap<Integer,Integer> roomSize = new HashMap<>();
    static ArrayList<HashMap<Integer,Boolean>> roomRelations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        rooms = new int[M][N];

        roomRelations.add(new HashMap<>());

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i = 0 ; i < M ; i++){
            for(int j = 0 ; j < N ; j++){
                // 1. 맵 전체 돌면서 DFS 들어가는 횟수 count하기
                // rooms를 visit으로도 사용
                if(rooms[i][j] == 0){
                    temp = 0;
                    roomCount++;

                    // roomCount
                    roomRelations.add(new HashMap<>());

                    rooms[i][j] = roomCount;
                    temp++;
                    DFS(i,j);
                    roomSize.put(roomCount,temp);
                    maxRoomSize = Math.max(maxRoomSize,temp);
                }
            }
        }

        System.out.println(roomCount);
        System.out.println(maxRoomSize);

        int sumRoomSize = Integer.MIN_VALUE;
        for(int i = 1 ; i < roomRelations.size() ; i++){
            int tempSize = roomSize.get(i);
            for(int k : roomRelations.get(i).keySet()){
                sumRoomSize = Math.max(sumRoomSize , tempSize + roomSize.get(k));
            }
        }

        System.out.println(sumRoomSize);


    }
    static void DFS(int x, int y){

        int num = map[x][y];

        for(int k = 0 ; k < 4 ; k++){
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(nx >= 0 && nx < M  && ny >= 0 && ny < N ){
                if(rooms[nx][ny] != 0 && rooms[nx][ny] != roomCount){
                    roomRelations.get(roomCount).put(rooms[nx][ny],true);
                }
            }
        }

        for(int k = 0 ; k < 4 ; k++) { // 남 동 북 서
            if (num / (int) Math.pow(2,3-k) == 1) { // 불가능한 방향일 때
                num = num - (num / (int) Math.pow(2, 3 - k)) * (int) Math.pow(2,3-k);
                continue;
            }
            num = (num - (num / (int) Math.pow(2, 3 - k)) * (int) Math.pow(2,3-k));
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(nx >= 0 && nx < M  && ny >= 0 && ny < N ){
                if(rooms[nx][ny] == 0){
                    rooms[nx][ny] = roomCount;
                    temp++;
                    DFS(nx,ny);
                }
            }
        }
    }
}
