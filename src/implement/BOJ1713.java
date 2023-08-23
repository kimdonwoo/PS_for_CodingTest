package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Student implements Comparable<Student>{
    int num;
    int total;
    int time;
    public Student(int num, int total, int time) {
        super();
        this.num = num;
        this.total = total;
        this.time = time;
    }
    @Override
    public int compareTo(Student o) {
        if(this.total==o.total) {
            return this.time-o.time;
        }else if(this.total<o.total)return -1;
        else return 1;

    }


}

public class BOJ1713 {
    static int n,m;
    static int[] students;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        students=new int[101];//학생 번호 100까지
        ArrayList<Student> list=new ArrayList<>();

        for (int i = 0; i < m; i++) {//1000번 이하
            int student=sc.nextInt();//추천학생 번호

            if(students[student]>0) {//있다
                students[student]+=1;
                for (int j = 0; j <list.size(); j++) {
                    if(list.get(j).num==student) {
                        list.get(j).total+=1;
                        break;
                    }
                }
            }else {//액자에 새로 추가
                if(list.size()>=n) {
                    Collections.sort(list);
                    int num=list.get(0).num;
                    students[num]=0;
                    list.remove(0);
                }
                list.add(new Student(student,1,i));
                students[student]=1;
            }

        }

        for (int i = 0; i <101; i++) {
            if(students[i]!=0) {
                System.out.print(i+" ");
            }
        }


    }
}
//public class BOJ1713 {
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//
//        st = new StringTokenizer(br.readLine());
//        int M = Integer.parseInt(st.nextToken());
//
//
//        HashMap<Integer,Boolean> hm = new HashMap<>();
//        st = new StringTokenizer(br.readLine());
//
//        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->{
//            if(o1.count == o2.count) return o1.order-o2.order;
//            return o1.count-o2.count;
//        });
//
//
//        for(int i = 0 ; i < M ; i++){
//            int num = Integer.parseInt(st.nextToken());
//
//            if(hm.containsKey(num)){ // 이미 큐 안에 있으면
//                Iterator<Node> iterator = pq.iterator();
//                while(iterator.hasNext()){
//                    Node next = iterator.next();
//                    if(next.key == num) {
//                        next.count++;
//                        break;
//                    }
//                }
//            }else if(pq.size() < N){ // 없는데 큐가 아직 안찼으면
//                pq.add(new Node(num,1,i));
//                hm.put(num,true);
//            }else{ // 큐에 없는데 큐가 꽉찬상황 -> 이때 pq 사용
//                Node poll = pq.poll();
//                hm.remove(poll.key);
//                pq.add(new Node(num,1,i));
//                hm.put(num,true);
//            }
//        }
//
//        int l = pq.size();
//
//        int[] res = new int[l];
//        for( int i = 0 ; i < l ;i++){
//            Node n = pq.poll();
//            res[i] = n.key;
//        }
//
//        Arrays.sort(res);
//
//        for(int i : res){
//            System.out.print(i+" ");
//        }
//
//
//    }
//
//}
//
//class Node{
//    // key, 횟수, 순서 -> 횟수로 정렬 횟수
//    // 똑같으면 순서 비교
//    int key;
//    int count;
//    int order;
//
//    public Node(int key, int count, int order) {
//        this.key = key;
//        this.count = count;
//        this.order = order;
//    }
//}