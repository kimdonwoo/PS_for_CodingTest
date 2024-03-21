package two_point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class BOJ_20442 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        ArrayList<Integer> kArr = new ArrayList<>();
        ArrayList<Integer> rArr = new ArrayList<>();

        boolean kStart = false;

        if(str.substring(0,1).equals("K")) kStart = true;

        boolean flag = kStart;

        for(int i = 0 ; i < str.length() ; i++){
            String temp = str.substring(i,i+1);
            int num = 1;
            while(true){
                i++;
                if(i >= str.length()) break;
                if(temp.equals(str.substring(i,i+1))){
                    // 같으면
                    num++;
                }else{
                    // 다르면
                    i--;
                    break;
                }
            }
            if(flag){
                kArr.add(num);
            }else{
                rArr.add(num);
            }
            flag = !flag;
        }

        // 다음 rArr의 누접합 구하기
        int[] prefixRArr = new int[rArr.size()+1];
        for(int i = 0 ; i < rArr.size() ; i++){
            prefixRArr[i+1] = prefixRArr[i]+rArr.get(i);
        }

        int res = prefixRArr[rArr.size()];

        if(kArr.size() == 0){
            System.out.println(res);
            return;
        }

        int left = 0;
        int right = kArr.size()-1;
        int leftSum = kArr.get(left);
        int rightSum = kArr.get(right);

        while(left < right){

            int temp = Math.min(leftSum,rightSum)*2;
            if(kStart) {
                temp += prefixRArr[right] - prefixRArr[left];
            }else{
                temp += prefixRArr[right+1] - prefixRArr[left+1];
            }
            res = Math.max(res, temp);

            if(leftSum < rightSum){
                left++;
                leftSum+=kArr.get(left);
            }else if(leftSum > rightSum){
                right--;
                rightSum+=kArr.get(right);
            }else{
                if(left+1 == right) break;
                else{
                    if(kStart) {
                        if(rArr.get(left) < rArr.get(right-1)){
                            left++;
                            leftSum+=kArr.get(left);
                        }else{
                            right--;
                            rightSum+=kArr.get(right);
                        }

                    }else{
                        if(rArr.get(left+1) < rArr.get(right)){
                            left++;
                            leftSum+=kArr.get(left);
                        }else{
                            right--;
                            rightSum+=kArr.get(right);
                        }
                    }
                }
            }
        }


        System.out.println(res);

    }
}
