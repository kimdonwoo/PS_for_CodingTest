package shortest_distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

    플로이드-와샬(Folyd-Warshall) 알고리즘
    - 그래프의 최단 경로 구하는 알고리즘 하나
    - 모든 정점에서 모든 정점까지 최단 거리를 구함
    - 음수 사이클이 없어야함 (음수 가중치는 허용)
    - 그래프 비용 인접 행렬로 표현되어 있다고 가정
    - 시간 복잡도 O(n^3)
 */


public class FloydWrasahll_basic {
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        //그래프 입력 받기
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // 정점의 개수, 간선의 개수
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());

        int[][] graph = new int[n + 1][n + 1];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if(i == j) continue;
                graph[i][j] = INF;
            }
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[v][w] = cost;
        }

        //플로이드 알고리즘 수행
        floyd(graph, n);
    }

    public static void floyd(int[][] graph, int n) {
        // 경유지
        for (int k = 1; k <= n; k++) {
            // 출발지
            for (int i = 1; i <= n; i++) {
                //도착지
                for (int j = 1; j <= n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
                }
            }
        }

        //출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(graph[i][j] == INF) System.out.print(0+" ");
                else System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
    }
}
