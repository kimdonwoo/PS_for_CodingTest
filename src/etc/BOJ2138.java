package etc;

import java.util.Scanner;

public class BOJ2138 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int ans1 = 1, ans2 = 0, INF = 987654321;

        String now = sc.next();
        String expect = sc.next();

        int[] now_arr_1 = new int[n];
        int[] now_arr_2 = new int[n];
        int[] expect_arr = new int[n];

        for(int i = 0; i < n; i++) {
            now_arr_1[i] = now.charAt(i)-'0';
            now_arr_2[i] = now.charAt(i)-'0';
            expect_arr[i] = expect.charAt(i)-'0';
        }

        // now_arr_1 의 0번째 버튼 누르기 (0번째 setting)
        now_arr_1[0] = 1-now_arr_1[0];
        now_arr_1[1] = 1-now_arr_1[1];

        // 1부터 n-1까지 돌기
        // 즉, 현재 앞의 전구에 상태에 따라 버튼 누를지 결정
        for(int i = 1; i < n; i++) {

            // 만약 다르면 눌러줘야함
            if(now_arr_1[i-1] != expect_arr[i-1]) {
                now_arr_1[i-1] = 1 - now_arr_1[i-1];
                now_arr_1[i] = 1 - now_arr_1[i];
                ans1++;
                if(i != n-1)
                    now_arr_1[i+1] = 1 - now_arr_1[i+1];

            }

            if(now_arr_2[i-1] != expect_arr[i-1]) {
                now_arr_2[i-1] = 1 - now_arr_2[i-1];
                now_arr_2[i] = 1 - now_arr_2[i];
                ans2++;
                if(i != n-1)
                    now_arr_2[i+1] = 1 - now_arr_2[i+1];
            }
        }

        // 두 경우중 하나는 0~n-2는 일치하는 상황임
        // 그리고 둘 중 n-1을 체크해서 겹치는게 하나라도 있으면
        // 원하는 전구 상태로 만든 상황!! (둘 중하나라도 INF가 아니면 찾은 상황)
        if(now_arr_1[n-1] != expect_arr[n-1]) ans1 = INF;
        if(now_arr_2[n-1] != expect_arr[n-1]) ans2 = INF;


        if(ans1 == INF && ans2 == INF)
            System.out.println(-1);
        else
            System.out.println(Math.min(ans1, ans2));
    }
}