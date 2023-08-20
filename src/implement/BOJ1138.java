package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1138 {
    static int[] sol;
    static boolean[] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        sol = new int[N];
        visited = new boolean[N];
        ArrayList<Integer> now = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            sol[i] =Integer.parseInt(st.nextToken());
        }

        DFS(now,0);

    }

    static void DFS(ArrayList<Integer> now, int idx){

        if(idx == N){
            for(Integer num : now) System.out.print(num+" ");
            return;
        }

        for(int i = 0 ; i < N; i++){

            // 1. sol[i]가 현재 방문안된 상태이며, 현재 자리에 놓을 수 있으면 다음 DFS
            if(visited[i]) continue;

            // 2. sol[i]값이랑 now 비교한 값이 일치하면 놓기
            int count = 0;
            for(Integer num : now){
                if(num > i+1) count++;
            }
            if(count == sol[i]){ // 즉 이까지 오면 now에 추가할 수 있는 애들임
                visited[i] = true;
                now.add(i+1);
                DFS(now,idx+1);
                now.remove(now.size()-1);
                visited[i] = false;
            }

        }

    }

}

/*
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int n = sn.nextInt();
        List<Integer> a = new ArrayList<>();

        int []left = new int[n];

        for (int i = 0 ; i < n ; i++){
            left[i] = sn.nextInt();
        } // 2 1 1 0


        for (int i = n - 1 ; i >= 0 ; i--){
            a.add(left[i], i + 1);
        }

        for (int answer : a){
            System.out.print(answer + " ");
        }
    }
    => ㄹㅈㄷ 풀이..



 */