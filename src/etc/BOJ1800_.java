package etc;


import java.util.*;

public class BOJ1800_ {
    static int N, P, K, ans = Integer.MAX_VALUE;
    static ArrayList<int[]>[] adj;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        P = sc.nextInt();
        K = sc.nextInt();

        adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++)
            adj[i] = new ArrayList();

        for (int i = 0; i < P; i++) {
            int n1 = sc.nextInt(), n2 = sc.nextInt(), cost = sc.nextInt();

            adj[n1].add(new int[]{n2, cost});
            adj[n2].add(new int[]{n1, cost});
        }

        // 즉, 최소 돈의 범위가 0 ~ 1_000_000이고
        // 이분탐색을 통해 최소 돈 하나 정해서 DFS돌기
        // 이렇게 적절한 돈 찾기
        divide(0, 1000000);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void divide(int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;

            // 만족하는 경우 ans에 저장하고
            // end를 줄여줌 (최소를 찾는 과정이라)
            if (dfs(mid)) {
                ans = mid;
                end = mid - 1;
            } else
                start = mid + 1;
        }
    }

    // x값이 최소라고 생각했을때 다익 돌리기
    static boolean dfs(int x) {

        Stack<int[]> stack = new Stack<>();
        boolean[][] visit = new boolean[N + 1][K + 1];

        stack.push(new int[]{1, K});

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            if (cur[0] == N) return true;

            if (visit[cur[0]][cur[1]]) continue;
            visit[cur[0]][cur[1]] = true;

            for (int[] a : adj[cur[0]]) {
                if (a[1] <= x) stack.push(new int[]{a[0], cur[1]});
                else if (cur[1] > 0) stack.push(new int[]{a[0], cur[1] - 1});
            }
        }

        return false;
    }
}


/*
    단순히 DFS를 돌려버리면 터지게 된다
    다익스트라 + 결정문제로 바꿔서 해결해야 함..
        => x원 이하의 비용으로 1번 컴퓨터와 N번 컴퓨터를 연결 시킬 수 있는지 판별

        즉, 최소값을 미리 정한 뒤 탐색
        => 최소값 x로 두고 x이하인 선은 그냥 연결을 하고, x이상인  선은 K개까지 무료로 연결

    x는 이분탐색을 통해 최소값의 범위를 지정해줌






public class BOJ1800_ {
    static int N,P,K;
    static ArrayList<Integer> values = new ArrayList<>();
    static boolean[] visit;
    static int res = Integer.MAX_VALUE;
    static int[] temps;
    static ArrayList<ArrayList<int[]>> map = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N+1 ; i++){
            map.add(new ArrayList<>());
        }

        for(int i = 0 ; i < P ; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map.get(a).add(new int[] {b,val});
            map.get(b).add(new int[] {a,val});
        }

        visit = new boolean[N+1];
        visit[1] = true;
        DFS(1);

        if(res == 0){
            System.out.println(-1);
        }else {
            System.out.println(res);
        }
    }


    static void DFS(int now){
        if(values.size() > K){
            temps = new int[values.size()];
            for(int i = 0 ; i < values.size() ; i++){
                temps[i] = values.get(i);
            }
            Arrays.sort(temps);
            if(res <= temps[temps.length -1 -K]) return;
            if(now == N) res = Math.min(res,temps[temps.length -1 -K]);

        }

        for(int[] t :map.get(now)){
            if(!visit[t[0]]) {
                visit[t[0]] = true;
                values.add(t[1]);
                DFS(t[0]);
                values.remove(values.size()-1);
                visit[t[0]] = false;
            }
        }
    }

}


 */