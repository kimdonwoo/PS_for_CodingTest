package two_point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7453 {
    static int[] AB, CD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N], B = new int[N], C = new int[N], D = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        AB = new int[N*N];
        CD = new int[N*N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx++] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);


        long ans = 0;
        int left = 0, right = N*N-1;

        while (left < N*N && right >= 0) {

            if (AB[left] + CD[right] < 0) {
                left++;
            } else if (AB[left] + CD[right] > 0) {
                right--;
            } else {
                long leftCount = 1, rightCount = 1;

                while (left + 1 < N*N && (AB[left] == AB[left+1])) {
                    leftCount++;
                    left++;
                }

                while (right > 0 && (CD[right] == CD[right-1])) {
                    rightCount++;
                    right--;
                }

                ans += leftCount * rightCount;

                left++;
                right--;
            }
        }

        System.out.println(ans);
    }

}


//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.StringTokenizer;
//
//public class BOJ_7453 {
//    static int res = 0,N,tempCnt;
//    static int[] C;
//    static int[] D;
//    static HashMap<Integer,Integer> cache = new HashMap<>();
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        N = Integer.parseInt(br.readLine());
//
//        int[] A = new int[N];
//        int[] B = new int[N];
//        C = new int[N];
//        D = new int[N];
//
//        StringTokenizer st;
//
//        for(int i = 0 ; i < N ; i++) {
//            st = new StringTokenizer(br.readLine());
//            A[i] = Integer.parseInt(st.nextToken());
//            B[i] = Integer.parseInt(st.nextToken());
//            C[i] = Integer.parseInt(st.nextToken());
//            D[i] = Integer.parseInt(st.nextToken());
//        }
//
//        Arrays.sort(A);
//        Arrays.sort(B);
//        Arrays.sort(C);
//        Arrays.sort(D);
//
//        for(int i = 0 ; i < N ; i++) {
//            for(int j = 0 ; j < N ; j++) {
//                if(cache.containsKey(A[i]+B[j])){
//                    res+=cache.get(A[i]+B[j]);
//                }else {
//                    tempCnt = 0;
//                    search(A[i] + B[j], 0, N - 1);
//                    cache.put(A[i] + B[j], tempCnt);
//                    res += tempCnt;
//                }
//            }
//        }
//
//        System.out.println(res);
//
//    }
//
//    // 16_000_000
//    //
//    private static void search(int temp,int left, int right) {
//
//        // 탈출조건
//        if(left > N-1 || right < 0){
//            return;
//        }
//
//        if(temp+C[left]+D[right] == 0){
//            int l = checkSame(true,left);
//            int r = checkSame(false,right);
//            tempCnt += l*r;
//            search(temp,left+l,right-r);
//        }else if(temp+C[left]+D[right] > 0){
//            int r = checkSame(false,right);
//            search(temp,left,right-r);
//        }else{
//            int l = checkSame(true,left);
//            search(temp,left+l,right);
//        }
//
//
//    }
//
//    private static int checkSame(boolean c, int idx) {
//        int cnt = 0;
//
//        if(c){
//            // C에서 체크
//            int i = idx;
//            while(i < N){
//                if(C[idx] == C[i]){
//                    cnt++;
//                    i++;
//                }else{
//                    break;
//                }
//            }
//
//        }else{
//            // D에서 체크
//            int i = idx;
//            while(i >= 0){
//                if(D[idx] == D[i]){
//                    cnt++;
//                    i--;
//                }else{
//                    break;
//                }
//            }
//        }
//
//        return cnt;
//    }
//}
