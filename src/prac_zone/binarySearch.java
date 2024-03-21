package prac_zone;

import java.util.ArrayList;
import java.util.Arrays;

public class binarySearch {
    public static void main(String[] args) throws Exception {

        //             0  1  2  3  4  5  6  7  8  9  10
        //int[] nums = {10,20,30,40,50,50,50,60,70,80,100};
        int[] nums = {1,3,5,3,5,4,3,7,5,9};

        int key = 50;


        // TODO : 1. 일치하는 값의 index 찾기
        System.out.println(binarySearch(nums,key));
        System.out.println(binarySearchByLibrary(nums,key));

        // TODO : 2. FFFTTT - lowerBound
        System.out.println(binarySearchForLowerBound(nums,key));

        // TODO : 3. FFFTTT - UpperBound
        System.out.println(binarySearchForUpperBound(nums,key));

        // TODO : 4. TTTFFF - lowerBound
        System.out.println(reverseBinarySearchForLowerBound(nums,key));

        // TODO : 5. TTTFFF - UpperBound
        System.out.println(reverseBinarySearchForUpperBound(nums,key));

        // TODO : 최장 증가 수열 구해보자
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0 ; i < nums.length; i++){
            if (arr.isEmpty() || arr.get(arr.size()-1) < nums[i]) arr.add(nums[i]);
            else{
                int idx = LISByArrayList(arr,nums[i]);
                arr.set(idx,nums[i]);
            }
        }

        System.out.println();
        System.out.println("최장 증가 수열 :");
        for(int a : arr){
            System.out.print(a+" ");
        }

        // TODO : 최장 감소 수열 구해보자
        ArrayList<Integer> arr2 = new ArrayList<>();
        for(int i = nums.length-1 ; i >= 0; i--){
            System.out.println();
            for(int a : arr2){
                System.out.print(a+" ");
            }
            if (arr2.isEmpty() || arr2.get(arr2.size()-1) > nums[i]) arr2.add(nums[i]);
            else{
                int idx = LDSByArrayList(arr2,nums[i]);
                arr2.set(idx,nums[i]);
            }
        }

        System.out.println();
        System.out.println("최장 감소 수열 :");
        for(int a : arr2){
            System.out.print(a+" ");
        }

    }

    // TODO : 1. 일치하는 값의 index 찾기
    static int binarySearch(int[] nums, int key){
        int left = -1;
        int right = nums.length;
        while(left + 1 < right){
            int mid = (left+right)/2;

            if(nums[mid] == key){
                return mid;
            }else if(nums[mid] < key){
                left = mid;
            }else{
                right = mid;
            }
        }
        return -1;
    }

    static int binarySearchByLibrary(int[] nums, int key){
        return Arrays.binarySearch(nums,key);
    }

    /*
        right = mid는 오른쪽 경계를 왼쪽으로 미는 행위
        left = mid는 왼쪽 경계를 오른쪽으로 미는 행위
     */


    // TODO : 2. lowerBound
    //      FFFTTT인 상황에서
    //      ... F F F (T) T ...
    static int binarySearchForLowerBound(int[] nums, int key){
        int left = -1;
        int right = nums.length;

        while(left + 1 < right){
            int mid = (left+right)/2;

            if(nums[mid] >= key){
                // 조건 만족하는 경우 - 현재까지 찾은 값중 mid가 최선의 답
                right = mid;
            }else{
                // 조건 만족안하는 경우 - 해당 mid 값은 더이상 필요 x
                left = mid;
            }
        }
        return right;
    }

    static int LISByArrayList(ArrayList<Integer> nums, int key){
        int left = -1;
        int right = nums.size();

        while(left + 1 < right){
            int mid = (left+right)/2;

            if(nums.get(mid) >= key){
                // 조건 만족하는 경우 - 현재까지 찾은 값중 mid가 최선의 답
                right = mid;
            }else{
                // 조건 만족안하는 경우 - 해당 mid 값은 더이상 필요 x
                left = mid;
            }
        }
        return right;
    }

    static int LDSByArrayList(ArrayList<Integer> nums, int key){
        int left = -1;
        int right = nums.size();

        while(left + 1 < right){
            int mid = (left+right)/2;

            if(nums.get(mid) <= key){
                // 조건 만족하는 경우 - 현재까지 찾은 값중 mid가 최선의 답
                right = mid;
            }else{
                // 조건 만족안하는 경우 - 해당 mid 값은 더이상 필요 x
                left = mid;
            }
        }
        return right;
    }

    // TODO : 3. UpperBound
    static int binarySearchForUpperBound(int[] nums, int key){
        int left = -1;
        int right = nums.length;

        while(left +1 < right){
            int mid = (left+right)/2;

            if(nums[mid] > key){
                // 조건 만족하는 경우 - 현재까지 찾은 값중 mid가 최선의 답
                right = mid;
            }else{
                // 조건 만족안하는 경우 - 해당 mid 값은 더이상 필요 x
                left = mid;
            }
        }

        return right;
    }

    // TODO : 4. TTTFFF - LowerBound
    static int reverseBinarySearchForLowerBound(int[] nums, int key){
        int left = -1;
        int right = nums.length;

        while(left + 1 < right){
            int mid = (left+right)/2;

            if(nums[mid] <= key){
                // 조건 만족하는 경우 - 현재까지 찾은 값중 mid가 최선의 답
                left = mid;
            }else{
                // 조건 만족안하는 경우 - 해당 mid 값은 더이상 필요 x
                right = mid;
            }
        }

        return left;
    }

    // TODO : 5. TTTFFF - UpperBound
    static int reverseBinarySearchForUpperBound(int[] nums, int key){
        int left = -1;
        int right = nums.length;

        while(left +1 < right){
            int mid = (left+right)/2;

            if(nums[mid] < key){
                // 조건 만족하는 경우 - 현재까지 찾은 값중 mid가 최선의 답
                left = mid;
            }else{
                // 조건 만족안하는 경우 - 해당 mid 값은 더이상 필요 x
                right = mid;
            }
        }

        return left;
    }
}
