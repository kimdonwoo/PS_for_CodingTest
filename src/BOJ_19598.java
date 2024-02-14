import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_19598 {
    static class Time implements Comparable<Time> {
        int time;
        boolean isStart;

        public Time(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Time o){
            return this.time-o.time;
        }
    }

    static int N;
    static PriorityQueue<Time> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Time(Integer.parseInt(st.nextToken()), true)); // 시작시간
            pq.add(new Time(Integer.parseInt(st.nextToken()), false)); // 종료시간
        }

        int cnt = 0; // 사용 회의실 개수
        int answer = 0; // 최대값

        while (!pq.isEmpty()) {
            Time t = pq.poll();

            if(t.isStart) {
                // 새로운 회의 시작
                cnt++;
                answer = Math.max(answer,cnt);
            }else{
                // 회의 하나 끝남
                cnt--;
            }
        }

        System.out.println(answer);
    }
}
