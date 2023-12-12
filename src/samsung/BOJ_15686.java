package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    해당 풀이에서 일반 집들도
    ArrayList에 저장해두고 비교하면
    시간 더 줄이기 가능!!
 */

public class BOJ_15686 {
    static int N,M;
    static ArrayList<int[]> chickens;
    static int[][] map;
    static int[][] dist;
    static int res = Integer.MAX_VALUE;
    static int home = 0;
    static ArrayList<Integer> picks = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chickens = new ArrayList<>();
        map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    chickens.add(new int[] {i,j});
                }else if(map[i][j] == 1){
                    home++;
                }
            }
        }

        dist = new int[chickens.size()][home];

        for(int k = 0 ; k < chickens.size() ; k++){
            int check = 0;
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < N ; j++){
                    if(map[i][j] == 1){ // 만약 집이면
                        dist[k][check] = Math.abs(chickens.get(k)[0] - i)+Math.abs(chickens.get(k)[1] - j);
                        check++;
                    }
                }
            }
        }

        // 여기서 chickens에서 조합 구하기
        // chickens.size()중에 M개 선택하기
        chickenDFS(0, 0);

        System.out.println(res);
    }

    // M개의 수를 어떻게 기억할것인가
    private static void chickenDFS(int idx, int count) {
        // 정지 조건
        if(count == M){
            // 여기서 최소 구하기
            int[] temp = new int[home];
            Arrays.fill(temp,Integer.MAX_VALUE);

            for(int p : picks){
                for(int i = 0 ; i < home ; i++) {
                    temp[i] = Math.min(temp[i] , dist[p][i]);
                }
            }

            // temp 합이랑 res 비교하기
            int sum = 0;
            for(int i : temp){
                sum+=i;
            }

            res = Math.min(sum,res);
            return;
        }

        // DFS 들어가기
        for(int i = idx ; i < chickens.size() ; i++){
            picks.add(i);
            chickenDFS(i+1,count+1);
            picks.remove(picks.size()-1);
        }
    }
}
