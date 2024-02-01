package BFS_DFS;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ_22948 {
    static class node{
        int node;
        int cnt;
        String allNode;

        public node(int node,int cnt ,String allNode) {
            this.node = node;
            this.cnt = cnt;
            this.allNode = allNode;
        }
    }

    static ArrayList<Integer> [] map;
    static boolean[] visit;
    static int[] printArr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        map = new ArrayList[N+1];
        for(int i = 0 ; i <=N ; i++) map[i] = new ArrayList<>();

        // 좌표순 정렬
        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> { //x = 좌표, y= 번호
            return o1.x - o2.x;
        });

        pq.add(new Point(-10000000,0));
        pq.add(new Point(10000000,0));

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            pq.add(new Point(x-r,k));
            pq.add(new Point(x+r,k));
        }

        makeTree(pq,-1);

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        printArr = new int[N+1];
        visit = new boolean[N+1];

        printArr[0] = A;
        visit[A] = true;
        DFS(A,B,1);

    }

    static void makeTree(PriorityQueue<Point> pq, int parents ){
        Point now = pq.poll();

        // 만약 루트가 아니면 연결
        if(parents != -1) {
            map[parents].add(now.y);
            map[now.y].add(parents);
        }

        // 만약 내부에 다른 원이 존재한다면 makeTree 들어가기
        while(now.y != pq.peek().y){
            makeTree(pq,now.y);
        }
        // 약간 스택 느낌?
        pq.poll();
    }

    private static void DFS(int now, int end,int depth) {
        if(now == end){
            System.out.println(depth);
            for(int i = 0 ; i < depth ; i++){
                System.out.print(printArr[i]+" ");
            }
            return;
        }

        for(int next : map[now]){
            if(!visit[next]){
                visit[next] = true;
                printArr[depth] = next;
                DFS(next,end,depth+1);
            }
        }
    }
}


//public class BOJ_22948 {
//    static class Circle{
//        public int num;
//        public int mid;
//        public int red;
//
//        public Circle(int num, int mid, int red){
//            this.num = num;
//            this.mid = mid;
//            this.red = red;
//        }
//    }
//    static ArrayList<Integer>[] map;
//    static boolean[] visit;
//    static int[] printArr;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        Circle[] arr = new Circle[N];
//        map = new ArrayList[N+1];
//        visit = new boolean[N+1];
//
//        for(int i = 0 ; i <= N ; i++){
//            map[i] = new ArrayList<>();
//        }
//
//        for(int i = 0 ; i < N ; i++){
//            st = new StringTokenizer(br.readLine());
//            int num = Integer.parseInt(st.nextToken());
//            int mid = Integer.parseInt(st.nextToken());
//            int red = Integer.parseInt(st.nextToken());
//
//            arr[i] = new Circle(num,mid,red);
//        }
//
//        Arrays.sort(arr,(o1,o2)->{
//            // 반지름 작은 순
//            // 반지름 같으면 흠.. 번호 작은 순 ? or
//            return o1.red - o2.red;
//        });
//
//        st = new StringTokenizer(br.readLine());
//        int A = Integer.parseInt(st.nextToken());
//        int B = Integer.parseInt(st.nextToken());
//
//        for(int i = 0 ; i < N ; i++){
//            boolean check = false;
//            for(int j = i+1 ; j < N ; j++){
//                if(arr[j].red == arr[i].red) continue;
//                // 자기를 포함하는지 체크
//                if(arr[j].mid - arr[j].red < arr[i].mid && arr[i].mid < arr[j].mid + arr[j].red) {
//                    // j가 i를 포함한 상태임
//                    map[arr[i].num].add(arr[j].num);
//                    map[arr[j].num].add(arr[i].num);
//                    check = true;
//                    break;
//                }
//            }
//            // 만약 아무도 없으면
//            if(!check){
//                map[arr[i].num].add(0);
//                map[0].add(arr[i].num);
//            }
//        }
//
//
//        // 여기서 방문
//        printArr = new int[N+1];
//        printArr[0] = A;
//        visit[A] = true;
//        DFS(A,B,1);
//
//    }
//
//    // depth랑 출력배열 사용?
//    private static void DFS(int now, int end,int depth) {
//        if(now == end){
//            System.out.println(depth);
//            for(int i = 0 ; i < depth ; i++){
//                System.out.print(printArr[i]+" ");
//            }
//            return;
//        }
//
//        for(int next : map[now]){
//            if(!visit[next]){
//                visit[next] = true;
//                printArr[depth] = next;
//                DFS(next,end,depth+1);
//            }
//        }
//
//    }
//}
