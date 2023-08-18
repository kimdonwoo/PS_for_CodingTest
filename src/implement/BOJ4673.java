package implement;

import java.io.IOException;
import java.util.HashMap;

public class BOJ4673 {

    public static HashMap<Integer,Boolean> hm;

    public static void main(String[] args){

        // 1. hashMap 만들어서 key값을 index로 하고 value에 boolean 값 넣기
        hm = new HashMap<>();

        for(int i = 1 ; i < 10001 ; i++){
            hm.put(i,true);
        }

        // 2. 만번 돌면서 본인을 생성자로 가진 숫자 지우기
        for(int i = 1 ; i < 10001 ; i++){
            self_number(i);
        }

        // 3. true인 값들 출력
        for(int i = 1 ; i < 10001 ; i++){
            if(hm.get(i)) System.out.println(i);
        }


    }

    static void self_number(int num){
        int sum = num;

        while(num != 0){
            sum += num % 10;
            num /= 10;
        }

        if(sum < 10001){
            hm.put(sum,false);
            self_number(sum);
        }

    }
}
