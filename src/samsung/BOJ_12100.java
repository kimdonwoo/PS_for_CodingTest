package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12100 {
    static int N;
    static int[][] map;
    static int[][] temp;
    static int[] select = new int[5];
    static int res = Integer.MIN_VALUE;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0,0);

        System.out.println(res);

    }

    private static void combi(int idx, int count) {
        // 선택 완료
        if(count == 5){

            // 1. map 복사
            temp = new int[N][N];
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < N ; j++){
                    temp[i][j] = map[i][j];
                }
            }

            // 2. select 돌면서 temp 돌리기
            for(int s : select){
                if(s == 0){
                    int[][] temp2 = new int[N][N];

                    for(int i = 0 ; i < N ; i++){
                        for(int j = 0 ; j < N ; j++){
                            temp2[j][i] = temp[i][j];
                        }
                    }

                    temp = temp2;
                }else if (s == 1){
                    int[][] temp2 = new int[N][N];

                    for(int i = 0 ; i < N ; i++){
                        for(int j = 0 ; j < N ; j++){
                            temp2[(N-1)-i][(N-1)-j] = temp[i][j];
                        }
                    }

                    temp = temp2;
                }else if (s == 2){
                    int[][] temp2 = new int[N][N];

                    for(int i = 0 ; i < N ; i++){
                        for(int j = 0 ; j < N ; j++){
                            temp2[(N-1)-j][(N-1)-i] = temp[i][j];
                        }
                    }

                    temp = temp2;
                }

                move();
            }

            // 3. 최댓값 비교
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < N ; j++){
                    if(temp[i][j] > res){
                        res = temp[i][j];
                    }
                }
            }

            return;
        }

        // 선택
        // 이렇게 하면 4**5의 시간복잡도 생김
        for(int i = idx ; i < 5 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                select[count] = j;
                combi(i+1,count+1);
            }
        }
    }

    private static void move() {

        Queue<Integer> q  = new LinkedList<>();
        ArrayList<Integer> arr = new ArrayList<>();

        // 왼쪽
        for(int i = 0 ; i < N ; i++){
            arr.clear();
            for(int j = 0 ; j < N ; j++){
                // 만약 0이 아니면
                if(temp[i][j] != 0){
                    q.add(temp[i][j]);
                }
            }
            Arrays.fill(temp[i],0);

            // 여기 큐에서 temp에 넣기
            while(!q.isEmpty()){
                int now = q.poll();
                if(!q.isEmpty()){
                    if( now == q.peek()){
                        now+=q.poll();
                    }
                }

                arr.add(now);
            }

            for(int k = 0 ; k < arr.size() ; k++){
                temp[i][k] = arr.get(k);
            }
        }


    }
}
