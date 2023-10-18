import java.util.*;
import java.io.*;


public class Main {
    static int[][] posts;
    static HashMap<Integer,ArrayList<Integer>> tree;
    static HashMap<Integer,String> indexs;
    public static void main(String args[]){

        // 얘가 List<Post>임
        posts = new int[][]{{1, 1}, {6, 1}, {7,1}, {2,2}, {4,2}, {8,4}, {3,3} ,{5,3}};
        tree = new HashMap<>();
        indexs = new HashMap<>();

        // post[0] : 본인
        // post[1] : 부모
        for(int[] post : posts){
            if(post[0] != post[1]) {
                if (tree.containsKey(post[1])) {
                    tree.get(post[1]).add(post[0]);
                } else {
                    tree.put(post[1], new ArrayList<>(Arrays.asList(post[0])));
                }
            }
        }


        for(int k : tree.keySet()){
            System.out.print(k + " : ");
            for(int n : tree.get(k)){
                System.out.print(n + " ");
            }
            System.out.println();
        }

        int res = 1;
        for(int[] post : posts){
            if(post[0] == post[1]){
                DFS(res,post[0],"");
                res++;
            }
        }

        // 여기서 indexs 출력
        for(int k : indexs.keySet()){
            System.out.println(k + " : " + indexs.get(k));
        }

    }

    static void DFS(int res, int now, String index){
        indexs.put(now,index+res);
        if(tree.containsKey(now)) {
            for (int i = 0; i < tree.get(now).size(); i++) {
                DFS(i + 1, tree.get(now).get(i), index + res + "-");
            }
        }
    }
}
