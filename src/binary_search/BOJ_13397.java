package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13397 {

    static int n,m, MAX = 987654321;;
    static int []arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        arr = new int[n];

        int right = Integer.MIN_VALUE;
        String[] s1 = br.readLine().split(" ");
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(s1[i]);
            right = Math.max(arr[i],right);
        }

        int left= 0;

        while(left<right){
            int mid = (left+right)/2;
            if(solve(mid)<=m){
                right=mid;
            }else{
                left=mid+1;
            }
        }

        System.out.println(right);
    }

    // 구간의 점수의 최댓값이 mid일때 M은 몇인지
    private static int solve(int mid){
        int count = 1;
        int min = MAX;
        int max = -MAX;
        // i번째까지의 최대 최소의 차이가 mid 보다 크면
        // count 값증가하고 max,min 초기화하고 그 이전 i부터 다시 시작하기
        for(int i=0; i<n; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            if(max - min > mid) {
                count++;
                max = -MAX; min = MAX;
                i--;
            }
        }
        return count;
    }
}

/*
    구간의 점수의 최댓값의 최솟값
    -> left~right (mid)에 값을 가능한 모든 구간의 점수의 최댓값으로
    -> 즉, left가 0이고 right이 max값일때
    -> mid 값 조정해가면서 만약 그때 필요한 M개의 갯수가 조건에 맞는지에 따라 mid 값 조정


 */