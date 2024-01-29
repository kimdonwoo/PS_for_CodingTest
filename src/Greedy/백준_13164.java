package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_13164 {

    static int n,k,result;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();

    public static void ps_13164(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1 ; i < n ; i++){
            list.add(arr[i]-arr[i-1]);
        }

        Collections.sort(list);

        for(int i = 0 ; i < n-k ; i++){
            result += list.get(i);
        }

        System.out.println(result);

    }
}
