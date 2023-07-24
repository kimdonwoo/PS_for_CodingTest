import java.util.*;


public class Main{
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String str = sc.next();
        char[] chars = str.toCharArray();
        int sum = 0;

        for(char a : chars){
            sum += (a-'0');
        }

        System.out.println(sum);


    }
}