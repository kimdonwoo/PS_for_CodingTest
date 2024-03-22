package BFS_DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_22946 {
    static class Circle{
        int idx;
        long x;
        long y;
        long r;

        Circle( long x, long y, long r){
            this.idx = -1;
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    static ArrayList<Circle>[] tree;
    static boolean[] visit;
    static int res = 0;
    static int N;
    static Circle[] circles;
    static int[] maxDepth;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        circles = new Circle[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            circles[i] = new Circle(x, y, r);
        }
        circles[0] = new Circle(0, 0, 10_000_000);

        // 원들을 반지름 큰 순서대로 정렬
        Arrays.sort(circles, (o1, o2) -> {
            return (int)(o2.r - o1.r);
        });

        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            circles[i].idx = i;
            tree[i] = new ArrayList<>();
        }

        // tree 만들기
        // root 원부터 들어가기
        visit = new boolean[N+1];
        visit[0] = true;
        makeTree(0);

        // tree depth 만들기
        maxDepth = new int[N+1];
        maxDepth[0] = makeDepth(0);

        // dfs
        searhTree(0);

        System.out.println(res);


    }

    private static void searhTree(int now) {

        if(tree[now].size() >= 2){
            // 여긴 그냥 자식들 중 최대 깊이 2개 더하기
            int temp = -1;
            int maxIdx = -1;
            int maxCnt = -1;
            for(Circle c : tree[now]){
                if(maxCnt < maxDepth[c.idx]){
                    maxCnt = maxDepth[c.idx];
                    maxIdx = c.idx;
                }
            }

            for(Circle c : tree[now]){
                if(c.idx == maxIdx) continue;
                temp = Math.max(temp,maxCnt+maxDepth[c.idx]);
            }

            res = Math.max(res,temp);
        }else{
            res = Math.max(res,maxDepth[now]-1);
            // 만약 now가 1개 있는 상황이면 ?
        }

        for(Circle c : tree[now]){
            searhTree(c.idx);
        }
    }

    private static int makeDepth(int now) {

        if(tree[now].size() == 0){
            return maxDepth[now] = 1;
        }

        for(Circle c : tree[now]){
            maxDepth[now] = Math.max(maxDepth[now],makeDepth(c.idx)+1);
        }
        return maxDepth[now];
    }
    // 지금 idx랑
    private static void makeTree(int now) {
        // 반지름 내림차순이기 때문에
        // 점점 들어가기
        for(int i = now+1 ; i <= N ; i++){
            // 방문안한곳이면 자식 노드가 될수도 있음
            if(!visit[i]){
                long d2 = (circles[i].x - circles[now].x) * (circles[i].x - circles[now].x) +
                        (circles[i].y - circles[now].y) * (circles[i].y - circles[now].y);
                long r2 = (circles[now].r - circles[i].r) * (circles[now].r - circles[i].r);

                if (d2 < r2) {
                    // 만약 i가 자식 노드라면
                    visit[i] = true;
                    tree[now].add(circles[i]);
                    makeTree(i);
                }
            }
        }
    }
}

/*
    1 -> 2,6
    2 -> 6

    각 노드들의 최대 깊이 2개 기억하기?
    그리고 한번더 돌리기 ?
    최대 깊이 아니면


    그래프는 사실 트리다
        root()
         4(4)
     1(4)  3(3)  5(1)  7
    2(4)   (3)
    6(4)


    다시 모든 노드 돌면서 자식 노드 두개이상이면 큰값 2개 합치기

    부모는 무조건 반지름이 더 크다
    ->

    해당 그래프의 특정 두 노드사이의 최대거리를 구하는 문제
    해당 문제의 핵심은
    1. 트리 만들기 ㅇ
    2. 트리 각 노드에서 후위로 기억하기
        -> 흠.. 뭘 기억 ?
        -> dp로 자신의 자식노드중 최고의 깊이를 기억하기 ?


     먼저 트리만들기는
     반 지름 큰것을 기준으로 정렬
     -> dfs ?
     visit

     현재 노드에서


 */