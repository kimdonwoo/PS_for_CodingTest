package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_14889 {
    static int N;
    static int teamMember;
    static int[][] score;
    static boolean[] teamA;
    static int res = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        score = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        teamMember = N/2;
        teamA = new boolean[N];

        recursive(0,0);

        System.out.println(res);
    }

    // now는 지금 index, count는 현재 teamA에 몇명인지
    private static void recursive(int now, int count) {
        // 중단 지점
        if(count == teamMember){
            // 여기서 계산
            teamCount();
            return;
        }

        // recursive
        for(int i = now ; i < N ; i++){
            teamA[i] = true;
            recursive(i+1,count+1);
            teamA[i] = false;
        }

    }

    private static void teamCount() {
        int teamA_sum = 0;
        int teamB_sum = 0;

        for(int i = 0 ; i < N-1 ; i++){
            for(int j = i+1 ; j < N ; j++){
                // 둘다 teamA일때
                if(teamA[i] && teamA[j]){
                    teamA_sum+=score[i][j];
                    teamA_sum+=score[j][i];
                }else if(!teamA[i] && !teamA[j]){
                    teamB_sum+=score[i][j];
                    teamB_sum+=score[j][i];
                }

            }
        }

        res = Math.min(res,Math.abs(teamA_sum-teamB_sum));
        if(res == 0){
            System.out.println(0);
            System.exit(0);
        }
    }

}




/*
public class BOJ_14889 {

    static int N;
    static int[][] map;
    static boolean[] visit;
    static int Min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0, 0);
        System.out.println(Min);

    }

    // idx는 인덱스, count는 조합 개수(=재귀 깊이)
    static void combi(int idx, int count) { // 조합 과정
        // 팀 조합이 완성되면 차이 비교해서 최솟값구하기
        if(count == N / 2) {
            diff();
            return;
        }

        for(int i = idx; i < N; i++) {
            if(!visit[i]) {
                visit[i] = true;
                combi(i + 1, count + 1);
                visit[i] = false;
            }
        }
    }

    // 두 팀의 능력치 차이를 계산하는 함수
    static void diff() {
        int team_start = 0;
        int team_link = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // i 번째 사람과 j 번째 사람이 true라면 스타트팀으로 점수 플러스
                if (visit[i] == true && visit[j] == true) {
                    team_start += map[i][j];
                    team_start += map[j][i];
                }
                // i 번째 사람과 j 번째 사람이 false라면 링크팀으로 점수 플러스
                else if (visit[i] == false && visit[j] == false) {
                    team_link += map[i][j];
                    team_link += map[j][i];
                }
            }
        }
        // 두 팀의 점수 차이 (절댓값)
        int val = Math.abs(team_start - team_link);

        if (val == 0) {
                System.out.println(val);
                System.exit(0);
                }

                Min = Math.min(val, Min);

                }

                }


 */