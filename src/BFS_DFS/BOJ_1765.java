package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1765 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; //

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        HashMap<Integer,Boolean>[] friends = new HashMap[N];
        for(int i = 0 ; i < N ; i++) friends[i] = new HashMap<>();
        HashMap<Integer,Boolean>[] enemys = new HashMap[N];
        for(int i = 0 ; i < N ; i++) enemys[i] = new HashMap<>();


        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            if(temp.equals("F")){
                friends[a].put(b,true);
                friends[b].put(a,true);
            }else{
                enemys[a].put(b,true);
                enemys[b].put(a,true);
            }
        }

        // enemy로 친구만들기 (이게 시간초과가 나려나?)
        for(int i = 0 ; i < N ; i++){
            Set<Integer> keys = enemys[i].keySet();
            for(int now : keys){
                for(int next : keys){
                    if(now >= next) continue;
                    // now랑 next는 친구관계임
                    if(friends[now].containsKey(next)) continue;
                    friends[now].put(next,true);
                    friends[next].put(now,true);
                }
            }
        }


        // 팀 갯수 세기
        int cnt = 0;
        boolean[] visit = new boolean[N];
        for(int i = 0 ; i < N ; i++){
            if(!visit[i]){
                cnt++;
                visit[i] = true;
                dfs(i,visit,friends);
            }
        }

        System.out.println(cnt);

    }

    private static void dfs(int now, boolean[] visit, HashMap<Integer,Boolean>[] friends){

        for(int k : friends[now].keySet()){
            if(!visit[k]){
                visit[k] = true;
                dfs(k,visit,friends);
            }
        }
    }
}
