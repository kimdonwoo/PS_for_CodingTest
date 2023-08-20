package implement;


import java.io.*;
import java.util.*;

public class BOJ2477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int val = Integer.parseInt(st.nextToken());

        int h_max = 0, w_max = 0; //각 가로 세로의 최대 길이 저장
        int hMaxIdx = -1, wMaxIdx = -1; //가로 세로의 최대 길이의 인덱스 저장

        int[] dir = new int[6]; //순서대로 방향 저장
        int[] leng = new int[6]; //변의 길이 저장

        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            dir[i] = Integer.parseInt(st.nextToken());
            leng[i] = Integer.parseInt(st.nextToken());

            if (dir[i] == 1 || dir[i] == 2) { // 가로 최댓값 + 인덱스 구하기
                if (h_max < leng[i]) {
                    h_max = leng[i];
                    hMaxIdx = i;
                }
            } else { // 세로 최댓값 + 인덱스 구하기
                if (w_max < leng[i]) {
                    w_max = leng[i];
                    wMaxIdx = i;
                }
            }
        }

        int maxSquare = w_max * h_max; //전체 사각형의 넓이

        //각 인덱스의 +3을 하면 안에 있는 사각형의 길이를 가지고 있는 인덱스임
        int minSquare = leng[(wMaxIdx + 3) % 6] * leng[(hMaxIdx + 3) % 6];

        /*
            아이디어 배우기
            배열을 순환하는 배열로 생각하기 !!! => % (배열의 길이)


         */

        System.out.println((maxSquare-minSquare)*val);

    }
}
