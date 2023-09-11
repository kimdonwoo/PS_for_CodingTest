package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class hast7_2 {
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 1 2 3 5 6
        HashMap<Integer,Integer> hm = new HashMap<>();

        for(int i = 0 ; i < n ; i++){
            hm.put(arr[i],i);
            // 1 0 , 2 1 , 3 2 ,5 3 , 6 4
        }

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            // 1 2 3 5 6 : q = 3 -> 4
            int num = hm.getOrDefault(q,0); // 2
            System.out.println(num*(n-1-num));
            // q*(n-1-q)
        }

    }
}
