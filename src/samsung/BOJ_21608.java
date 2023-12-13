package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_21608 {
    static int N;
    static int[][] map;
    static int[][] like;
    static HashMap<Integer,int[]> now = new HashMap<>();
    static HashMap<Integer, ArrayList<Integer>> friends = new HashMap<>();
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    static ArrayList<int []> likes = new ArrayList<>();
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;

        for(int i = 0 ; i < N*N ; i++){
            st = new StringTokenizer(br.readLine());
            int stu = Integer.parseInt(st.nextToken());
            friends.put(stu,new ArrayList<>());

            like = new int[N][N];
            int max_like = 0;

            for(int j = 0 ; j < 4; j++){
                int temp = Integer.parseInt(st.nextToken());
                friends.get(stu).add(temp);
                if(now.containsKey(temp)){
                    // 친구가 이미 할당되어 있는 상태면
                    // 해당 친구(temp)의 사방위치중
                    for(int k = 0 ; k < 4; k++){
                        int nx = now.get(temp)[0]+dx[k];
                        int ny = now.get(temp)[1]+dy[k];

                        if(nx >= 0 && nx < N  && ny >= 0 && ny < N){
                            if(map[nx][ny] == 0){
                                like[nx][ny]++;
                                max_like = Math.max(max_like,like[nx][ny]);
                            }
                        }
                    }
                }
            }

            // 여기서 like 최대값인 자리 모두 likes에 넣기 + 비어있는 자리 추가
            for(int j = 0 ; j < N ; j++){
                for(int k = 0 ; k < N ; k++){
                    if(like[j][k] == max_like && map[j][k] == 0){

                        int emp = 0;

                        for(int l = 0 ; l < 4 ; l++){
                            int nx = j+dx[l];
                            int ny = k+dy[l];

                            if(nx >= 0 && nx < N  && ny >= 0 && ny < N){
                                if(map[nx][ny] == 0) emp++;
                            }
                        }

                        likes.add(new int[] {j,k,emp});
                    }
                }
            }

            // 그리고 Collections.sort(like)로 정렬
            Collections.sort(likes,(o1,o2)->{
                if(o1[2] == o2[2]){
                    if(o1[0] == o2[0]){
                        return o1[1] - o2[1];
                    }else{
                        return o1[0] - o2[0];
                    }
                }else{
                    return o2[2]-o1[2];
                }
            });

            // 할당
            map[likes.get(0)[0]][likes.get(0)[1]] = stu;
            now.put(stu,new int[] {likes.get(0)[0],likes.get(0)[1]});
            likes.clear();

        }

        // 주변에 좋아하는 친구가 앉아 있는지 체크
        int sum = 0;
        int[][] check = new int[N][N];

        // check
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                for(int k = 0 ; k < 4 ; k++){
                    int nx = i+dx[k];
                    int ny = j+dy[k];

                    if(nx >= 0 && nx < N  && ny >= 0 && ny < N){
                        if(friends.get(map[i][j]).contains(map[nx][ny])){
                           check[i][j]++;
                        }
                    }
                }
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                sum+= Math.pow(10,check[i][j]-1);
            }
        }

        System.out.println(sum);

    }
}
