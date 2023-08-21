package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ2002 {
    /*

        (앞) 1 2 3 4 5
             2 5 4 1 3
        => 3대

        여기서 한대씩 검사?
            뒤는 상관안해도됨
            특정 차량마다 원래 앞에 있던 차량이 그대로 앞에 있으면 추월 x
                차량 마다 앞에 어떤 차가 있는지 Map에 저장
                모든 차량 돌아가면서 i*j로 검사
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        HashMap<String, ArrayList<String>> next_hm = new HashMap<>();
        String[] arr = new String[N];
        String[] next = new String[N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            arr[i]=st.nextToken();
            hm.put(arr[i],new ArrayList<>());
        }

        // hm 초기화
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < i ; j++){
                hm.get(arr[i]).add(arr[j]);
            }
        }


        int res = 0;

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            next[i]=st.nextToken();
            next_hm.put(next[i],new ArrayList<>());
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < i ; j++){
                next_hm.get(next[i]).add(next[j]);
            }
        }


        for(String k : hm.keySet()){
            int count = 0;
            // 해당 arraylist의 원소들이 모두 hm_next에 포함되어 있는지 체크
            ArrayList<String> checks = hm.get(k);
            ArrayList<String> comp = next_hm.get(k);
            for (String check : checks){
                if(comp.contains(check)){
                    count++;
                }
            }

            if(count == checks.size()) res++;

        }


        System.out.println(N - res);



    }

}