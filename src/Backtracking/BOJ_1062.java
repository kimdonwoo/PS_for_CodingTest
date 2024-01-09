package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1062 {
    static int n;
    static int k;
    static boolean[] visited;
    static String[] word;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        visited = new boolean[26];
        word = new String[n];

        if (k < 5) {
            System.out.println(0);
            return;
        } else if (k == 26) {
            System.out.println(n);
            return;
        }

        /* 무조건 배워야하는 단어 */
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        for (int i = 0; i < n; i++) {
            String str = br.readLine().replaceAll("anta|tica", "");

            word[i] = str;
        }

        check(0, 0);
        System.out.println(answer);
    }

    static void check(int alpha, int count) {
        if (count == k - 5) {
            int temp = 0;
            for (int i = 0; i < n; i++) {
                boolean flag = true;

                for (int j = 0; j < word[i].length(); j++) {
                    /* 배우지않은 알파벳이 있는 경우 */
                    if (!visited[word[i].charAt(j) - 'a']) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    temp++;
                }
            }
            answer = Math.max(temp, answer);
            return;
        }

        for (int i = alpha; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                check(i, count + 1);
                visited[i] = false;
            }
        }
    }
}


//public class BOJ_1062 {
//    static int N,K;
//    static HashMap<Character,Boolean>[] hm;
//    static int res = 0;
//    static char[] select;
//    //static HashMap<Character,Boolean> select2 = new HashMap<>();
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        K = Integer.parseInt(st.nextToken());
//
//        if(K == 0){
//            System.out.println(res);
//            return;
//        }
//
//        hm = new HashMap[N];
//        for(int i = 0 ; i < N ; i++){
//            hm[i] = new HashMap<>();
//        }
//        select = new char[K];
//
//        int min_length = Integer.MAX_VALUE;
//        for(int i = 0 ; i < N ; i++){
//            String str = br.readLine();
//            for(int j = 0 ; j < str.length() ; j++){
//                if(!hm[i].containsKey(str.charAt(j))){
//                    hm[i].put(str.charAt(j),true);
//                }
//            }
//            min_length = Math.min(min_length,hm[i].size());
//        }
//
//        if(K < 5 || K < min_length){
//            System.out.println(res);
//            return;
//        }
//
//        select[0] = 'a';
//        select[1] = 'n';
//        select[2] = 't';
//        select[3] = 'i';
//        select[4] = 'c';
//
//
//
//        backtracking(0,5);
//
//        System.out.println(res);
//
//    }
//
//    private static void backtracking(int idx , int count){
//        // 선택 완료
//        if(count == K){
//
//            int temp = 0;
//            boolean check = false;
//            boolean suc;
//            for(int i = 0 ; i < N ; i++){
//                if(K < hm[i].size()) continue;
//                // hm[i]의 key들이 select에 있는 애들이면 temp++
//                suc = false;
//
//                for(char k : hm[i].keySet()){
//                    check = false;
//                    for(int j = 0 ; j < K; j++){
//                        // 현재 단어에서 key가 select에 존재하면 check하기
//                        // 하나라도 포함안되어 있으면 다음 i로
//                        if(select[j] == k){
//                            check = true;
//                            break;
//                        }
//                    }
//                    if(!check){
//                        suc = true;
//                        break;
//                    }
//                }
//                if(!suc) temp++;
//            }
//
//            res = Math.max(res,temp);
//
//            return;
//        }
//
//        // 선택
//        for(int i = idx ; i < 26 ; i++){
//            char temp = (char)('a'+i);
//            if(temp == 'a' || temp == 'n' || temp == 't' || temp == 'i' || temp == 'c') continue;
//            select[count] = (char)('a'+i);
//            backtracking(i+1,count+1);
//        }
//    }
//}

