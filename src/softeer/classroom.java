package softeer;

import java.util.*;
import java.io.*;

public class classroom {
    public static void main(String args[]) throws IOException
    {
        /*
            강의실 1개에 최대한 많은 강의 배치
            시작시간과 종료시간은 겹쳐도 된다.

            N개의 강의 : 10^6
            강의 시간 길이 : 10^9

            항상 현재 고를 수 있는 것들 중 가장 빨리 끝나는 것을 선택 -> DP ?

            현재 고를 수 있는것을 어떻게 알지 ??

            1 3 / 2 4 / 3 5

            먼저 끝나는 순으로 정렬하고 하나씩 보면서
                현재 가능한지 check하고
                    불가능하면 다음꺼 확인
                    가능하면 현재 바꾸고 cnt 증가 시키고


            최대 강의 수를 출력
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] classes = new int[N][2];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            classes[i][0] = Integer.parseInt(st.nextToken());
            classes[i][1] = Integer.parseInt(st.nextToken())-1;
        }
        // 1~2 2~3 3~4

        Arrays.sort(classes,(o1, o2)->{
            return o1[1] - o2[1];
        });


        int now = 1;
        int cnt = 0;

        for(int i = 0 ; i < N ; i++){
            if(classes[i][0] >= now){
                now = classes[i][1]+1;
                cnt++;
            }
        }

        System.out.println(cnt);

    }
}