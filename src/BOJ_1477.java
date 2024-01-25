import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] place = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            place[i] = Integer.parseInt(st.nextToken());
        }
        place[N] = L;
        Arrays.sort(place);

        int low = 1;
        int high = L-2;

        while(low <= high){
            int mid = (low+high)/2;
           //-> 휴게소가 없는 구간의 최댓값이 mid일때 몇개의 휴게소를 설치해야하냐
           //-> 이것을 이분탐색을 통해 휴게소가 M개 필요한 값중 mid의 최솟값을 찾아라!
            int cnt = 0;
            int now = 0;

            int i = 0;
            while(now+mid < L){
                if(now+mid < place[i]){
                    // now+mid에 새거 설치
                    cnt++;
                    now+=mid;
                }else{
                    // 기존 place
                    now = place[i];
                    i++;
                }
            }

            // mid 증가 -> cnt 감소
            // cnt > M -> cnt 감소시켜야하니 mid ++
            // cnt < M -> cnt를 증가시켜야 되니 mid --
            // cnt == M -> 현재 mid보다 큰값은 볼 필요 x, mid--

            // 필요한 휴게소 갯수가 M개보다 작거나 같으면 mid를 -
            // 필요한 휴게소 갯수가 M개보다 크면 mid를 +
            if(cnt > M){
                low = mid+1;
            }else{
                high = mid-1;
            }

        }

        System.out.println(low);

        /*
            N개의 휴게소
            M개 더 세울려고함

            이미있는 곳 설치 x 고속도로 끝에도 x

            N : 50 / M : 100 / L : 1000
            휴게소 위치는 1 ~ L-1

           휴게소가 없는 구간의 길이의 최댓값의 최솟값
            ->  low = 1, high = L+1 or L
            -> 휴게소가 없는 구간의 최댓값이 mid일때 몇개의 휴게소를 설치해야하냐
            -> 이것을 이분탐색을 통해 휴게소가 M개 필요한 값중 mid의 최솟값을 찾아라!
         */
    }
}
