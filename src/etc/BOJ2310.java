package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ2310 {
    /*
        n 방을 지나가야함

        방안에 레프리콘 or 트롤
            레프리콘 : 모험가의 소지금을 채워줌
            트롤 : 일정량의 통행료 지불

        n개의 방

        자료구조

        *(1,0) -> 2,3
        (2,201) -> 2,3
        (3,10) -> 4
        (4,-15) -> 1 2 3

     */
    static int N;
    static ArrayList<ArrayList<Integer>> arr;
    static HashMap<Integer,Integer> hm;
    static int nowCoin = 0;
    static boolean check = false;
    static boolean[] visit;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            if(N == 0) break;

            arr = new ArrayList<>();
            hm = new HashMap<>();
            visit = new boolean[N];

            for(int i = 0 ; i < N+1 ; i++) arr.add(new ArrayList<>());

            for(int i = 0 ; i < N ;i++){
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int coin = Integer.parseInt(st.nextToken());

                if(c == 'E'){
                    hm.put(i+1,coin);
                }else if(c == 'L'){
                    hm.put(i+1,coin);
                }else{
                    hm.put(i+1,(-1)*coin);
                }

                while(true){
                    int temp = Integer.parseInt(st.nextToken());
                    if(temp == 0) break;
                    arr.get(i+1).add(temp);

                }
            }
            check = false;
            visit[0] = true;
            DFS(1);

            if(check){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }

        }


    }

    static void DFS(int now){
        // now가 N이면 YES 출력
        if(now==N){
            check = true;
            return;
        }

        for(int next : arr.get(now)){
            /*
                만약 hm.get(next)가 0보다 크면 nowCoin = Math.max(nowCoin,hm.get(next))

             */
            if(hm.get(next) > 0 && !visit[next-1]){
                int temp = nowCoin;
                nowCoin = Math.max(nowCoin,hm.get(next));
                visit[next-1] = true;
                DFS(next);
                visit[next-1] = false;
                nowCoin = temp;

            }else if(nowCoin + hm.get(next) >= 0 && !visit[next-1]){
                nowCoin += hm.get(next);
                visit[next-1] = true;
                DFS(next);
                visit[next-1] = false;
                nowCoin -= hm.get(next);
            }
        }
    }

}
