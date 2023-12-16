package samsung;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_14890 {
    static int N,L;
    static int[][] nor;
    static int[][] inv;
    static int res = 0;
    static boolean[] temp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        nor = new int[N][N];
        inv = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                nor[i][j] = Integer.parseInt(st.nextToken());
                inv[j][i] = nor[i][j];
            }
        }

        // 가로 길 체크
        check(inv);
        check(nor);

        System.out.println(res);

    }

    private static void check(int[][] map){
        for(int i = 0 ; i < N ; i++){
            temp = new boolean[N];
            boolean check = false;
            for(int j = 0 ; j < N-1 ; j++){
                int diff = map[i][j] - map[i][j+1];

                if(diff > 1 || diff < -1){
                    check = true;
                    break;
                }else if(diff == -1){ // 다음 계단이 더 높아짐
                    for(int k = 0 ; k < L ; k++){
                        if(j - k < 0 || temp[j-k] ){
                            check = true;
                            break; // 범위 벗어나거나 이미 설치되어 있거나
                        }
                        if(map[i][j] != map[i][j-k]){
                            check = true;
                            break;
                        }
                        temp[j-k] = true;
                    }
                    if(check) break;
                }else if(diff == 1){ // 다음 계단이 더 낮아짐
                    for(int k = 1 ; k <= L ; k++){
                        if(j + k >= N || temp[j+k] ){
                            check = true;
                            break; // 범위 벗어나거나 이미 설치되어 있거나
                        }
                        if(map[i][j] -1 != map[i][j+k]){
                            check = true;
                            break;
                        }
                        temp[j+k] = true;
                    }
                    if(check) break;
                }
            }
            if(!check) res++;
        }

    }

}
