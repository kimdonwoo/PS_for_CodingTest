package combination;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1010 {
    static int[][] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new int[30][30];

        for(int i = 0 ; i < N ; i++){
            String[] temp = br.readLine().split(" ");

            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);

            System.out.println(comb(b,a));
        }
    }

    static int comb(int n, int r){
        // 이미 탐색했던 경우
        if (dp[n][r] > 0) return dp[n][r];
        // nC0 = nCn = 1
        else if( n==r || r==0) return dp[n][r] = 1;
        // n+1Cr+1 = nCr + nCr+1
        else return comb(n-1,r-1)+comb(n-1,r);
    }
}
