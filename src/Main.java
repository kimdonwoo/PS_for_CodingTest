import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        System.out.println(q.poll());

        System.out.println(" ----- ");

        Deque<Integer> deque = new LinkedList<>(); // deque의 add,remove는 그냥 queue
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3); // 1 2 3
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        //System.out.println(deque.removeLast());
        //System.out.println(deque.removeFirst());

        System.out.println(" ----- ");

        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());


    }
}
