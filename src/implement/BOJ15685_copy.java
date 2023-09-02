package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15685_copy {
    static boolean[][] map;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        map = new boolean[101][101];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            // 여기서 그리기
            draw(x,y,d,g);
        }

        // 정사각형 갯수 count
        System.out.println(countMap());

    }

    static void draw(int x, int y, int d, int g){
        // 먼저 d랑 g로 방향 리스트구하기
        ArrayList<Integer> dirs = new ArrayList<>();

        // 0 세대
        dirs.add(d);
        map[x][y] = true;

        for(int i = 0 ; i < g ; i++) {
            for (int j = dirs.size()-1; j>=0; j--) {
                // 뒤에서 부터 돌면서 +1 %4 값 넣기
                dirs.add((dirs.get(j)+1) %4);
            }
        }


        // x,y 기준으로 방향리스트 돌면서 map 바꾸기
        for(int dir : dirs){
            switch(dir){
                case 0 : // 오
                    map[++x][y] = true;
                    break;
                case 1 : // 위
                    map[x][--y] = true;
                    break;
                case 2 : // 왼
                    map[--x][y] = true;
                    break;
                case 3 : // 아래
                    map[x][++y] = true;
                    break;
            }

        }

    }

    static int countMap(){
        // map에 있는 둘러쌓인 정사각형 갯수 세기
        int count = 0;

        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (map[x][y] && map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1])
                    count++;
            }
        }
        return count;

    }

}
