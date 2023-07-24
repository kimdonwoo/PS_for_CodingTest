package BFS_DFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 백준_12851 {
    static int K;
    static int count , time;
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        K = sc.nextInt();
        count = 0;
        time = Integer.MAX_VALUE;

        /*
            제일 처음 수빈이는 N 동생은 K
            수빈이는 현재 위치에서 +1, -1, *2 가 가능하다

            수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초후인지 , 방법은 몇가지 인지??
        */

        BFS(N);

        System.out.println(time);
        System.out.println(count);

    }


    private static void BFS(int n) {
        Queue<int[]> q = new LinkedList<>();
        int[] visited = new int[200001];

        q.add(new int[] {n,0});
        visited[n] = 1;

        while(!q.isEmpty()){
            int[] now = q.poll();

            if(now[1] > time) return;
            if(now[0] == K){

                time = now[1];
                count++;
            }

            // 방문한곳에 now[1]을 저장하고 현재 now[1]이 visited보다 크면
            /*
                visit한곳도 큐에 넣어줘야함
                즉 모든 자리에 visit했을때의 now[1]을 넣어두고
                다시 그 자리에 왔을때 visit 값이 now[1]이 0 이거나 now[1]이랑 같을때만 큐에 추가하고
                    visit값 갱신
             */

            if(0 <= now[0] && now[0] < 100001){

                if(visited[2*now[0]] == 0 ||visited[2*now[0]] == now[1] ){
                    if(2*now[0] != K) visited[2*now[0]] = now[1];
                    q.add(new int[] {2*now[0],now[1]+1});
                }

                if(visited[now[0]+1] ==0  || visited[now[0]+1] == now[1]){
                    if(now[0]+1 != K) visited[now[0]+1] = now[1];
                    q.add(new int[] {now[0]+1,now[1]+1});
                }

                if(now[0] > 0) {
                    if (visited[now[0] - 1] == 0 || visited[now[0] - 1] == now[1]) {
                        if (now[0] - 1 != K) visited[now[0] - 1] = now[1];
                        q.add(new int[]{now[0] - 1, now[1] + 1});
                    }
                }

            }


        }


    }
}
