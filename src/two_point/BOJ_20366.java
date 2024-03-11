package two_point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20366 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[n];

        for(int i=0;i<n;i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        long ans = Integer.MAX_VALUE;

        for(int i=0 ; i<n-1 ; i++){
            for(int j=i+1 ; j<n ; j++){
                // i랑 j를 선택한 상황
                long temp = arr[i]+arr[j];

                //투 포인터
                int left = 0;
                int right = n-1;

                if(left == i) left++;
                if(left == j) left++;
                if(right == j) right--;
                if(right == i) right--;

                while(left < right){

                    long snow = arr[left]+arr[right];
                    ans = Math.min(ans,Math.abs(temp-snow));

                    if(temp == snow){
                        System.out.println(0);
                        return;
                    }else if(temp > snow){
                        // snow가 더 작으면
                        // snow값을 증가시켜야함
                        left++;
                        if(left == i) left++;
                        if(left == j) left++;
                    }else{
                        right--;
                        if(right == j) right--;
                        if(right == i) right--;
                    }
                }
            }
        }


        System.out.println(ans);

    }
}
