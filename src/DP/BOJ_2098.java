package DP;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2098 {
    static int N;
    static final int INF = 16_000_000;
    static int[][] map, dp;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][(1<<N)-1];
        for(int i=0;i<N;i++) Arrays.fill(dp[i], -1);

        // 0에서 시작
        System.out.println(dfs(0,1));
    }

    static int dfs(int now, int visit) {

        // 모든 도시를 지난 경우에는 now에서 출발지로 돌아가는 거리 return
        if(visit == (1<<N)-1) {
            // now -> 0(출발도시)로 가는 경로가 존재해야 돌아갈 수 있음
            if(map[now][0] == 0) return INF;
            return map[now][0];
        }

        // 이미 방문한 곳이면 return
        if(dp[now][visit] != -1) return dp[now][visit];
        dp[now][visit] = INF;

        // 여기까지 왔다는건 더 방문할 위치가 존재하고 처음가는 위치라는 것 (즉, dp 계산해줘야함!!)
        for(int i=0;i<N;i++) {
            // now -> 아직 방문하지 않는 i번 도시 가는 경로가 있는 경우
            if((visit & (1<<i)) == 0 && map[now][i] != 0) {
                // d[i][j] = 현재 있는 도시가 i이고 이미 방문한 도시들의 집합이 j일때,
                // 방문하지 않은 나머지 도시들을 모두 방문한 뒤 출발 도시로 돌아올 때 드는 최소 비용.
                // 즉, 방문해야하는 도시(여기에 시작지점으로 돌아오는 것 포함) 들까지 가는 최소 비용
                dp[now][visit] = Math.min(dfs(i, visit | (1 << i)) + map[now][i], dp[now][visit]);   // 최소비용 갱신
                // dfs(다음 도시, 다음도시 방문했다고 가정) + 여기서 다음 도시까지의 거리 와 최소거리 비교
            }
        }

        return dp[now][visit];
    }
}


/*
    DP + 비트마스크
    - 비트마스크를 통해 방문한 도시를 표현할 수 있음
    - **출발 도시가 달라져도, 경로가 같으면 최소 비용이 같음

      dp[cur][visit]
        - cur : 현재 위치한 도시
        - visit : 지금까지 방문한 도시 집합 (비트마스크)
        - dp[cur][visit] : 방문하지 않은 나머지 도시 모두 방문한 뒤, 출발 도시로 돌아갔을 때
            최소 비용

     정리하면 TSP(외판원 순회)는 최단 순환 경로를 탐색해야하는데
     1) N! 의 중복 경로를 제거해주는 DP 메모제이션 기법을 사용한다.
       그래도 2^N의 모든 경우의 수를 표현해야 하기 때문에 그만큼의 공간복잡도가 필요하다.
     2) 메모리 사용량도 줄이고 성능 향상을 위해서 2^N의 경우의 수를 Nbit로
       표현할 수 있는 비트마스킹으로 사용한다.




 */