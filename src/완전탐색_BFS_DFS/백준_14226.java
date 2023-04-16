package 완전탐색_BFS_DFS;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 백준_14226 {

     public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);

            System.out.print(bfs(sc.nextInt()));
        }

        public static int bfs(int n) {
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[1001][1001];
            // {화면, 클립보드, 시간}
            // 최소 시간이니깐 bfs로 먼저 발견하면 최소 시간임
            // (dfs로하면 발견할때마다 시간 비교해줘야 함)
            queue.add(new int[] {1, 0, 0});
            visited[1][0] = true;

            while(!queue.isEmpty()) {
                int[] now = queue.poll();

                if(now[0] == n) {
                    // 찾았을 때 시간 출력 - 이게 답
                    return now[2];
                }

                if(now[0] > 0 && now[0] < 1001) {
                    // 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
                    if(!visited[now[0]][now[0]]) {
                        visited[now[0]][now[0]] = true;
                        queue.add(new int[] {now[0], now[0], now[2] + 1});
                    }
                    // 화면에 있는 이모티콘 중 하나를 삭제한다.
                    if(!visited[now[0] - 1][now[1]]) {
                        visited[now[0] - 1][now[1]] = true;
                        queue.add(new int[] {now[0] - 1, now[1], now[2] + 1});
                    }
                }

                // 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
                if(now[1] >= 0 && now[0] + now[1] < 1001) {
                    if(!visited[now[0] + now[1]][now[1]]) {
                        visited[now[0] + now[1]][now[1]] = true;
                        queue.add(new int[] {now[0] + now[1], now[1], now[2] + 1});
                    }
                }
            }
            return 0;
        }
    }







