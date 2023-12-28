package prac_zone;

import java.util.ArrayList;

public class Free {
    static int[][] map = new int[5][5];
    public static void main(String[] args) throws Exception {


        ArrayList<int[]> temp = new ArrayList<>();
        temp.add(new int[]{1,1});
        temp.add(new int[]{2,2});
        temp.add(new int[]{3,3});
        temp.add(new int[]{4,4});
        temp.add(new int[]{5,5});



        for(int i = 0 ; i < temp.size() ; i++){
            temp.remove(0);
        }



    }
}
