package two_point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2632 {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int goal = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int[] aArr = new int[A];
        int B = Integer.parseInt(st.nextToken());
        int[] bArr = new int[B];

        ArrayList<Integer> aValue = new ArrayList<>();
        ArrayList<Integer> bValue = new ArrayList<>();

        int aSum = 0;
        for(int i = 0 ; i < A ; i++){
            aArr[i] = Integer.parseInt(br.readLine());
            aSum+=aArr[i];
            aValue.add(aArr[i]);
        }
        int bSum = 0;
        for(int i = 0 ; i < B ; i++){
            bArr[i] = Integer.parseInt(br.readLine());
            bSum+=bArr[i];
            bValue.add(bArr[i]);
        }

        for(int i = 1 ; i < A-1 ; i++){
           for(int j = 0 ; j < A ; j++) {
               aValue.add(aArr[j] + aValue.get((i - 1) * A + ((j + 1) % A)));
           }
        }

        for(int i = 1 ; i < B-1 ; i++){
            for(int j = 0 ; j < B ; j++) {
                bValue.add(bArr[j] + bValue.get((i - 1) * B + ((j + 1) % B)));
            }
        }
        aValue.add(0);
        aValue.add(aSum);
        bValue.add(0);
        bValue.add(bSum);

        Collections.sort(aValue);
        Collections.sort(bValue);

        int res = 0;
        int left = 0;
        int right = bValue.size()-1;

        while(left < aValue.size() && right >= 0){
            int now = aValue.get(left) + bValue.get(right);
            if(now == goal){
                // 여기서 중복값 다 구하기
                int leftTempCnt = 1;
                while(left+1 < aValue.size()){
                    if(aValue.get(left).equals(aValue.get(left+1))){
                        left++;
                        leftTempCnt++;
                    }else{
                        break;
                    }
                }

                int rightTempCnt = 1;
                while(right-1 >= 0){
                    if(bValue.get(right).equals(bValue.get(right-1))){
                        right--;
                        rightTempCnt++;
                    }else{
                        break;
                    }
                }
                res+=(leftTempCnt*rightTempCnt);
                right--;
                left++;
            }else if(now < goal){
                left++;
            }else{
                right--;
            }
        }

        System.out.println(res);

    }
}
