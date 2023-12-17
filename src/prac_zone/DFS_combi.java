package prac_zone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    DFS로 조합연습하기!!

    1. 기본 조합 : 배열 or ArrayList에서 N개 중에 M개 고르기
        DFS의 매개변수로 idx랑 count가 필수

    - 값들의 의미
        goal : 총 선택 갯수
        count : 조합에서 지금까지 선택한 애들의 갯수
                이번 depth에서는 선택한 애들을 저장하는 배열 select[count]에 저장
        idx : 이번 depth에서는 idx에서부터 N까지 선택하겠다.
        select : 선택한 애들을 저장하는 배열 int[] select = new int[goal]

    - **조합 문제 구조 바꾸기
        만약 map[i][j]에서 여러 개의 (i,j)중에 goal개를 선택해야 하는 상황이라 가정
        - map을 돌면서 선택 후보들 ArrayList cand에 값 넣기
        - 여기서 N은 ArrayList.size()가 되는거
        - select라는 선택된 애들을 저장하는 배열 하나 만들기
        - DFS로 돌면서 select[count]에 값 저장
        - count == goal이 되는 시점에 문제에 맞는 추가적인 로직 적용하기

 */


public class DFS_combi {
    static int N ,goal;
    //static int[] arr = {1,2,3,4,5};
    // 5개중에 2개 고르는 거 연습해보자
    static int[][] map = {
            {0,1,0,1,0},{0,0,1,0,0},{1,0,0,1,0},{0,0,0,0,0},{0,0,0,0,0}
    };
    static ArrayList<int[]> cand = new ArrayList<>();
    static int[][] select;

    public static void main(String[] args) throws Exception {

        // cand에 후보자들 모두 등록
        System.out.print("전체 후보 :");
        for( int i = 0 ; i < 5 ; i++){
            for( int j = 0 ; j < 5 ; j++){
                if(map[i][j] == 1){
                    cand.add(new int[] {i,j});
                    System.out.print("("+i+","+j+") ");
                }
            }
        }

        System.out.println();

        // N개의 후보들 중에 goal개 고르기
        goal = 3;

        // 선택하기
        select = new int[goal][2];
        DFS_comb(0,0);

    }

    private static void DFS_comb(int idx, int count) {
        // 선택 완료
        if(count == goal){
            // 선택한거 출력 or 추가 로직
            for(int[] s : select) System.out.print("("+s[0]+","+s[1]+") ");
            System.out.println();

            // return 필수 !
            return;
        }

        // 선택
        for(int i = idx ; i < cand.size() ; i++){
            // 선택하기
            select[count] = cand.get(i);
            DFS_comb(i+1,count+1);
        }
    }

//    private static void DFS_comb(int idx, int count) {
//        // 모두 선택 완료
//        if(count == goal){
//            for(int i : select) System.out.print(i+" ");
//            System.out.println();
//            return;
//        }
//
//        // 선택
//        for(int i = idx ; i < N; i++){
//            select[count] = arr[i];
//            DFS_comb(i+1,count+1);
//        }
//    }

}
