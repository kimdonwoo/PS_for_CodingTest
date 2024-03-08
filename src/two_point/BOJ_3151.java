package two_point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3151 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[]arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long cnt = 0;

        for(int i=0; i<N; i++) {

            if(arr[i] > 0) break;

            int num = arr[i];

            int left = i+1;
            int right = N-1;

            while(left < right) {
                int sum = num + arr[left] + arr[right];

                if(sum == 0) {
                    int l = 1;
                    int r = 1;
                    if(arr[left] == arr[right]) {
                        int n = right-left+1;
                        cnt += Combination(n);
                        break;
                    }
                    while (arr[left] == arr[left + 1]) {
                        l++;
                        left++;
                    }
                    while (arr[right] == arr[right - 1]) {
                        r++;
                        right--;
                    }

                    cnt+= l * r;
                }

                if(sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static long Combination(int n) {
        return n * (n - 1) / 2;
    }
}

//public class BOJ_3151 {
//    static int nums[];
//    static int N;
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        N = Integer.parseInt(br.readLine());
//        nums = new int[N];
//
//        if(N <= 2){
//            System.out.println(0);
//            return;
//        }
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        for(int i = 0 ; i < N ; i++){
//            nums[i] = Integer.parseInt(st.nextToken());
//        }
//
//        Arrays.sort(nums);
//
//        long res = 0;
//
//        for(int i = 0 ; i < N-2 ; i++){
//            if(nums[i] > 0) break;
//            for(int j = i+1 ; j < N-1 ; j++){
//                res += twoPointer((-1)*(nums[i]+nums[j]),j+1);
//            }
//        }
//
//        System.out.println(res);
//
//    }
//
//    private static int twoPointer(int goal, int s) {
//        // nums에서
//        int cnt = 0;
//
//        int idx = Arrays.binarySearch(nums, s, N, goal);
//        if(idx >= s) {
//            for (int i = idx; i < N; i++) {
//                if (nums[i] == goal) cnt++;
//                else break;
//            }
//            for (int i = idx-1; i >= s; i--) {
//                if (nums[i] == goal) cnt++;
//                else break;
//            }
//        }
//
//        return cnt;
//    }
//}

