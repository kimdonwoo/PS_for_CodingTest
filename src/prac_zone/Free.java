package prac_zone;

import java.util.ArrayList;

public class Free {
    static int[][] map = new int[5][5];
    public static void main(String[] args) throws Exception {

        int k = 0;
        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < 5 ; j++){
                map[i][j] = k;
                k++;
            }
        }

        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < 5 ; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        // 두번째 줄 오른쪽으로 3칸 shift


    }
}
