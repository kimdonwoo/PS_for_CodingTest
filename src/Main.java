import java.util.ArrayList;

public class Main{

    static ArrayList<Integer>[] arrs;
    static boolean[] visited;
    public static void main(String args[]) {
        int n = 10;
        arrs = new ArrayList[n];
        visited = new boolean[n];

        for(int i = 0 ; i < n ; i++){
            arrs[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < n ; i++){
            arrs[i].add(i*i);
            arrs[i].add(i*i*i);
        }

        for(int i = 0 ; i < n ; i++){
            System.out.print(i +" -> ");
            for(int j = 0 ; j < arrs[i].size(); j++){
                System.out.print( arrs[i].get(j) +" ");
            }
            System.out.println();
        }



    }
}

