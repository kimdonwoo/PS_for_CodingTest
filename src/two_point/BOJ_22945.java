package two_point;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_22945 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = n-1;
        int ans = 0;

        while( left <= right ){

            int min = Math.min(arr[left],arr[right]);
            ans = Math.max(ans, (right-left-1) * min);

            /*
                간격을 점점 줄인다
                ans의 변화가 있으려면 더 작은값 방향을 옮겨야함

             */

            if(arr[left] < arr[right]){
                left++;
            }else{
                right--;
            }

        }

        System.out.println(ans);
    }
}
