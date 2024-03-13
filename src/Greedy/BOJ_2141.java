package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class BOJ_2141 {
    //마을 관련 클래스
    static class House implements Comparable<House>{
        long pos, val;
        public House(long pos, long val){
            this.pos = pos;
            this.val = val;
        }
        //마을 위치 기준 오름차순 정렬 기준
        @Override
        public int compareTo(House o) {
            return (int) (this.pos - o.pos);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        //마을 정보 저장 리스트
        List<House> houseList = new ArrayList<>();
        long sum = 0;

        //마을 정보들 저장
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");

            long pos  = Long.parseLong(st.nextToken()); // 위치
            long val = Long.parseLong(st.nextToken()); // 사람 수
            houseList.add(new House(pos, val));
            sum += val;	//총 인원 구하기
        }

        Collections.sort(houseList);	//마을 위치 기준 오름차순 정렬

        long result = 0;

        //가장 먼저 중간값보다 크거나 같은 마을 탐색
        for(int i=0;i<N;i++){
            result += houseList.get(i).val;
            if((sum + 1) / 2 <= result){	//(sum+1)/2 : 중간값
                System.out.println(houseList.get(i).pos);
                break;
            }
        }
    }
}


//public class BOJ_2141 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//
//        StringTokenizer st;
//
//        BigInteger sum = new BigInteger("0");
//        BigInteger allPersonCnt = new BigInteger("0");
//        for(int i = 0 ; i < N ; i++){
//            st = new StringTokenizer(br.readLine());
//
//            long loc = Long.parseLong(st.nextToken());
//            long personCnt = Long.parseLong(st.nextToken());
//
//            sum = sum.add(BigInteger.valueOf(loc*personCnt));
//            allPersonCnt = allPersonCnt.add(BigInteger.valueOf(personCnt));
//        }
//
//        if(allPersonCnt.compareTo(BigInteger.valueOf(0)) == 0){
//            System.out.println(-1_000_000_000);
//            return;
//        }
//
//        if(sum.mod(allPersonCnt).compareTo(BigInteger.valueOf(0)) == 0){
//            System.out.println(sum.divide(allPersonCnt));
//        }else{
//            if(sum.mod(allPersonCnt).multiply(BigInteger.valueOf(2)).compareTo(allPersonCnt) <= 0 ){
//                System.out.println(sum.divide(allPersonCnt));
//            }else{
//                System.out.println(sum.divide(allPersonCnt).add(BigInteger.valueOf(1)));
//            }
//        }
//    }
//}
