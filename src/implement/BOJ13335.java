package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13335 {
    /*

        다리를 n개의 트럭이 지나감
        한번에 w대의 트럭만 가능
        다리의 길이는 w 단위 길이

            하나의 트럭들은 하나의 단위시간에 하나의 단위길이 만큼 이동
            트럭 무게의 합(완전히 올라가야 함)은 <= 다리 최대하중 L

            차갯수 : 4/ 다리길이 : 2/ L : 10
            [7 4 5 6]

            다리 길이 배열 하나랑
            트럭 큐
            다리 배열이랑 트럭 큐 둘다 빌 때까지 반복 -> 반복횟수가 시간
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 트럭 수
        int N = Integer.parseInt(st.nextToken());
        // 다리 길이
        int M = Integer.parseInt(st.nextToken());
        // 하중
        int L = Integer.parseInt(st.nextToken());

        // 트럭 큐랑 다리 배열 초기화
        Queue<Integer> q = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            q.add(Integer.parseInt(st.nextToken()));
        }
        int[] bridge = new int[M];
        int count = 0;

        // 반복
        while(true){ // 다리 배열이랑 트럭 큐 둘다 빌 떄 까지 반복

            // 시간 증가
            count ++;
            // 배열에 있는 트럭 한 칸 씩 이동 (100칸)
            // 다리 총 M칸, 0,1,2,...,M-1 => 이동 방향
            for(int i = M-1 ; i > 0 ; i--){
                bridge[i] = bridge[i-1];

            }
            bridge[0] = 0;

            // 현재 다리에 있는 트럭 무게 합 구하기 + 다음 트럭 무게 합쳐서 L이랑 비교
            int sum = 0;
            for(int a : bridge){ // 이 부분 계산량 줄일 수 있긴함
                sum+=a;
            }

            // L보다 작으면 큐에서 꺼내서 배열 넣기
            if(!q.isEmpty()){
                if(sum+q.peek() <= L){
                    bridge[0] = q.poll();
                }
            }else{
                if(sum == 0) break;
            }

        }

        System.out.println(count);


    }
}
