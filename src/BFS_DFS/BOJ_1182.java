package BFS_DFS;

import java.util.*;
import java.io.*;


public class BOJ_1182 {
    static int N, S;
    static int arr[], result[];
    static int cnt;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        if(S == 0) {
            System.out.println(cnt-1);
        }else {
            System.out.println(cnt);
        }
    }

    private static void dfs(int index, int sum) {
        if(index == N) {
            if(sum == S) {
                cnt++;
            }
            return;
        }

        // 선택하느냐 안하느냐
        dfs(index+1, sum+arr[index]);
        dfs(index+1, sum);
    }
}
