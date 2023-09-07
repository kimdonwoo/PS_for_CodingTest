package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17140 {
    /*
        젤 처음 무조건 A는 3*3으로 시작

        R 연산 : 행 >= 열일때, 행 정렬
        C 연산 : 열 > 행일때, 열 정렬

        - 정렬 방법
          등장 횟수 오름차순
          같다면 수 오름차순

          이렇게 되면 행 또는 열의 크기가 달라짐 -> 어떤 자료구조 ?
            최대는 이전 배열에 2배로 길이가 증가함


          가장 커진 쪽에 모든 2차원 배열의 크기를 맞추고 0을 추가한다
     */
    static int r,c,k;
    static int[][] A;
    static int now_x, now_y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());

        A= new int[100][100];

        for(int i = 0 ; i < 3 ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 3 ; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /*
            1. 먼저 현재 A 배열의 A[r][c]가 k값인지 확인하고 아니면 2로

            2. A의 행의 갯수와 열의 갯수를 비교하고 그거에 따라 R연산 or C연산 실시
         */
        now_x = 3; // 행의 갯수
        now_y = 3; // 열의 갯수

        for(int i = 0 ; i <= 100 ; i++){
            // 1. 먼저 현재 A 배열의 A[r][c]가 k값인지 확인하고 아니면 2로
            if(A[r][c] == k){
                System.out.println(i);
                break;
            }else if(i == 100){
                System.out.println(-1);
            }

            // 2. A의 행의 갯수와 열의 갯수를 비교하고 R연산, C연산 구현 -> 바뀐 행과 열 저장
            if(now_x >= now_y){
                rOperation();
            }else{
                cOperation();
            }
        }

    }
    /*
       - 정렬 방법
          등장 횟수 오름차순
          같다면 수 오름차순
            [3, 1, 1, 2] -> (3,1),(1,2),(2,1)
            hashmap으로 하나 만들고 다시 돌면서 새로운 배열에 넣고 Arrays..?
            3 - 1
            1 - 2
            2 - 1


            [2, 1, 3, 1, 1, 2]

            현재 각 행에 대해 정렬을 적용한 배열을 하나 만들어서 집어넣기?

     */

    static void rOperation(){ // 모든 행에 대한 연산 수행 ---
        // 여기서 바뀌는건

        for(int i = 0 ; i < now_x ;i++){
            HashMap<Integer,Integer> hm = new HashMap();
            for(int j = 0 ; j < now_y ;j++){
                // key 존재하면 val ++ , 없으면 1
                if(A[i][j] != 0)
                    hm.put(A[i][j],hm.getOrDefault(A[i][j],0)+1);
            }
            // 여기서 hm 돌면서 새로운 배열에 넣기
            int[][] temp = new int[hm.keySet().size()][2];

            int l = 0;
            for(int key : hm.keySet()){
                temp[l][0] = key;
                temp[l][1] = hm.get(key);
                l++;
            }

            /*
            - 정렬 방법
                  등장 횟수 오름차순
                  같다면 수 오름차순
             */
            Arrays.sort(temp,(o1,o2)->{
                if(o1[1] == o2[1]) return o1[0]-o2[0];
                return o1[1] - o2[1];
            });

            // 정렬후 현재 A에 집어 넣기
            int p = 0;
            for(int[] temp_arr : temp){
                if(p >= 100) break;
                A[i][p] = temp_arr[0];
                p++;
                if(p >= 100) break;
                A[i][p] = temp_arr[1];
                p++;
            }

            for(int pp = p; pp < 100 ; pp++){
                A[i][pp] = 0;
            }

            now_y = Math.max(now_y,p);
        }


    }

    static void cOperation(){

        for(int i = 0 ; i < now_y ;i++){
            HashMap<Integer,Integer> hm = new HashMap();
            for(int j = 0 ; j < now_x ;j++){
                // key 존재하면 val ++ , 없으면 1
                if(A[j][i] != 0)
                    hm.put(A[j][i],hm.getOrDefault(A[j][i],0)+1);
            }
            // 여기서 hm 돌면서 새로운 배열에 넣기
            int[][] temp = new int[hm.keySet().size()][2];

            int l = 0;
            for(int key : hm.keySet()){
                temp[l][0] = key;
                temp[l][1] = hm.get(key);
                l++;
            }

            Arrays.sort(temp,(o1,o2)->{
                if(o1[1] == o2[1]) return o1[0]-o2[0];
                return o1[1] - o2[1];
            });

            int p = 0;
            for(int[] temp_arr : temp){
                if(p >= 100) break;
                A[p][i] = temp_arr[0];
                p++;
                if(p >= 100) break;
                A[p][i] = temp_arr[1];
                p++;
            }

            for(int pp = p; pp < 100 ; pp++){
                A[pp][i] = 0;
            }

            now_x = Math.max(now_x,p);
        }

    }


}
