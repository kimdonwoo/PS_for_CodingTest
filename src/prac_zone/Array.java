package prac_zone;

public class Array {

    static int[][] map = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
    static int N = 5 ;
    public static void main(String[] args) throws Exception {



        System.out.println("[이전]");
        for(int i = 0 ; i < N ; i++ ){
            for(int j = 0 ; j < N ; j++ ){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }


//        int[][] temp = new int[5][5];
//
//        for(int i = 0 ; i < N ; i++ ){
//            for(int j = 0 ; j < N ; j++ ){
//                temp[j][i] = map[i][j]; // y=-x 그래프 대칭
//                temp[(N-1)-i][(N-1)-j] = map[i][j]; // 시계방향 2번
//                temp[(N-1)-j][(N-1)-i] = map[i][j]; // y=x 그래프 대칭
//                temp[(N-1)-i][j] = map[i][j]; // 가로축 대칭
//                temp[i][(N-1)-j] = map[i][j]; // 세로축 대칭
//            }
//        }
//
//        System.out.println("[이후]");
//        for(int i = 0 ; i < N ; i++ ){
//            for(int j = 0 ; j < N ; j++ ){
//                System.out.print(temp[i][j]+" ");
//            }
//            System.out.println();
//        }


        // 조회 방식
        System.out.println("\n[조회 방식]");
        for(int i = 0 ; i < N ; i++ ){
            for(int j = 0 ; j < N ; j++ ){
                //System.out.print(map[j][i]+" "); // y=-x 그래프 대칭
                //System.out.print(map[(N-1)-i][(N-1)-j]+" "); // 시계방향 2번
                //System.out.print(map[(N-1)-j][(N-1)-i]+" "); // y==x 그래프 대칭
                //System.out.print(map[(N-1)-i][j]+" "); // 가로축 대칭
                //System.out.print(map[i][(N-1)-j]+" "); // 세로축 대칭
                //System.out.print(map[(N-1)-j][i]+" "); // 시계방향
                //System.out.print(map[j][(N-1)-i]+" "); // 반시계방향
            }
            System.out.println();
        }

        int rows = 5;    // 행의 개수
        int cols = 5;

        // 1. (0,0)부터 시계 방향으로 조회
        System.out.println(" (0,0)부터 시계 방향으로 조회하기");

        for (int layer = 0; layer < Math.min(rows, cols) / 2; layer++) {
            // 위쪽 행
            for (int i = layer; i < cols - layer; i++) {
                System.out.print(map[layer][i] + " ");
            }

            // 오른쪽 열
            for (int i = layer + 1; i < rows - layer; i++) {
                System.out.print(map[i][cols - 1 - layer] + " ");
            }

            // 아래쪽 행
            for (int i = cols - 2 - layer; i >= layer; i--) {
                System.out.print(map[rows - 1 - layer][i] + " ");
            }

            // 왼쪽 열
            for (int i = rows - 2 - layer; i > layer; i--) {
                System.out.print(map[i][layer] + " ");
            }
        }

        // 2. (0,0)부터 반시계 방향으로 조회

        // 3. 중앙부터 시계 방향으로 조회

        // 4. 중앙부터 반시계 방향으로 조회


    }
}
