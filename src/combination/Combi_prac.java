package combination;

import java.util.HashMap;

public class Combi_prac {

    static HashMap<Integer,Boolean> select = new HashMap<>();
    static HashMap<Integer,Boolean> noSelect = new HashMap<>();
    static int[][] list ;
    static int[] ans;
    static int n,res = 0;

    public static void main(String[] args){

        n = 10;
        list = new int[][] {{1,2,3,4,5},{6,7,8,9,10},{3,4,5,6,7}};
        ans = new int[]{3, 2, 4};


        combi(0,0,0,ans[0]);

        System.out.println(res);

    }

    /*
        order : 몇번째 리스트인지
        depth : 현재 선택한 숫자
        now : list[] 배열에서 현재 몇번까지 확인했는지
        goal : 각 리스트마다 선택해야하는 숫자

     */
    private static void combi(int order, int depth, int now, int goal){

        if(select.size() > 5) return;

        // 모두 선택 완료
        if(depth == goal){

            if(order == list.length-1){

                for(int k : select.keySet()){
                    System.out.print(k+" ");
                }
                System.out.println();
                System.out.println("======");

                if(select.size() == 5){
                    res++;
                }else{
                    res+=2;
                }
                return;
            }

            for(int i = 0 ; i < 5 ; i++){
                if(!select.containsKey(list[order][i])){
                    noSelect.put(list[order][i],true);
                }
            }

            int temp = 0;
            for(int i = 0 ; i < 5 ; i++){
                if(select.containsKey(list[order+1][i])){
                    temp++;
                }
            }

            combi(order+1,0,0,ans[order+1]-temp);

            for(int i = 0 ; i < 5 ; i++){
                if(!select.containsKey(list[order][i])){
                    noSelect.remove(list[order][i]);
                }
            }

            return;
        }

        for(int i = now ; i < 5 ; i++){
            if(noSelect.containsKey(list[order][i])) continue;
            if(select.containsKey(list[order][i])) continue;

            select.put(list[order][i],true);
            combi(order,depth+1,i+1,goal);
            select.remove(list[order][i]);

        }

    }
}
