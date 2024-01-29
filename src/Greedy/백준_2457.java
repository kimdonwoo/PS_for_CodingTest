package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 백준_2457 {
    public static class Node{
        int start;
        int end;
        Node(int start,int end){
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String args[]) throws IOException {

        /*
            5/8 ~ 6/13 이면 12일 지는 것
            (올해는 4, 6, 9, 11월은 30일까지 있고, 1, 3, 5, 7, 8, 10, 12월은 31일까지 있으며, 2월은 28일까지만 있다.)

            1. 3.1~ 11/30 매일 꽃이 한가지 이상
            2. 꽃들의 수 최소로
         */

        /*
            풀이
                지금 가능한것 중에 최대한 늦게 끝나는 것을 선택

                제일 첫 선택은 3/1 전에 시작중에 최대한 늦게 끝나는 것 선택
                그 이후는 선택한것중에 끝나는 것을 지금이라고 생각

                즉, 지금 필수있는 꽃중 제일 늦게 끝나는 것 선택

                제일 늦게 끝나는 것부터 앞에 두고 앞에서 부터 계속 비교
                now 갱신하면서 res +1 종료 조건은

         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int res=0;
        Node[] nodes = new Node[n];
        ArrayList<Node> set = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            st=new StringTokenizer(br.readLine());
            //nodes[i] = new Node(100*Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())*100+Integer.parseInt(st.nextToken()));
            set.add(new Node(100*Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())*100+Integer.parseInt(st.nextToken())));
        }

        Collections.sort(set,((o1, o2) -> {
            if(o1.end == o2.end) return o1.start-o2.start;
            return o2.end - o1.end;

        }));

        int now = 301;

        while(now < 1201){
            for(Node s : set){
                if(s.start <= now && now < s.end){
                    res++;
                    now = s.end;
                    break;
                }
                if(s.equals(set.get(set.size()-1))){
                    res = 0;
                    now = 1201;
                    break;
                }
                // Node를 다 돌았는데 마지막까지 if문에 안들어가면 0 출력
            }
        }

        System.out.println(res);


    }
}
