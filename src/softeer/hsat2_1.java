package softeer;

import java.util.*;
import java.io.*;

public class hsat2_1 {

    static int N, K;
    static HashMap<Integer, ArrayList<int[]>> hm;
    static int res = 2000*2000;
    public static void main(String args[]) throws IOException
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        hm = new HashMap<>();

        for(int i = 0 ; i < K ; i++){
            hm.put(i, new ArrayList<int[]>());
        }

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken())-1;

            hm.get(color).add(new int[] {x,y});
        }

        DFS(0,1000,-1000,1000,-1000); // 애매 ..

        System.out.println(res);
    }

    static void DFS(int color, int left, int right, int bottom , int top ){
        // 1. 정지 조건
        if(color == K){
            res = Math.min(res ,(right - left)*(top - bottom));
            return;
        }

        ArrayList<int[]> arr = hm.get(color);

        for(int i = 0 ; i < arr.size() ; i++){
            int[] temp = arr.get(i);
            int left_min = Math.min(left, temp[0]);
            int right_max = Math.max(right,temp[0]);
            int bottom_min = Math.min(bottom,temp[1]);
            int top_max =  Math.max(top,temp[1]);
            if(res > (right_max - left_min)*(top_max - bottom_min)){
                DFS(color+1,left_min,right_max,bottom_min,top_max);
            }

        }




    }
}