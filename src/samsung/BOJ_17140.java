package samsung;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_17140 {
    static int[][] map = new int[100][100];
    static int[] end = {2,2};
    static HashMap<Integer,Integer> temp = new HashMap<>();
    static ArrayList<int[]> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;
        int k = Integer.parseInt(st.nextToken());


        for(int i = 0 ; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while(true){

            // (r,c) 체크
            if(map[r][c] == k || time > 100){
                break;
            }

            // R 연산
            if(end[0] >= end[1]){
                Roperation();
            }else{ // C 연산

                Coperation();
            }

            // 시간 증가
            time++;

        }

        if(time > 100){
            System.out.println(-1);
        }else{
            System.out.println(time);
        }

    }

    private static void Roperation() {

        int len = 0;

        for(int i = 0 ; i <= end[0] ; i++){
            temp.clear();
            arr.clear();

            for(int j = 0 ; j <= end[1] ; j++){
                if(map[i][j] != 0){
                    temp.put(map[i][j],temp.getOrDefault(map[i][j],0)+1);
                }
            }
            // 여기서 temp 다 돌면서 ArrayList에 넣고 Collections.sort하기
            for(int k : temp.keySet()){
                arr.add(new int[] {k,temp.get(k)});
            }

            Collections.sort(arr,(o1,o2)->{
                if(o1[1] == o2[1]) return o1[0]-o2[0];
                // 같으면 [0] 작은것 부터
                return o1[1] - o2[1];
                // 여기서 [1]값이 작은것부터
            });

            // 그리고 하나씩 넣으면서 넣기 하고 end[1] 수정
            for(int j = 0 ; j < 100 ; j++){
                map[i][j] = 0;
            }

            for(int j = 0 ; j < arr.size() && j < 50 ; j++){
                map[i][2*j] = arr.get(j)[0];
                map[i][2*j+1] = arr.get(j)[1];
            }

            len = Math.max(len,arr.size()*2 <= 100 ? arr.size()*2:100);
        }
        end[1] = len-1;

    }

    private static void Coperation() {

        int len = 0;

        for(int i = 0 ; i <= end[1] ; i++){
            temp.clear();
            arr.clear();

            for(int j = 0 ; j <= end[0] ; j++){
                if(map[j][i] != 0){
                    temp.put(map[j][i],temp.getOrDefault(map[j][i],0)+1);
                }
            }
            // 여기서 temp 다 돌면서 ArrayList에 넣고 Collections.sort하기
            for(int k : temp.keySet()){
                arr.add(new int[] {k,temp.get(k)});
            }

            Collections.sort(arr,(o1,o2)->{
                if(o1[1] == o2[1]) return o1[0]-o2[0];
                // 같으면 [0] 작은것 부터
                return o1[1] - o2[1];
                // 여기서 [1]값이 작은것부터
            });

            for(int j = 0 ; j < 100 ; j++){
                map[j][i] = 0;
            }

            // 그리고 하나씩 넣으면서 넣기 하고 end[1] 수정
            for(int j = 0 ; j < arr.size() && j < 50 ; j++){
                map[2*j][i] = arr.get(j)[0];
                map[2*j+1][i] = arr.get(j)[1];
            }

            len = Math.max(len,arr.size()*2 <= 100 ? arr.size()*2:100);
        }
        end[0] = len-1;

    }


}
