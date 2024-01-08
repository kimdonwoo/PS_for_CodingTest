package Brute_Force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16637 {
    static int N;
    static char[] arr;
    static int res = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new char[N];
        String str = br.readLine();
        for(int i = 0 ; i < N; i++){
            arr[i] = str.charAt(i);
        }

        brute(0,arr[0]-'0');

        System.out.println(res);
    }

    private static void brute(int idx, int sum){
        // 선택 완료
        if(idx == N-1){
            res = Math.max(res, sum);
            return;
        }

        // 선택
        if(arr[idx+1] == '+'){
            brute(idx+2,sum+(arr[idx+2]-'0'));
            if(idx+4 <= N){
                if(arr[idx+3] == '+'){
                    brute(idx+4,sum+((arr[idx+2]-'0') + (arr[idx+4]-'0')));
                }else if(arr[idx+3] == '-'){
                    brute(idx+4,sum+((arr[idx+2]-'0') - (arr[idx+4]-'0')));
                }else if(arr[idx+3] == '*'){
                    brute(idx+4,sum+((arr[idx+2]-'0') * (arr[idx+4]-'0')));
                }
            }
        }else if(arr[idx+1] == '-'){
            brute(idx+2,sum-(arr[idx+2]-'0'));
            if(idx+4 <= N){
                if(arr[idx+3] == '+'){
                    brute(idx+4,sum-((arr[idx+2]-'0') + (arr[idx+4]-'0')));
                }else if(arr[idx+3] == '-'){
                    brute(idx+4,sum-((arr[idx+2]-'0') - (arr[idx+4]-'0')));
                }else if(arr[idx+3] == '*'){
                    brute(idx+4,sum-((arr[idx+2]-'0') * (arr[idx+4]-'0')));
                }
            }
        }else if(arr[idx+1] == '*'){
            brute(idx+2,sum*(arr[idx+2]-'0'));
            if(idx+4 <= N){
                if(arr[idx+3] == '+'){
                    brute(idx+4,sum*((arr[idx+2]-'0') + (arr[idx+4]-'0')));
                }else if(arr[idx+3] == '-'){
                    brute(idx+4,sum*((arr[idx+2]-'0') - (arr[idx+4]-'0')));
                }else if(arr[idx+3] == '*'){
                    brute(idx+4,sum*((arr[idx+2]-'0') * (arr[idx+4]-'0')));
                }
            }
        }
    }
}

/*
    뒤에꺼 더해서 ㅏ

 */