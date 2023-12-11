package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501 {

    public static int N;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+2];

        for(int i = 1 ; i < N+1 ; i++){
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            if(i+T <= N+1){
                arr[i+T] = Math.max(arr[i+T],arr[i]+P);
            }
            arr[i+1] = Math.max(arr[i+1],arr[i]);
        }

        System.out.println(arr[N+1]);

    }
}
