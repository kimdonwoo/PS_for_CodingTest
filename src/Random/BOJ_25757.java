package Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_25757 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        String str = st.nextToken();
        int nums = 0;
        if(str.equals("Y")){
            nums = 1;
        }else if(str.equals("F")){
            nums = 2;
        }else{
            nums = 3;
        }

        HashMap<String,Boolean> hm = new HashMap<>();

        for(int i = 0 ; i < N ; i++){
            hm.put(br.readLine(), true);
        }

        System.out.println(hm.size()/nums);

    }
}
