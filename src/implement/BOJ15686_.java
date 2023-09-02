package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686_ {
    static int n, m;
    static int[][] map;
    static ArrayList<int[]> house = new ArrayList<>();
    static ArrayList<int[]> chicken = new ArrayList<>();
    static ArrayList<int[]> choice = new ArrayList<>();
    static int result = Integer.MAX_VALUE;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 집 좌표 저장
                if (map[i][j] == 1) {
                    house.add(new int[]{i, j});
                }
                // 치킨집 좌표 저장
                if (map[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }

        visit = new boolean[chicken.size()];

        back(0, 0);
        System.out.println(result);

    }

    static void back(int depth, int start) {
        // 치킨 집 m 개 선택했을 때
        if (depth == m) {
            int sum = 0;
            // 선택한 집에서 모든 선택된 치킨집과의 거리중 최소값을 구한다. -> 이게 min
            // 모든 집 마다 돌면서 min 값들의 합을 구한다 -> sum
            // sum 값중에 최소 값을 구한다 그 값이 답
            for (int[] h : house) {
                int min = Integer.MAX_VALUE;
                for (int[] c : choice) {
                    int d = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
                    min = Math.min(d, min);
                }
                sum += min;
            }
            result = Math.min(result, sum);
            return;
        }

        // 치킨 집 선택
        /*
            특정 배열에서 조합 선택을 구현한 원리 -> DFS + 백트래킹
            1. 특정 배열의 size만큼의 크기를 가진 visit 배열을 만든다.
            2. for 문에서 특정 배열을 돌면서 방문안했으면 visit 배열 true로 바꾸고
                선택하고 DFS(다음 단계)에 다시 들어간다.
         */
        for (int i = start; i < chicken.size(); i++) {
            if (!visit[i]) {
                visit[i] = true;
                choice.add(chicken.get(i));
                back(depth + 1, i + 1);
                choice.remove(choice.size() - 1);// 추가
                visit[i] = false;
            }
        }
    }
}
