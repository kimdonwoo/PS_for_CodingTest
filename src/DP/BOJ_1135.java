package DP;

import java.io.*;
import java.util.*;

public class BOJ_1135 {

    static List<Integer>[] list;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new ArrayList[n];
        int rt=0;
        dp = new int[n];

        for(int i=0; i<n; i++) {
            list[i] = new ArrayList<>();
            int a = Integer.parseInt(st.nextToken());
            if(a==-1) {
                rt=i;
            }else {
                // 자식으로 추가
                list[a].add(i);
            }
        }
        int min = solve(rt);
        System.out.println(min);
    }

    static int solve(int idx) {
        for(int nxt : list[idx]) {
            dp[nxt] = 1+ solve(nxt);
        }
        Collections.sort(list[idx], (o1,o2)->{
            return dp[o2]-dp[o1];
        });

        int res =0;

        for(int i=0; i < list[idx].size(); i++) {
            int num = list[idx].get(i);
            dp[num] +=i;
            res = Math.max(res, dp[num]);
        }

        return res;
    }
}