package prac_zone;

import java.io.IOException;

public class DFS_combi_3 {

    /*
        3. 선택된 여러명이 각자 4방향으로 움직이는 상황
            - 선택하는 숫자가 작으면 그냥 반복문으로 돌리면 됨
            - 하지만 많으면 combi로 선택해야할 듯?
     */

    static int[] select;
    static int N;
    static int allCnt = 0;

    public static void main(String[] args) throws IOException {

        N = 3;
        select = new int[N];

        for(int i = 0 ; i < N ; i++){
            select[i] = i;
        }

        combi(0);

        System.out.println(allCnt);
    }

    private static void combi(int idx) {

        if(idx == N){
            // 여기서 문제 로직 추가
            allCnt++;
            System.out.println();
            for(int i = 0 ; i < N ; i++){
                System.out.print(select[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i = 0 ; i < 4 ; i++){
            select[idx] = i;
            combi(idx+1);
        }
    }
}
