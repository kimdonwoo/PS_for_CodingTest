package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20055 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i, j, d,t,cnt;
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] map = new int[N * 2];
        int[] rob = new int[N * 2];
        for (i = 0; i < N * 2; i++) {
            map[i] = sc.nextInt();
        }

        for(d=1;;d++) {
            cnt=0;
            t=map[N*2-1];
            for(i=N*2-1;i>0;i--) {
                map[i]=map[i-1];
                rob[i]=rob[i-1];
            }
            rob[0]=0;
            map[0]=t;

            rob[N-1]=0;

            for(i=N-2;i>=0;i--) {
                if(rob[i]==1 && map[i+1]>0 && rob[i+1]==0) {
                    rob[i+1]=1;
                    rob[i]=0;
                    map[i+1]--;
                }
            }
            if(map[0]>0) {
                map[0]--;
                rob[0]=1;
            }
            for(i=0;i<N*2;i++) {
                if(map[i]==0) cnt++;
            }
            if(cnt>=M) break;
        }
        System.out.println(d);
    }
}

//public class BOJ20055 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//
//        // arr 초기화 과정
//        int[] arr = new int[2*N];
//        boolean[] robots = new boolean[N];
//
//        st = new StringTokenizer(br.readLine());
//
//        for(int i = 0 ; i < 2*N ; i++){
//            arr[i] = Integer.parseInt(st.nextToken());
//        }
//
//
//        int start_idx = 0;
//        int count = 0;
//        // N에서 내린다
//
//        while(true){
//
//            count++;
//            // 1. 벨트+로봇 한칸 회전
//            start_idx--;
//            if(start_idx < 0) start_idx+=(2*N);
//            //start_idx = (start_idx-1)%N;
//            for(int i = N-1 ; i >= 1 ; i--){
//                robots[i] = robots[i-1];
//            }
//            robots[0] = false;
//            robots[N-1] = false;
//
//            // 2. 이동할 수 있는 로봇들 이동! (끝 로봇은?)
//            // i-1이 i로 갈수 있는지 체크
//            for(int i = N-1 ; i >= 1;i--){
//                // i가 뒤에서 부터임
//                if(!robots[i] && arr[(start_idx+i+1)%(2*N)] > 0 && robots[i-1]){ // i에 로봇이 없고 i의 내구도
//                    arr[(start_idx+i+1)%(2*N)]--;
//                    robots[i] = robots[i-1];
//                }
//            }
//            robots[N-1] = false;
//
//            // 3. 올리는 위치 올릴 수 있으면 올리기
//            if(!robots[0] && arr[start_idx] > 0 ){
//                arr[start_idx]--;
//                robots[0] = true;
//            }
//
//            // 4. 내구도가 0인 칸의 갯수 count하고 k개 이상이면 종료
//            int num = 0;
//            for(int i = 0 ; i < 2*N ; i++){
//                if(arr[i] == 0) num++;
//            }
//            if(num >= K) break;
//        }
//
//        System.out.println(count);
//    }
//}
