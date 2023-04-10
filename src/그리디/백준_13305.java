package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 백준_13305 {
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Long n = Long.parseLong(st.nextToken());

        ArrayList<Long> dist = new ArrayList<>();
        ArrayList<Long> price = new ArrayList<>();


        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n-1 ; i++){
            dist.add(Long.parseLong(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n ; i++){
            price.add(Long.parseLong(st.nextToken()));
        }

        Long temp = Long.MAX_VALUE;
        Long res =0L;

        for(int i = 0; i < n-1 ; i++){
            if(temp > price.get(i)){
                temp = price.get(i);
            }

            res+=temp*dist.get(i);
        }
        System.out.println(res);

    }
}





