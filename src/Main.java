import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int n = sn.nextInt();
        List<Integer> a = new ArrayList<>();

        int []left = new int[n];

        for (int i = 0 ; i < n ; i++){
            left[i] = sn.nextInt();
        } // 2 1 1 0


        for (int i = n - 1 ; i >= 0 ; i--){
            a.add(left[i], i + 1);
        }

        for (int answer : a){
            System.out.print(answer + " ");
        }
    }
}
