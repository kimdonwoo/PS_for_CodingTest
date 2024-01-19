package Topological;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        while(T --> 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 1000
            int K = Integer.parseInt(st.nextToken()); // 100_000
            long res = 0;

            st = new StringTokenizer(br.readLine());
            HashMap<Integer,Integer> hm = new HashMap<>();

            for(int i = 0 ; i < N ; i++){
                hm.put(i,Integer.parseInt(st.nextToken()));
            }

            ArrayList<Integer>[] map = new ArrayList[N];
            for(int i = 0 ; i < N ; i++){
                map[i]=new ArrayList<>();
            }
            int[] cnt = new int[N];

            for(int i = 0 ; i < K ; i++){
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken())-1;
                int Y = Integer.parseInt(st.nextToken())-1;

                map[X].add(Y);
                cnt[Y]++;
            }

            st = new StringTokenizer(br.readLine());
            int goal = Integer.parseInt(st.nextToken())-1;

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
                return o1[1]-o2[1];
            });

            boolean check = false;

            for(int i = 0 ; i < N ; i++){
               if(cnt[i] == 0){
                   if(i == goal){
                       System.out.println(hm.get(i));
                       check = true;
                       break;
                   }
                   pq.add(new int[] {i,hm.get(i)});
               }
            }
            if(check) continue;

            while(!pq.isEmpty()){
                int[] now = pq.poll();

                for(int next : map[now[0]]){
                    cnt[next]--;
                    if(cnt[next] == 0){
                        if(next == goal){
                            System.out.println(now[1]+hm.get(next));
                            check = true;
                            break;
                        }
                        pq.add(new int[] {next,now[1]+hm.get(next)});
                    }
                }
                if(check) break;
            }

        }
    }
}
