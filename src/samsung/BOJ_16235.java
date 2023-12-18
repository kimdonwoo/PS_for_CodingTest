package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16235 {
    static int N,M,K;
    static int[][] ground;
    static int[][] plus;
    static int[] dx ={0,0,1,1,1,-1,-1,-1};
    static int[] dy ={1,-1,0,1,-1,0,1,-1};

    static ArrayList<int[]> trees = new ArrayList<>();
    static Deque<Integer> deadTrees = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ground = new int[N][N];
        plus = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                plus[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = 5;
            }
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new int[] {x,y,age});
        }

        // K년
        for(int i = 0 ; i < K ; i++){
            springSummer();
            fall();
            winter();
        }

        // 살아남은 나무의 수 출력
        System.out.println(trees.size());

    }

    private static void springSummer() {
        Collections.sort(trees,(o1,o2)->{
            // 나무 나이에 따라 정렬
            return o1[2]-o2[2];
        });
        ArrayList<int[]> tempTrees = new ArrayList<>();
        for(int i = 0 ; i < trees.size() ; i++){
            // 양분 먹을 수 있으면
            int[] t = trees.get(i);
            if(ground[t[0]][t[1]] >= t[2]){
                ground[t[0]][t[1]]-=t[2];
                t[2]++;
                tempTrees.add(t);
            }else{
                // 못먹으면
                deadTrees.add(i);
            }
        }
        //

        while(!deadTrees.isEmpty()){
            int idx = deadTrees.pollLast();
            int[] tree = trees.get(idx);
            ground[tree[0]][tree[1]] += tree[2]/2;
            // remove는 굉장히 연산 느림
            //trees.remove(idx);
        }
        trees = tempTrees;
    }

    private static void fall() {
        ArrayList<int[]> temp = new ArrayList<>();
        for(int[] t : trees){
            temp.add(t);
            if(t[2]%5 == 0){
                for(int i = 0 ; i < 8 ; i++){
                    int nx = t[0]+dx[i];
                    int ny = t[1]+dy[i];

                    if(nx >= 0 && nx < N && ny >= 0 && ny < N){
                        temp.add(new int[] {nx,ny,1});
                    }
                }
            }
        }
        trees = temp;
    }

    private static void winter() {
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                ground[i][j] +=plus[i][j];
            }
        }
    }
}
