package prac_zone;

public class binarySearch {
    public static void main(String[] args) throws Exception {

        //             0  1  2  3  4  5  6  7  8  9  10
        int[] nums = {10,20,30,40,40,40,50,60,70,80,100};
        int key = 45;


        // TODO : 1. 일치하는 값의 index 찾기
        System.out.println(binarySearch(nums,key));

        // TODO : 2. FFFTTT - lowerBound
        System.out.println(binarySearchForLowerBound(nums,key));

        // TODO : 3. FFFTTT - UpperBound
        System.out.println(binarySearchForUpperBound(nums,key));

        // TODO : 4. TTTFFF - lowerBound
        System.out.println(reverseBinarySearchForLowerBound(nums,key));

        // TODO : 5. TTTFFF - UpperBound
        System.out.println(reverseBinarySearchForUpperBound(nums,key));




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

    // TODO : 2. lowerBound
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
