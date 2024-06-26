package two_point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1208 {
    static int N, S;
    static long cnt;
    static ArrayList<Integer> leftList = new ArrayList<>();
    static ArrayList<Integer> rightList = new ArrayList<>();
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 입력받은 배열을 두 부분으로 나눠 각 부분에서 모든 부분 수열의 합 case들을 list에 저장
        makeSum(0, 0, N/2, leftList);
        makeSum(0, N/2, N, rightList);

        // 모든 부분 수열의 합 case가 담긴 list를 오름차순으로 정렬
        Collections.sort(leftList);
        Collections.sort(rightList);


        cnt = 0;
        calcC();

        // 합이 0인 수를 찾는 경우, 부분 수열의 합을 찾을 때 하나도 선택하지 않은 경우가 포함되기 때문에 한 번 빼주어야 함
        if(S == 0) {
            System.out.println(cnt - 1);
        }else {
            System.out.println(cnt);
        }

    }

    // 투포인터
    private static void calcC() {
        int pointerL = 0;
        int pointerR = rightList.size()-1;

        while(true) {

            if(pointerL == leftList.size() || pointerR < 0) {
                break;
            }

            int lv = leftList.get(pointerL);
            int rv = rightList.get(pointerR);

            // 합이 목적 값과 같으면 합을 이루고 있는 각 수가 list 내에 몇개 있는지 센다.
            if(lv + rv == S) {
                long lc = 0;
                while(pointerL < leftList.size() && leftList.get(pointerL) == lv) {
                    lc++;
                    pointerL++;
                }

                long rc = 0;
                while(0 <= pointerR && rightList.get(pointerR) == rv) {
                    rc++;
                    pointerR--;
                }
                cnt += lc * rc;
            }else if(lv + rv > S) {
                pointerR--;
            }else if(lv + rv < S) {
                pointerL++;
            }
        }
    }

    // 즉, list에는 2^20의 결과가 들어감
    private static void makeSum(int sum, int start, int end, ArrayList<Integer> list) {
        if(start == end) {
            list.add(sum);
            return;
        }

        // 선택 유무에 따른 결과
        makeSum(sum, start+1, end, list);
        makeSum(sum+arr[start], start+1, end, list);
    }

}