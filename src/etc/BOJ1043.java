package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1043 {
    static HashMap<Integer,Boolean> hm;
    static ArrayList<ArrayList<Integer>> user = new ArrayList<>();
    static boolean[] visit;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N+1 ; i++){
            user.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        hm = new HashMap<>();

        for(int i = 0 ; i < K ; i++){
            int num = Integer.parseInt(st.nextToken());
            user.get(num).add(0);
            user.get(0).add(num);
        }

        // 파티마다 유저 배열 다시 돌리기 위해
        boolean[][] party = new boolean[M][N+1];

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());

            int first = Integer.parseInt(st.nextToken());
            party[i][first] = true;
            for(int j = 0 ; j < L-1 ; j++){
                int temp = Integer.parseInt(st.nextToken());
                party[i][temp] = true;
                user.get(first).add(temp);
                user.get(temp).add(first);
            }
        }

        // 0 에서부터 돌면서 hashmap 만들기
        visit = new boolean[N+1];
        visit[0] = true;
        DFS(0);

        int res = 0 ;

        for(int i = 0 ; i < M ; i++){
            boolean check = false;
            for(int j = 0 ; j < N+1; j++) {
                if(party[i][j]){ // 파티에 온 사람이면
                    // 포함되어 있는지 체크
                    if(hm.containsKey(j)){
                        // 포함되어 있으면 해당 파티 x
                        check = true;
                        break;
                    }
                }
            }
            if(!check) res++;
        }

        System.out.println(res);

    }
    static void DFS(int now){
        for(int next : user.get(now)){
            if(!visit[next]){
                visit[next] = true;
                hm.put(next,true);
                DFS(next);
            }
        }

    }
}

