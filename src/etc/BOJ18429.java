package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18429 {
    static int N;
    static int[] exer;
    static int count = 0;
    static boolean[] visit;
    static int now = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        exer = new int[N];
        visit = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            exer[i] = Integer.parseInt(st.nextToken())-K;
        }

        soon(0);

        System.out.print(count);

    }

    static void soon(int depth){

        if(depth == N){
            count++;
            return;
        }

        // -1 3 1
        for(int i = 0 ; i < N ; i++){
            if(!visit[i]){
                if(now + exer[i] >= 0){
                    visit[i] = true;
                    now += exer[i];
                    soon(depth+1);
                    now -= exer[i];
                    visit[i] = false;
                }
            }
        }
    }
}
