package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_20210 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<ArrayList<String>> arrs = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N ; i ++){
            arrs.add(new ArrayList<String>());

            ArrayList<String> temp = arrs.get(i);
            String str = br.readLine();

            // str를 분해해서 temp에 add하기
            for(int j = 0 ; j < str.length() ; j++){
                char c = str.charAt(j);
                if('A' <=  c && c <= 'z'){
                    temp.add(str.substring(j,j+1));
                }else{
                    // 숫자면
                    int k = j;
                    while(true){
                        k++;
                        if(str.length() <= k) break;
                        if('A' <=  str.charAt(k) && str.charAt(k) <= 'z') break;
                    }
                    temp.add(str.substring(j,k));
                    j = k-1;
                }
            }
        }

        // o1이 ArryList<String> 이거든
        Collections.sort(arrs,(o1,o2)->{

            // 먼저 o1, o2 List 사이즈 비교해서 짧은 걸 기준으로 돌기
            int len = Math.min(o1.size(),o2.size());

            // 돌면서 o1.get(i)랑 o2.get(i) 비교
            for(int i = 0 ; i < len; i++){
                String A = o1.get(i);
                String B = o2.get(i);

                // 만약 equal이면 continue;
                if(A.equals(B)) continue;

                // 다르면
                if(('A' <=  A.charAt(0) && A.charAt(0) <= 'z') && ('A' <=  B.charAt(0) && B.charAt(0) <= 'z')){
                    // 둘다 영어일 때 -> AaBb
                    // AaBb
                    if(A.toLowerCase().equals(B.toLowerCase())){
                      return A.charAt(0) - B.charAt(0);
                    }
                    return A.toLowerCase().charAt(0) - B.toLowerCase().charAt(0);

                }else if('A' <=  A.charAt(0) && A.charAt(0) <= 'z') {
                    // A만 영어 일때 B부터
                    return 1;

                }else if('A' <=  B.charAt(0) && B.charAt(0) <= 'z'){
                    // B만 영어 일때 A부터
                    return -1;
                }else{
                    int strLen = Math.max(A.length(),B.length());
                    String tempStr;
                    StringBuilder sb = new StringBuilder();
                    int ans;
                    if(A.length() < B.length()){
                        for(int k = 0 ; k < (B.length()-A.length()); k++) sb.append("0");
                        sb.append(A);
                        tempStr = sb.toString();
                        ans = check(tempStr,B);
                    }else if(A.length() > B.length()){
                        for(int k = 0 ; k < (A.length()-B.length()); k++) sb.append("0");
                        sb.append(B);
                        tempStr = sb.toString();
                        ans = check(A,tempStr);
                    }else{
                        ans = check(A,B);
                    }

                    if(ans != 0){
                        return ans;
                    }
                    return A.length()-B.length();
                }
            }
            // 여기서 길이짧은 ArrayList size 작은거 부터 출력
            return o1.size()-o2.size();
        });

        StringBuilder sb = new StringBuilder();

        for(ArrayList<String> arr: arrs){
            for(String s : arr){
                sb.append(s);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int check(String A, String B) {
        for(int i = 0 ; i < A.length() ; i++){
            if(A.charAt(i) == '0' && B.charAt(i) == '0') continue;
            if(A.charAt(i) == B.charAt(i)) continue;
            return A.charAt(i)-B.charAt(i);
        }
        return 0;
    }
}
