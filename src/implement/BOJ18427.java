package implement;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ18427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));

        String[] s = br.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int h = Integer.parseInt(s[2]);

        int [][] dp = new int[n+1][1001];

        List<Integer>[] list = new ArrayList[n+1];
;;
        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=0; i<=n; i++){
            dp[i][0]=1;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=h; j++){

                for(Integer integer: list[i]){
                    if(j-integer >= 0){
                        dp[i][j] += dp[i-1][j-integer];
                        dp[i][j] %=10007;
                    }
                }
                dp[i][j] += dp[i-1][j];
                dp[i][j] %=10007;
            }
        }
        System.out.println(dp[n][h]);
    }
}


    //    static int N, M, H;
//    static ArrayList<ArrayList<Integer>> stu = new ArrayList<>();
//    static int[] dp;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        H = Integer.parseInt(st.nextToken());
//
//        // 1. N명의 학생
//        for(int i = 0 ; i < N ; i++){
//            stu.add(new ArrayList<>());
//        }
//
//        dp = new int[H+1];
//        dp[0] = 1;
//
//        for(int i = 0 ; i < N ; i++){
//            String[] strs = br.readLine().split(" ");
//
//            if(i == 0){
//                for (String s : strs) {
//                    int num = Integer.parseInt(s);
//                    dp[num] ++;
//                }
//            }else {
//                for (String s : strs) {
//                    int num = Integer.parseInt(s);
//                    stu.get(i).add(Integer.parseInt(s));
//                }
//            }
//        }
//
//
//        for(ArrayList s : stu){
//            // dp 돌면서 0아닌 값있으면 거기다 더하기
//            int[] tempArr = new int[H+1];
//            for(int i = 0 ; i < H ; i++){
//                if(dp[i] != 0){  // 0아닌 값만나면
//
//                    for(int j = 0 ; j < s.size() ; j++){
//                        int temp = (Integer)s.get(j) + i;
//                        if(temp <= H){
//                            tempArr[temp] += dp[i];
//                            //dp[temp] += dp[i];
//                        }
//                    }
//
//                }
//            }
//            for(int i = 0 ; i < H+1 ; i++){
//                dp[i]+=tempArr[i];
//            }
//
//        }
//
//        System.out.println(dp[H] % 10007);
//
//    }
//}

// 역시나 시간 초과 ...
/*
    0 2 3 5
    3 5
    1 2 3

    dp
    0 1 2 3 4 5
    1 0 1 2 0 3

    0 1 0 1 2 0
    0 0 1 0 1 2
    0 0 0 1 0 1






 */


/*
public class BOJ18427 {
    static int N, M, H;
    static int res = 0;
    static ArrayList<ArrayList<Integer>> stu = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 1. N명의 학생
        for(int i = 0 ; i < N ; i++){
            stu.add(new ArrayList<>());
        }

        for(int i = 0 ; i < N ; i++){
            String[] strs = br.readLine().split(" ");
            stu.get(i).add(0);
            for(String s: strs) {
                int num = Integer.parseInt(s);
                if(num <= H) stu.get(i).add(Integer.parseInt(s));
            }
        }

        DFS(0,0);

        System.out.println(res % 10007);


    }

    static void DFS(int nowStudent , int nowH){
        // 1. 마지막 학생까지 다 포함시켰을 때 H값이면 res++
        if(nowStudent == N){
            if(nowH == H) res++;
            return;
        }

        // 2. 현재 높이가 H보다 크면 뒤로가기
        if(nowH > H){
            return;
        }

        // 3. 현재 학생의 stu 값중에 하나 골라서 다음 DFS 들어가기
        for(int val : stu.get(nowStudent)){
            DFS(nowStudent+1, nowH + val);
        }
    }
}

 */