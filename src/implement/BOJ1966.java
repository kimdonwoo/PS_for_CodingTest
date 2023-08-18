package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1966 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N ; i ++){

            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int target_index = Integer.parseInt(st.nextToken());
            Queue<int[]> q = new LinkedList<>();

            int[] arr = new int[M];

            st = new StringTokenizer(br.readLine());
            // 제일 첫 큐 완성! + 내림차순 정렬 배열
            for(int j = 0 ; j < M ; j++){
                int num = Integer.parseInt(st.nextToken());
                q.add(new int[] {j,num});
                arr[j] = (-1)*num;
            }
            // arr 꺼낼때 -1 곱해서 사용하기
            Arrays.sort(arr);
            int it = 0;

            while(true){ // 수정
                int[] val = q.poll();
                if(val[1] == (-1)*arr[it]){
                    it++;
                    if(val[0] == target_index){
                        System.out.println(it);
                        break;
                    }
                }else{
                    q.add(new int[] {val[0],val[1]});
                }

            }


        }



    }
}
