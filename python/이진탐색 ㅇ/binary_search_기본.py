'''
    <파이썬 이진 탐색 라이브러리>
    
    - bisect_left(a,x) : 정렬된 순서를 유지하면서 배열 a에 x를 삽입할 가장 왼쪽 인덱스를 반환
    - bisect_right(a,x) : 정렬된 순서를 유지하면서 배열 a에 x를 삽입할 가장 오른쪽 인덱스를 반환
    
    - 값이 특정 범위에 속하는 데이터 갯수 구하기
    
    from bisect import bisect_left,bisect_right
    
    def count_by_range(a, left, right):
        right_index = bisect_right(a,right)
        left_index = bisect_left(a,left)
        return right_index - left_index 

'''

def binary_search(array, target, start, end):
    if start > end :
        return None
    mid = (start+end)//2
    
    if array[mid] == target:
        return mid
    elif array[mid] > target:
        return binary_search(array,target,start,mid-1)
    elif array[mid] < target:
        return binary_search(array,target,mid+1,end)

n, target = list(map(int, input().split()))
array = list(map(int, input().split()))

result = binary_search(array, target, 0 , n-1)
if result == None:
    print(" 존재 x")
else :
    print(result+1)
