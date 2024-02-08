import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2412 {

    public static class node{
        public int x;
        public boolean visit;

        public node(int x, boolean visit){
            this.x= x;
            this.visit = visit;
        }
    }
    static int n, T;
    static ArrayList<node>[] rock;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        rock = new ArrayList[200001];
        for (int i = 0; i < 200001; i++) {
            rock[i] = new ArrayList<node>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            rock[y].add(new node(x,false));
        }

        for (int i = 0; i < 200001; i++) {
            Collections.sort(rock[i],(o1,o2)->{
                return o1.x-o2.x;
            });
        }

        System.out.println(bfs());
        br.close();
    }

    public static int bfs() {

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0,0});

        int move = 0;
        while (!q.isEmpty()) {

            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] now = q.poll();
                if (now[1] == T) return move;

                for (int y = now[1] - 2; y <= now[1] + 2; y++) {
                    if (y < 0 || y >= 200001) continue;

                    // rock[y]에서 now[0]-2 idx 찾기
                    // now[0] - 2 보다 큰 값 찾기
                    // FFFTTT
                    int left = -1;
                    int right = rock[y].size();

                    while(left+1 < right){
                        int mid = (left+right)/2;

                        if(rock[y].get(mid).x >= now[0]-2){
                            right = mid;
                        }else{
                            left = mid;
                        }
                    }
                    // 여기서 right 값이 rock[y].size() 랑 같으면 없는거
                    int start = right;

                    // TTTFFF , T들중 최대 idx 찾기
                    left = -1;
                    right = rock[y].size();

                    while(left+1 < right){
                        int mid = (left+right)/2;

                        if(rock[y].get(mid).x <= now[0]+2){
                            left = mid;
                        }else{
                            right = mid;
                        }
                    }
                    int end = left;

                    for(int j = start ; j <= end ; j++){
                        node n = rock[y].get(j);
                        if(!n.visit){
                            q.add(new int[] {n.x,y});
                            n.visit = true;
                        }
                    }

                }
            }
            move++;
        }

        return -1;
    }
}
