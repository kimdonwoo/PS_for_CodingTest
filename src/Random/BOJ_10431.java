package Random;

import java.util.*;
import java.io.*;

public class BOJ_10431 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T --> 0){
            st = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(st.nextToken());
            int res = 0;

            ArrayList<Integer> arrs = new ArrayList<>();
            arrs.add(Integer.parseInt(st.nextToken()));

            for(int i = 1; i < 20 ; i++){
                int stud = Integer.parseInt(st.nextToken());
                int nowSize = arrs.size();
                for(int j = nowSize-1; j >= 0 ; j--){
                    if(arrs.get(j) < stud){
                        res += nowSize - 1 - j;
                        arrs.add(j+1,stud);
                        break;
                    }
                    if(j == 0){
                        res += nowSize;
                        arrs.add(0,stud);
                    }
                }
            }

            sb.append(idx+" "+res+"\n");

        }

        System.out.println(sb);

    }
}
