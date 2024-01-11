package shortest_distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_13549 {
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        if(N >= K) System.out.println(N-K);
        else{

            visit = new boolean[2*K+1];

            // {시간, 현재위치}
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
               return o1[0]-o2[0];
            });

            visit[N] = true;
            pq.add(new int[] {0,N});

            while(!pq.isEmpty()){
                int[] now = pq.poll();
                if(now[1] == K){
                    System.out.println(now[0]);
                    System.exit(0);
                }
                visit[now[1]] = true;


                if(2*now[1] < 2*K+1){
                    if(!visit[2*now[1]])
                        pq.add(new int[] {now[0],2*now[1]});
                }
                if(now[1]+1 < 2*K+1){
                    if(!visit[now[1]+1])
                        pq.add(new int[] {now[0]+1,now[1]+1});
                }

                if(now[1]-1 > 0){
                    if(!visit[now[1]-1])
                        pq.add(new int[] {now[0]+1,now[1]-1});
                }

            }
        }



        /*
            수빈이는 1초 : X-1 X+1 / 0초 : X*2 중에 가기 가능

            K까지 갈수 있는 최단 시간

            N : 100 000
            K : 100 000

            if( N >= K) N-K 하면 될듯
            아니면
            우선순윜큐 써야겠다

            BFS + 우선순위큐
            우선순위큐에는 현재위치+시간
            이미 간곳은 continue 하는게좋을라나 흠..

         */


    }
}
