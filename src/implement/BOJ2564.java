package implement;

import java.util.Scanner;

public class BOJ2564 {

        public static void main(String[] args) throws Exception {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();

            int cnt = 0, police=0;

            int[] map = new int [k];

            // 직선에 위치 표시
            for (int i = 0; i < k+1; i++) {
                int dir = sc.nextInt();
                int loc = sc.nextInt();
                int tmp=0;

                switch (dir) {
                    case 1: // 북
                        tmp = loc;
                        break;
                    case 2: // 남
                        tmp = n + m + n - loc;
                        break;
                    case 3: // 서
                        tmp = n + m + n + m - loc;
                        break;
                    case 4: // 동
                        tmp = n + loc;
                        break;
                }
                if(i<k)
                    map[i]=tmp;
                else
                    police=tmp;
            }

            //
            for (int i = 0; i < k; i++) {
                int path1 = Math.abs(police-map[i]);
                int path2 = 2*n+2*m-path1;
                cnt += Math.min(path1, path2);
            }

            System.out.println(cnt);
        }

}
