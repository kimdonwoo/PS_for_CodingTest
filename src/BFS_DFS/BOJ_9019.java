package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9019 {
    static public class Node{
        int val;
        String hist;

        public Node(int val, String hist){
            this.val = val;
            this.hist = hist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuffer sb = new StringBuffer();

        while(T --> 0){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int goal = Integer.parseInt(st.nextToken());

            Queue<Node> q = new LinkedList<>();
            q.add(new Node(start,""));
            HashMap<Integer,Boolean> hm = new HashMap<>();
            hm.put(start,true);

            while(!q.isEmpty()){

                Node now = q.poll();

                if(now.val == goal){
                    sb.append(now.hist+"\n");
                    break;
                }

                // D
                int D = (now.val*2) %10000;
                if(!hm.containsKey(D)){
                    hm.put(D,true);
                    q.add(new Node(D,now.hist+"D"));
                }
                // S
                int S = (now.val+9999) %10000;
                if(!hm.containsKey(S)){
                    hm.put(S,true);
                    q.add(new Node(S,now.hist+"S"));
                }
                // L
                int L = (now.val*10+(now.val*10)/10000)%10000;
                if(!hm.containsKey(L)){
                    hm.put(L,true);
                    q.add(new Node(L,now.hist+"L"));
                }
                // R
                int R =  (now.val/10)+(now.val%10)*1000;
                if(!hm.containsKey(R)){
                    hm.put(R,true);
                    q.add(new Node(R,now.hist+"R"));
                }
            }
        }

        System.out.println(sb);

    }
}
