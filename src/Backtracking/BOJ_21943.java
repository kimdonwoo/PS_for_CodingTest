package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_21943 {
    static int[] nums;
    static int[] selectNum;
    static boolean[] selectCheck;

    static int res = Integer.MIN_VALUE;
    static int N;
    static ArrayList<boolean[]> orders = new ArrayList<>();
    static boolean[] select;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        nums = new int[N];
        selectNum = new int[N];
        selectCheck = new boolean[N];
        for(int i = 0 ; i < N ; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int P = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        // 필요한 매개 변수
        // 현재 남은 p랑 q, 현재 depth
        select = new boolean[N-1];
        dfs(0,P,Q);

        // 현재 nums로 만들 수 있는 select 만들기
        selectDFS(0);

        System.out.println(res);
    }

    // 연산 경우의 수
    // P,Q 4,1 이면
    private static void dfs(int depth, int p, int q) {
        if(depth == N-1){
            orders.add(select.clone());
            return;
        }

        if(p > 0){
            select[depth] = true;
            dfs(depth+1,p-1,q);
        }

        if(q > 0){
            select[depth] = false;
            dfs(depth+1,p,q-1);
        }
    }

    private static void selectDFS(int depth) {
        if(depth == N){
            // 선택 완료
            res = Math.max(res,countVal());
            return;
        }

        for(int i = 0 ; i < N ; i++){
            if(!selectCheck[i]){
                // 방문 안했으면
                selectCheck[i] = true;
                selectNum[depth] = nums[i];
                selectDFS(depth+1);
                selectCheck[i] = false;
            }
        }
    }


    private static int countVal() {
        int tempRes = Integer.MIN_VALUE;

        // 여기서 현재 selectNum 순서로
        // order 돌면서 계산해서 최댓값 출력
        for(boolean[] b : orders){
            ArrayList<Integer> sumList = new ArrayList<>();
            int sum = selectNum[0];

            for(int i = 0; i < b.length ; i++){
                if(b[i]){
                    // 더하기면
                    sum+= selectNum[i+1];
                }else{
                    // 곱하기면
                    sumList.add(sum);
                    sum = selectNum[i+1];
                }
            }
            sumList.add(sum);

            int x = 1;
            for(int s : sumList) x*=s;

            tempRes = Math.max(tempRes,x);
        }

        return tempRes;
    }
}

/*
    해당 풀이도 근데 값 중복이 많다
    -> 사실 최악의 풀이일듯?


 */