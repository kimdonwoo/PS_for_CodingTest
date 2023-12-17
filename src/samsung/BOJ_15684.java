package samsung;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15684 {
    static int N,M,H;
    static boolean[][] map;
    static int[] val;
    static boolean res = false;
    static int[][] select;
    static int goal;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());

        map = new boolean[H][N-1];

        for( int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
        }

        select = new int[H*(N-1)-M][2];
        val = new int[N];

        int idx = 0;
        for(int i = 0 ; i < H ; i++){
            for(int j = 0 ; j < N-1 ; j++){
                if(!map[i][j]){
                    select[idx][0] = i;
                    select[idx][1] = j;
                    idx++;
                }
            }
        }

        for(int i = 0 ; i <= 3; i++){
            goal = i;
            int[] temp = new int[i];
            Arrays.fill(temp,-1);

            combi(0,temp,0);
        }

        System.out.println(-1);


    }

    private static void combi( int idx, int[] count , int now) {
        // 모두 선택 완료
        if(now == goal){
            res = false;

            for(int c : count) map[select[c][0]][select[c][1]] = true;

            // 현재 상태에서 val 체크
            // map = new boolean[H][N-1];
            for(int i = 0 ; i < N; i++){
                int loc = i;
                for(int j = 0 ; j < H; j++){
                    if(loc - 1 >= 0 && loc < N-1){
                        if(map[j][loc] && map[j][loc-1]){
                            for(int c : count) map[select[c][0]][select[c][1]] = false;
                            return;
                        }
                    }
                    if(loc - 1 >= 0){
                        if(map[j][loc-1]){
                            loc--;
                            continue;
                        }
                    }
                    if(loc < N-1){
                        if(map[j][loc]) loc++;
                    }
                }
                // 끝까지 내려왔을 때
                val[i] = loc;
            }

            // val 체크
            for(int i = 0 ; i < N ; i++){
                if(val[i] != i){
                    res = true;
                    break;
                }
            }

            if(!res){
                System.out.println(goal);
                System.exit(0);
            }


            for(int c : count) map[select[c][0]][select[c][1]] = false;

            return;
        }

        // 선택
        // select는
        for(int i = idx ; i < select.length; i++){
            count[now]= i;
            combi( i+1,count,now+1);
        }

    }
}
