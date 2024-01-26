package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9466 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T --> 0){
            int N = Integer.parseInt(br.readLine());
            int[] nums = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i++){
                nums[i] = Integer.parseInt(st.nextToken())-1;
            }

            boolean[] visit = new boolean[N];
            ArrayList<Integer> hist;
            int res = 0;

            for(int i = 0 ; i < N ; i++){
                if(!visit[i]){
                    hist = new ArrayList<>();
                    // 팀 못구한 사람 반환
                    res += DFS(i,nums,hist,visit);
                }
            }

            System.out.println(res);
        }
    }

    private static int DFS(int now, int[] nums, ArrayList<Integer> hist, boolean[] visit) {

        // 첫 노드 들어옴
        if(visit[now]){
            // 방문 했었으면 hist로 now 있는지 확인하고
            if(hist.contains(now)){
                return hist.indexOf(now);
            }else {
                return hist.size();
            }
        }

        // 처음 간곳이면
        visit[now] = true;
        hist.add(now);

        return DFS(nums[now],nums,hist,visit);
    }
}
