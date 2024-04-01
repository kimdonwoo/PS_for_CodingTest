package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2195 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] sArr = br.readLine().toCharArray();
        char[] pArr = br.readLine().toCharArray();

        // 현재 위치 중심으로
        int idx = 0;
        int answer = 0;

        while(idx < pArr.length){

            // 현재 idx 기준으로 뒤로 얼마나 같은지 체크
            // or 최장 중복 수열 찾기 ?
            int cnt = 0;
            answer++;
            for(int i = 0 ; i < sArr.length; i++){
                if(pArr[idx] == sArr[i]){
                    int temp = 0;

                    while(true){
                        temp++;

                        if(idx+temp >= pArr.length || i+temp >= sArr.length) break;
                        if(pArr[idx+temp] != sArr[i+temp]) break;
                    }

                    cnt = Math.max(cnt,temp);
                }
            }

            // 최장 길이만큼 더하기
            idx += cnt;
        }

        System.out.println(answer);

    }
}
