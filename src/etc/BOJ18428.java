package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ18428 {
    static int N;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static char[][] map;
    static int count;
    static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        ArrayList<int[]> o = new ArrayList<>();
        ArrayList<int[]> t = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j]=='X'){
                    o.add(new int[] {i,j});
                }else if(map[i][j]=='T'){
                    t.add(new int[] {i,j});
                }
            }
        }

        for(int i = 0 ; i < o.size()-2;i++){
            for(int j = i+1 ; j < o.size()-1;j++){
                for(int k = j+1 ; k < o.size();k++){
                    //1. X 중에 3개를 골라서 장애물 설치 -> O
                    int[] o1 = o.get(i);
                    int[] o2 = o.get(j);
                    int[] o3 = o.get(k);

                    map[o1[0]][o1[1]] = 'O';
                    map[o2[0]][o2[1]] = 'O';
                    map[o3[0]][o3[1]] = 'O';

                    //2. T의 감시 영역 체크
                    //count = 0;
                    check = false;

                    for(int[] temp : t){
                        for(int l = 0 ; l < 4 ; l++) {
                            checkStudent(temp[0], temp[1], l);
                            if(check) break;
                        }
                        if(check) break;
                    }

                    if(!check){
                        System.out.println("YES");
                        return;
                    }


                    map[o1[0]][o1[1]] = 'X';
                    map[o2[0]][o2[1]] = 'X';
                    map[o3[0]][o3[1]] = 'X';

                }
            }
        }

        System.out.println("NO");


    }
    static void checkStudent(int x, int y, int dir){
        if(map[x][y]=='S'){
            //count++;
            check = true;
            return;
        }

        int next_x = x+dx[dir];
        int next_y = y+dy[dir];

        if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= N) return;
        if(map[next_x][next_y] == 'O') return;

        checkStudent(next_x,next_y,dir);
    }

    // 현재 상황에서 S를 만나면 모든 checkStudent 탈출하기
}
