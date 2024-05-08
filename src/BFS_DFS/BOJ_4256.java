package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_4256 {
    public static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T --> 0){
            int N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            int[] preorder = new int[N];
            for(int i = 0 ; i < N ; i++){
               preorder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            //int[] inorder = new int[N];
            HashMap<Integer,Integer> inorderhm = new HashMap<>();
            for(int i = 0 ; i < N ; i++){
                int temp = Integer.parseInt(st.nextToken());
                inorderhm.put(temp,i);
            }

            Node root = new Node(preorder[0]);
            for(int i = 1 ; i < N ; i++){
                append(root,preorder[i],inorderhm);
            }

            // 후위 순회하기
            postorder(root,sb);
            sb.append("\n");

        }

        System.out.println(sb);


    }

    private static void postorder(Node node,StringBuilder sb) {

        if(node.left != null){
            postorder(node.left,sb);
        }
        if(node.right != null){
            postorder(node.right,sb);
        }
        sb.append(node.val+" ");

    }

    private static void append(Node node, int now,HashMap<Integer,Integer> inorder) {

        //int a = search(node.val,inorder);
        int a = inorder.get(node.val);

        //int b = search(now,inorder);
        int b = inorder.get(now);

        if( a > b ){
            // left
            if(node.left == null){
                node.left = new Node(now);
            }else{
                append(node.left,now,inorder);
            }

        }else{
            // right
            if(node.right == null){
                node.right = new Node(now);
            }else{
                append(node.right,now,inorder);
            }
        }
    }

    private static int search(int val, int[] arr){
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] == val) return i;
        }

        return -1;
    }
}
