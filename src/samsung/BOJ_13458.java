package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_13458 {

    public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long sum = 0;
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N ; i++){

            arr[i]-=B;
            sum++;
            if(arr[i] <= 0){
                continue;
            }
            if(arr[i] % C != 0) sum++;
            sum+= arr[i]/C;
        }

        System.out.println(sum);


        /*
            N개의 시험장

            총감독 : 감시 응시자의 수 B명
            부감독 : C명

            => 총감 1명, 부감 여러 명 가능

            이 상황에 감독관 수의 최솟값을 구해라

            N = 1_000_000
            1. 그냥 돌려도 문제없을거 같긴함




         */


    }
}
