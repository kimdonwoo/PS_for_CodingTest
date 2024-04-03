package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    [처음에 내가 놓친 부분]
    1. 내가 구매한 고기보다 저렴한 고기는 덤으로 받을 수 있다
        = 최댓값만 구하는 게 아닌, 최댓값이 중복으로 여러개일수있다는 것을 고려해라
    2. 최댓값으로 여러개 사는것보다 그것보다 더 비싼것을 더 작게 사는게 이득일 수도 있다.

 */

public class BOJ_2258 {

    public static class Meat{
        int weight;
        int price;
        public Meat(int weight, int price){
            this.weight = weight;
            this.price = price;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Meat> meats = new PriorityQueue<>((o1,o2)->{
            if(o1.price == o2.price){
                return o2.weight-o1.weight;
            }
            return o1.price-o2.price;
        });
        int temp = 0;
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            temp += w;

            meats.add(new Meat(w,p));
        }

        if(M > temp){
            System.out.println(-1);
            return;
        }

        // 만약 M보다 크면 ?
        int wSum = 0;
        int maxPrice = 0;
        int maxCnt = 0;
        int res = Integer.MAX_VALUE;
        while(!meats.isEmpty()){
            Meat now = meats.poll();

            wSum+= now.weight;
            if(maxPrice < now.price){
                maxPrice = now.price;
                maxCnt = 1;
            }else if(maxPrice == now.price){
                maxCnt++;
            }

            if(wSum >= M){
                // 무게를 넘은 시점에
                res = Math.min(res,maxPrice * maxCnt);
            }
        }

        System.out.println(res);

    }
}
