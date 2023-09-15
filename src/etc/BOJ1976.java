package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976 {
    /*
        N개의 도시 존재
            -> 도시 사이에 길존재 (그래프)

        여행계획이 가능한지 판별

        도시 수 : N (200)
        여행 도시 수 : M (1000)

        연결정보 : N*N

        마지막 줄 : 여행 계획 (도시번호는 1~N) -> M개의 숫자

        => 유니온 파인드 문제인듯??

        결국 하나의 그래프면 어떻게든 갈수있음
        여행 계획이 다른 그래프인지 체크!!

        도시가 같은 그래프인가?
     */
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                if(Integer.parseInt(st.nextToken())== 1){
                    union(i,j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken())-1;

        for(int i = 0 ; i < M-1 ; i++){
            int next = Integer.parseInt(st.nextToken())-1;
            if(parent[start] != parent[next]){ // 다르면 다른 그래프
                // 다른 그래프인 next가 하나라도 존재하면 NO 출력
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");


    }
    // x의 부모를 찾는 연산
    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    // y의 부모를 x의 부모로 치환하는 연산 (x > y 일 경우, 반대)
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x < y) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
        }
    }
}
