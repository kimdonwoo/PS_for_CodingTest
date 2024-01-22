package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_3079 {
    static int n;
    static long m, max;
    static int [] arr;
    static long result = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max,arr[i]);
        }
        Arrays.sort(arr);

        solve();

        System.out.println(result);
    }

    private static void solve(){
        long low = 0;
        long high = m * max;

        while(low<=high){
            long mid = (low+high)/2;
            long sum = 0;
            for(long index: arr){
                long count = mid/index;

                if(sum>=m){
                    break;
                }
                sum+=count;
            }
            if(sum>=m){
                high = mid-1;
                result = Math.min(mid,result);
            }
            else{
                low = mid+1;
            }
        }
    }
}

/*
    모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 구해라
    -> 나올수 있는 시간의 범위를 low,high에 저장
    -> mid = (low+high)/2로 mid 시간일 때 M명 이상 심사 가능한지 체크
        -> 만약 가능하면 high = mid-1;
        -> 불가능하면 low = mid+1;

    이분 탐색 원리


 */