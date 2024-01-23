import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20444 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        boolean res = false;

        // N을 나눈거
        long low = 0;
        long high = N/2;

        while(low <= high){
            long mid = (low+high)/2;

            long temp = (mid+1)*(N-mid+1);

            if(temp == M){
                res = true;
                break;
            }else if(temp > M){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }

        if(res) System.out.println("YES");
        else System.out.println("NO");

    }
}

/*
    오래 걸린 이유

    주어진 N,K에 대해서

 */