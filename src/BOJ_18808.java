import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18808 {
    static boolean[][] map;
    static int N,M,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        while(K-->0){

            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            boolean[][] sticker = new boolean[R][C];
            for(int i = 0 ; i < R ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < C ; j++){
                    int s = Integer.parseInt(st.nextToken());
                    if(s == 1) sticker[i][j] = true;
                }
            }

            // 여기서 map 다 돌면서 붙이기
            for(int r = 0 ; r < 4; r++) {
                boolean succ = false;
                for (int i = 0; i < N - sticker.length+1; i++) {
                    for (int j = 0; j < M - sticker[0].length+1; j++) {
                        if (check(i, j, sticker)) {
                            // 만약 붙혔으면 다음 스티커
                            succ = true;
                            break;
                        }
                    }
                    if (succ) break;
                }
                if (succ) break;

                // 실패했으면 90도 돌리기
                sticker = rotate(sticker);
            }
        }

        int res = 0;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j]) res++;
            }
        }

        System.out.println(res);

    }

    private static boolean[][] rotate(boolean[][] sticker) {
        int newR = sticker.length;
        int newC = sticker[0].length;

        boolean[][] rotateSticker = new boolean[newC][newR];

        for(int i = 0 ; i < sticker.length ; i++){
            for(int j = 0 ; j < sticker[0].length ; j++){
                // 90도 돌리기
                rotateSticker[j][sticker.length-1-i] = sticker[i][j];
            }
        }

        return rotateSticker;
    }

    private static boolean check(int x, int y, boolean[][] sticker) {
        // i,j 에서 시작하기
        for(int i = 0 ; i < sticker.length ; i++){
            for(int j = 0 ; j < sticker[0].length ; j++){
                if(sticker[i][j] && map[x+i][y+j]){
                    return false;
                }
            }
        }

        // 붙히기
        for(int i = 0 ; i < sticker.length ; i++){
            for(int j = 0 ; j < sticker[0].length ; j++){
                if(sticker[i][j]){
                    map[x+i][y+j] = true;
                }
            }
        }

        return true;

    }
}
