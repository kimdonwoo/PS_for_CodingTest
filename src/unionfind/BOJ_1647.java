package unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1647 {

    public static class Road{
        int a;
        int b;
        int cost;
        Road(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException{

     /*
        1. 길을 오름차순 정렬
        2. N-2개 선택한다.
        3. 선택할때, 이미 같은 그룹이면
            패스해야함
        4. 흠.. 유니온파인드 ?
      */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N =Integer.parseInt(st.nextToken());
        int M =Integer.parseInt(st.nextToken());

        ArrayList<Road> roads = new ArrayList<>();

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roads.add(new Road(x,y,cost));
        }

        // cost 오름차순
        Collections.sort(roads,(o1,o2)->{
            return o1.cost-o2.cost;
        });


        // 여기서 유니온파인드 하면서 N-2개 선택
        int[] parent = new int[N+1];
        for(int i = 0 ; i < N+1 ; i++){
            parent[i] = i;
        }

        int num = 0;
        int res = 0;
        for(Road r : roads){

            if(num == N-2) break;

            // 이미 같은 그룹이면 패스
            if(find(r.a,parent) == find(r.b,parent)){
                continue;
            }else{
                // 다른 그룹이면 선택
                num++;
                res += r.cost;

                // 합치기
                union(r.a,r.b,parent);
            }
        }

        System.out.println(res);
    }

    private static void union(int a, int b, int[] parent) {
        // 다른 그룹인 상황임
        a = find(a,parent);
        b = find(b,parent);

        if(a < b) {
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }

    // 부모찾기
    private static int find(int a,int[] parent) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a],parent);
    }
}
