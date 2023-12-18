package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15685 {
    static boolean[][] map = new boolean[101][101];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            draw(y,x,d,g);
        }

        int res = 0;
        for(int i = 0 ; i < 100 ; i++){
            for(int j = 0 ; j < 100 ; j++){
                if(map[i][j]){
                    if(map[i+1][j] && map[i][j+1] && map[i+1][j+1] ) res++;
                }
            }
        }

        System.out.println(res);


    }

    private static void draw(int x, int y, int d,int g) {

        ArrayList<int[]> temp = new ArrayList<>();

        temp.add(new int[] {x,y});
        int[] end = new int[2];

        if( d == 0){
            temp.add(new int[] {x,y+1});
            end[0] = x;
            end[1] = y+1;
        }else if( d == 1){
            temp.add(new int[] {x-1,y});
            end[0] = x-1;
            end[1] = y;
        }else if( d == 2){
            temp.add(new int[] {x,y-1});
            end[0] = x;
            end[1] = y-1;
        }else if( d == 3){
            temp.add(new int[] {x+1,y});
            end[0] = x+1;
            end[1] = y;
        }

        for(int i = 0 ; i < g ; i++){
            ArrayList<int[]> temp2 = new ArrayList<>();
            for(int j = temp.size()-1 ; j >= 0  ; j--){
                int[] t = temp.get(j);

                int a = end[0] - end[1] + t[1];
                int b = end[1] - t[0] + end[0];

                if( a == end[0] && b == end[1] ) continue;
                temp2.add(new int[] {a,b});
            }

            for(int[] t : temp2){
                temp.add(t);
            }

            end[0] = temp2.get(temp2.size()-1)[0];
            end[1] = temp2.get(temp2.size()-1)[1];
        }

        for(int[] t : temp){
            map[t[0]][t[1]] = true;
        }

    }
}
